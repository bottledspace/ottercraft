package ca.otterspace.ottercraft;

import ca.otterspace.ottercraft.goals.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.IAnimatable;

import javax.annotation.Nullable;

import java.util.UUID;
import java.util.function.Predicate;


public class EntityOtter extends TameableEntity implements IAnimatable, ISemiAquatic, IAngerable {
    protected static final DataParameter<Boolean> BEGGING = EntityDataManager.defineId(EntityOtter.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.defineId(EntityOtter.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_REMAINING_ANGER_TIME = EntityDataManager.defineId(WolfEntity.class, DataSerializers.INT);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.COD || entitytype == EntityType.SALMON
                || entitytype == EntityType.TROPICAL_FISH;
    };
    private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
    private UUID persistentAngerTarget;

    public EntityOtter(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        this.setTame(false);
        this.noCulling = true;
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        this.setPathfindingMalus(PathNodeType.WATER_BORDER, 0.0F);
        this.switchNavigator(false);
    }

    public static AttributeModifierMap.MutableAttribute createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.4F)
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BEGGING, false);
        this.entityData.define(COLLAR_COLOR, DyeColor.RED.getId());
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, (double)this.getBbHeight() * 0.5F, (double)(this.getBbWidth() * 0.4F));
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);

        compound.putByte("CollarColor", (byte)this.getCollarColor().getId());
        this.addPersistentAngerSaveData(compound);
    }
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("CollarColor", 99)) {
            this.setCollarColor(DyeColor.byId(compound.getInt("CollarColor")));
        }
        if (!level.isClientSide)
            this.readPersistentAngerSaveData((ServerWorld)this.level, compound);
    }
    public DyeColor getCollarColor() {
        return DyeColor.byId(this.entityData.get(COLLAR_COLOR));
    }
    public void setCollarColor(DyeColor collar) {
        this.entityData.set(COLLAR_COLOR, collar.getId());
    }
    public boolean isBegging() { return this.entityData.get(BEGGING); }
    public void setBegging(boolean value) { this.entityData.set(BEGGING, value); }

    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int p_230260_1_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, p_230260_1_);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@Nullable UUID p_230259_1_) {
        this.persistentAngerTarget = p_230259_1_;
    }


    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld level, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData edat, @Nullable CompoundNBT nbt) {
        this.setAirSupply(this.getMaxAirSupply());
        this.xRot = 0.0F;
        return super.finalizeSpawn(level, difficulty, reason, edat, nbt);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity parent) {
        return Ottercraft.OTTER.create(world);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        Item item = stack.getItem();
        return item == Items.COD
             | item == Items.SALMON;
    }

    @Override
    public void die(DamageSource cause) {
        if (!this.level.isClientSide
                && this.level.getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES)
                && this.getOwner() instanceof ServerPlayerEntity) {
            this.getOwner().sendMessage(this.getCombatTracker().getDeathMessage(), Util.NIL_UUID);
        }

        super.die(cause);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return Ottercraft.OTTER_SQUEAK;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Ottercraft.OTTER_ANGRY;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    protected boolean isLandNavigator;
    protected int swimTimer = 0;

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public int getMaxAirSupply() {
        return 4800;
    }

    @Override
    protected int increaseAirSupply(int delta) {
        // Instantly fill to max
        return getMaxAirSupply();
    }

    @Override
    public boolean shouldEnterWater() {
        return swimTimer < 1000;
    }

    @Override
    public boolean shouldLeaveWater() {
        return swimTimer > 1000;
    }


    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (this.isInWater())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.slide", true));
        else if (this.onGround && event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.run", true));
        else if (this.isInSittingPose())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.sit", true));
        else if (this.isBegging() || this.isPassenger())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.beg", true));
        else
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<EntityOtter> controller = new AnimationController<>(this, "controller", 3, this::animationPredicate);
        data.addAnimationController(controller);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreatheAirGoal(this));
        this.goalSelector.addGoal(1, new SitGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new GoalBeg(this, 4.0F));
        this.goalSelector.addGoal(4, new GoalPlayWithItems(this));
        this.goalSelector.addGoal(5, new GoalSwimWithPlayer(this, 4.0D));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new GoalEnterWater(this));
        this.goalSelector.addGoal(10, new GoalLeaveWater(this));
        this.goalSelector.addGoal(11, new RandomWalkingGoal(this, 1.0D, 60));
        this.goalSelector.addGoal(12, new AvoidEntityGoal<>(this, GuardianEntity.class, 8.0F, 1.0D, 1.0D));
        this.goalSelector.addGoal(13, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(14, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTamedTargetGoal<>(this, TurtleEntity.class, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
        this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, GuardianEntity.class)).setAlertOthers());
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.navigation = new GroundPathNavigator(this, this.level);
            this.moveControl = new MovementController(this);
            this.isLandNavigator = true;
        } else {
            this.navigation = new SemiAquaticPathNavigator(this, this.level);
            this.moveControl = new SwimMovementController(this, 2.5f, 1.6f);
            this.isLandNavigator = false;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isInWaterOrBubble() && this.isInSittingPose())
            super.setOrderedToSit(false);

        if (!this.level.isClientSide) {
            if (this.isInWater()) {
                this.swimTimer++;
            } else {
                this.swimTimer--;
            }
        }

        if (this.isInWaterOrBubble() && this.isLandNavigator) {
            switchNavigator(false);
        }
        if (!this.isInWaterOrBubble() && !this.isLandNavigator) {
            switchNavigator(true);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerWorld)this.level, true);
        }
    }

    @Override
    public void setOrderedToSit(boolean sit) {
        if (this.isInWaterOrBubble() && sit)
            return;  // Can't be ordered to sit in water!
        super.setOrderedToSit(sit);
    }

    @Override
    public void setTame(boolean p_70903_1_) {
        super.setTame(p_70903_1_);
        if (p_70903_1_) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if (itemstack.isEmpty() && this.isTame()) {
            // If we are already tame, take this as a command to sit.
            boolean shouldSit = !this.isInSittingPose();
            this.setOrderedToSit(shouldSit);
            return ActionResultType.SUCCESS;

        } else if (this.isFood(itemstack)) {
            if (!this.isTame()) {
                // If not already tame and offered food, become tame and sit.

                itemstack.shrink(1);
                super.tame(playerIn);
                this.navigation.stop();
                this.setTarget(null);
                this.setOrderedToSit(true);

                // Probably broadcasts that we are now tame?
                this.level.broadcastEntityEvent(this, (byte)7);

                return ActionResultType.SUCCESS;
            } else {
                // If already tame and offered food, heal if hurt. Otherwise fall through so that we may breed.

                if (this.getHealth() < this.getMaxHealth()) {
                    if (!playerIn.abilities.instabuild) {
                        itemstack.shrink(1);
                    }
                    this.heal((float)itemstack.getItem().getFoodProperties().getNutrition());
                    return ActionResultType.CONSUME;
                }
            }
        }
        return super.mobInteract(playerIn, hand);
    }
}