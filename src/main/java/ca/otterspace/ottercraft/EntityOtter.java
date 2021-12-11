package ca.otterspace.ottercraft;

import ca.otterspace.ottercraft.goals.*;
import com.mojang.math.Vector3d;
import net.minecraft.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
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


public class EntityOtter extends TamableAnimal implements IAnimatable, ISemiAquatic, NeutralMob {
    protected static final EntityDataAccessor<Boolean> BEGGING = SynchedEntityData.defineId(EntityOtter.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<Integer> COLLAR_COLOR = SynchedEntityData.defineId(EntityOtter.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(EntityOtter.class, EntityDataSerializers.INT);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.COD || entitytype == EntityType.SALMON
                || entitytype == EntityType.TROPICAL_FISH;
    };
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private UUID persistentAngerTarget;

    public EntityOtter(EntityType<? extends TamableAnimal> type, Level worldIn) {
        super(type, worldIn);
        this.setTame(false);
        this.noCulling = true;
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
        this.switchNavigator(false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
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
    public Vec3 getLeashOffset() {
        return new Vec3(0.0D, (double)this.getBbHeight() * 0.5F, (double)(this.getBbWidth() * 0.4F));
    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        if (this.isBegging() || this.isPassenger())
            return 1.0f;
        else
            return super.getEyeHeight(p_213307_1_);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putByte("CollarColor", (byte)this.getCollarColor().getId());
        this.addPersistentAngerSaveData(compound);
    }
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("CollarColor", 99)) {
            this.setCollarColor(DyeColor.byId(compound.getInt("CollarColor")));
        }
        if (!level.isClientSide)
            this.readPersistentAngerSaveData(this.level, compound);
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
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@Nullable UUID p_230259_1_) {
        this.persistentAngerTarget = p_230259_1_;
    }

    @Override
    public boolean wantsToAttack(LivingEntity p_142018_1_, LivingEntity p_142018_2_) {
        if (!(p_142018_1_ instanceof Creeper) && !(p_142018_1_ instanceof Ghast)) {
            if (p_142018_1_ instanceof Wolf) {
                Wolf wolfentity = (Wolf) p_142018_1_;
                return !wolfentity.isTame() || wolfentity.getOwner() != p_142018_2_;
            } else if (p_142018_1_ instanceof EntityOtter) {
                // Otters won't attack otters!
                return false;
            } else if (p_142018_1_ instanceof Player && p_142018_2_ instanceof Player && !((Player)p_142018_2_).canHarmPlayer((Player)p_142018_1_)) {
                return false;
            } else if (p_142018_1_ instanceof AbstractHorse && ((AbstractHorse)p_142018_1_).isTamed()) {
                return false;
            } else {
                return !(p_142018_1_ instanceof TamableAnimal) || !((TamableAnimal)p_142018_1_).isTame();
            }
        } else {
            return false;
        }
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData edat, @Nullable CompoundTag nbt) {
        this.setAirSupply(this.getMaxAirSupply());
        //this.xRot = 0.0F;
        return super.finalizeSpawn(level, difficulty, reason, edat, nbt);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob parent) {
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
                && this.getOwner() instanceof ServerPlayer) {
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
        return swimTimer < -1000;
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
        else if (event.isMoving())
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
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new GoalBeg(this, 4.0F));
        this.goalSelector.addGoal(4, new GoalPlayWithItems(this));
        this.goalSelector.addGoal(5, new GoalSwimWithPlayer(this, 4.0D));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new GoalEnterWater(this));
        this.goalSelector.addGoal(10, new GoalLeaveWater(this));
        this.goalSelector.addGoal(11, new RandomStrollGoal(this, 1.0D, 60));
        this.goalSelector.addGoal(12, new AvoidEntityGoal<>(this, Guardian.class, 8.0F, 1.0D, 1.0D));
        this.goalSelector.addGoal(13, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(14, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeleton.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Guardian.class)).setAlertOthers());
    }

    private void switchNavigator(boolean onLand) {
        if (onLand) {
            this.navigation = new GroundPathNavigation(this, this.level);
            this.moveControl = new MoveControl(this);
            this.isLandNavigator = true;
            //Ottercraft.LOGGER.info("switching to land");
        } else {
            this.navigation = new SemiAquaticPathNavigator(this, this.level);
            this.moveControl = new SwimMovementController(this, 2.5f, 1.6f);
            this.isLandNavigator = false;
            //Ottercraft.LOGGER.info("switching to water");
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

        if (this.isSwimming() && this.isLandNavigator) {
            switchNavigator(false);
        }
        if (this.isOnGround() && !this.isLandNavigator) {
            switchNavigator(true);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel) this.level, true);
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
    public InteractionResult mobInteract(Player playerIn, InteractionHand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if (itemstack.isEmpty() && this.isTame()) {
            // If we are already tame, take this as a command to sit.
            boolean shouldSit = !this.isInSittingPose();
            this.setOrderedToSit(shouldSit);
            return InteractionResult.SUCCESS;

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

                return InteractionResult.SUCCESS;
            } else {
                // If already tame and offered food, heal if hurt. Otherwise fall through so that we may breed.

                if (this.getHealth() < this.getMaxHealth()) {
                    if (!playerIn.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    this.heal((float)itemstack.getItem().getFoodProperties().getNutrition());
                    return InteractionResult.CONSUME;
                }
            }
        }
        return super.mobInteract(playerIn, hand);
    }
}