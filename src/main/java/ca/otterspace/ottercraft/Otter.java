package ca.otterspace.ottercraft;

import ca.otterspace.ottercraft.goals.*;
import ca.otterspace.skeletal.AnimationController;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.*;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Predicate;


public class Otter extends TameableEntity implements ISemiAquatic, IBegger, IAngerable {
    protected static final DataParameter<Boolean> BEGGING = EntityDataManager.defineId(Otter.class, DataSerializers.BOOLEAN);
    protected static final DataParameter<Integer> COLLAR_COLOR = EntityDataManager.defineId(Otter.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_REMAINING_ANGER_TIME = EntityDataManager.defineId(Otter.class, DataSerializers.INT);
    public static final Predicate<LivingEntity> PREY_SELECTOR = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.COD || entitytype == EntityType.SALMON
                || entitytype == EntityType.TROPICAL_FISH;
    };
    private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);
    private UUID persistentAngerTarget;
    public AnimationController animationController = new ca.otterspace.skeletal.AnimationController();
    
    public Otter(EntityType<? extends TameableEntity> type, World worldIn) {
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
        this.entityData.define(COLLAR_COLOR, DyeColor.YELLOW.getId());
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }
    
    @Override
    public Vector3d getLeashOffset() {
        return new Vector3d(0.0D, (double)this.getBbHeight() * 0.5F, (double)(this.getBbWidth() * 0.4F));
    }
    
    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        if (this.isBegging() || this.isPassenger())
            return 1.0f;
        else
            return super.getEyeHeight(p_213307_1_);
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
    
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }
    
    public void setPersistentAngerTarget(UUID p_230259_1_) {
        this.persistentAngerTarget = p_230259_1_;
    }
    
    @Override
    public boolean wantsToAttack(LivingEntity p_142018_1_, LivingEntity p_142018_2_) {
        if (!(p_142018_1_ instanceof CreeperEntity) && !(p_142018_1_ instanceof GhastEntity)) {
            if (p_142018_1_ instanceof WolfEntity) {
                WolfEntity wolfentity = (WolfEntity) p_142018_1_;
                return !wolfentity.isTame() || wolfentity.getOwner() != p_142018_2_;
            } else if (p_142018_1_ instanceof Otter) {
                // Otters won't attack otters!
                return false;
            } else if (p_142018_1_ instanceof PlayerEntity && p_142018_2_ instanceof PlayerEntity && !((PlayerEntity)p_142018_2_).canHarmPlayer((PlayerEntity)p_142018_1_)) {
                return false;
            } else if (p_142018_1_ instanceof HorseEntity && ((HorseEntity)p_142018_1_).isTamed()) {
                return false;
            } else {
                return !(p_142018_1_ instanceof TameableEntity) || !((TameableEntity)p_142018_1_).isTame();
            }
        } else {
            return false;
        }
    }

    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
        this.setAirSupply(this.getMaxAirSupply());
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }
    
    @Override
    public boolean isFood(ItemStack stack) {
        Item item = stack.getItem();
        return item == Items.COD
                | item == Items.SALMON;
    }
    
    @Override
    public int getMaxHeadYRot() {
        return 37;
    }
    
    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }
    
    protected boolean isLandNavigator;
    protected int swimTimer = 0;
    
    @Override
    public boolean canBreatheUnderwater() {
        return true;
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
        this.goalSelector.addGoal(12, new AvoidEntityGoal(this, GuardianEntity.class, 8.0F, 1.0D, 1.0D));
        this.goalSelector.addGoal(13, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(14, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, PlayerEntity.class, 10, true, false, entity -> entity instanceof LivingEntity && this.isAngryAt((LivingEntity)entity)));
        this.targetSelector.addGoal(5, new NonTamedTargetGoal(this, AnimalEntity.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTamedTargetGoal(this, TurtleEntity.class, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, SkeletonEntity.class, false));
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
            this.moveControl = new SwimMovementController(this,  2.5f, 1.6f);
            this.isLandNavigator = false;
        }
    }
    
    void setAnimation() {
        Vector3d movement = new Vector3d(this.getDeltaMovement().x(), 0, this.getDeltaMovement().z());
        if (isInWater())
            animationController.setAnimation("animation.otter.slide");
        else if (movement.dot(movement) > 0.02f*0.02f)
            animationController.setAnimation("animation.otter.run");
        else if (isInSittingPose())
            animationController.setAnimation("animation.otter.sit");
        else if (isBegging() || isPassenger())
            animationController.setAnimation("animation.otter.beg");
        else
            animationController.setAnimation("animation.otter.idle");
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
        
        if (this.level.isClientSide) {
            if (this.animationController != null) {
                this.setAnimation();
                this.animationController.tick();
            }
        }
    }
    
    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerWorld) this.level, true);
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
    
    protected ActionResultType mobInteractTame(PlayerEntity playerIn, Hand hand) {
        if (!isOwnedBy(playerIn))
            return ActionResultType.PASS;
        
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if (itemstack.isEmpty()) {
            // Command to sit.
            boolean shouldSit = !this.isInSittingPose();
            this.setOrderedToSit(shouldSit);
            return ActionResultType.SUCCESS;
        }
    
        Item item = itemstack.getItem();
        if (this.isFood(itemstack)) {
            // If already tame and offered food, heal if hurt.
            if (this.getHealth() < this.getMaxHealth()) {
                float nutrition = (float)item.getFoodProperties().getNutrition();
        
                if (!playerIn.abilities.instabuild) {
                    itemstack.shrink(1);
                }
                this.heal(nutrition);
                return ActionResultType.CONSUME;
                
            // Otherwise allow breeding
            } else
                return super.mobInteract(playerIn, hand);
            
        } else if ((item instanceof DyeItem) && this.isTame()) {
            // Dye the harness color
            DyeColor color = ((DyeItem)item).getDyeColor();
            if (color != this.getCollarColor()) {
                this.setCollarColor(color);
                if (!playerIn.abilities.instabuild) {
                    itemstack.shrink(1);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }
    
    protected ActionResultType mobInteractWild(PlayerEntity playerIn, Hand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        if (itemstack.isEmpty())
            return ActionResultType.PASS;
    
        if (this.isFood(itemstack)) {
            // If not already tame and offered food, become tame and sit.
            if (!playerIn.abilities.instabuild)
                itemstack.shrink(1);
            super.tame(playerIn);
            this.navigation.stop();
            this.setTarget(null);
            this.setOrderedToSit(true);
    
            // Probably broadcasts that we are now tame?
            this.level.broadcastEntityEvent(this, (byte)7);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity playerIn, Hand hand) {
        if (isTame())
            return mobInteractTame(playerIn, hand);
        else
            return mobInteractWild(playerIn, hand);
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
        return Ottercraft.OTTER_SQUEAK;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return Ottercraft.OTTER_ANGRY;
    }
    
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity parent) {
        Otter otter = Ottercraft.OTTER.create(world);
        
        // If either parent is owned by a player, they own the offspring
        for (LivingEntity parentEntity : ImmutableList.of(this, parent)) {
            if (!(parentEntity instanceof Otter))
                continue;  // Shouldn't happen??
            Otter parentOtter = (Otter)parentEntity;
            if (!parentOtter.isTame())
                continue;  // Otter is wild
            if (!(parentOtter.getOwner() instanceof PlayerEntity))
                continue;  // Otter isn't owned by a player
            
            PlayerEntity player = (PlayerEntity)parentOtter.getOwner();
            otter.tame(player);
            break;
        }
        return otter;
    }
}