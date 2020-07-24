package ca.otterspace.ottercraft;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.RiverBiome;
import net.minecraft.world.biome.SwampBiome;

import javax.annotation.Nullable;

public class OtterEntity extends WolfEntity {
    private static final DataParameter<Integer> OTTER_SUBSPECIES = EntityDataManager.createKey(OtterEntity.class, DataSerializers.VARINT);

    public enum Animation {
        IDLE,
        RUNNING
    };
    public Animation animation;
    public int frame;
    public int dframe;
    public OtterEntity(EntityType<? extends WolfEntity> type, World worldIn) {
        super(type, worldIn);
        this.animation = Animation.IDLE;
        this.frame = 0;
        this.dframe = 1;
    }
    public void setOtterSubspecies(int subspecies) { this.dataManager.set(OTTER_SUBSPECIES, subspecies); }
    public int getOtterSubspecies() {
        return this.dataManager.get(OTTER_SUBSPECIES);
    }
    protected void registerData() {
        super.registerData();
        this.dataManager.register(OTTER_SUBSPECIES, 0);
    }
    public void readAdditional(CompoundNBT compound) {
        this.setOtterSubspecies(compound.getInt("Subspecies"));
        super.readAdditional(compound);
    }
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Subspecies", this.getOtterSubspecies());
    }
    public void livingTick() {
        super.livingTick();
        double dx = Math.abs(this.getMotion().getX());
        double dz = Math.abs(this.getMotion().getZ());

        this.animation = (dx*dx+dz*dz <= 0.001)? Animation.IDLE : Animation.RUNNING;

        this.frame = (this.frame + this.dframe) % 20;
        if (this.animation == Animation.RUNNING) {
            this.dframe = (this.dframe == 1) ? 2 : 1;
        }
        else
            this.dframe = 1;
    }
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }
    public boolean canBeSteered() { return true; }
    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (itemstack.getItem() == Items.NAME_TAG) {
            itemstack.interactWithEntity(player, this, hand);
            return true;
        } else if (!this.isBeingRidden()) {
            if (this.getOtterSubspecies() == 1 && !this.world.isRemote) {
                player.startRiding(this);
            }
            return true;
        } else
            return false;
    }
    public void travel(Vec3d p_213352_1_) {
        if (this.isAlive()) {
            LivingEntity entity = (LivingEntity)getControllingPassenger();

            if (this.isBeingRidden() && this.canBeSteered()) {
                float strafe = entity.moveStrafing * 0.5F;
                float forward = entity.moveForward;
                this.rotationYaw = entity.rotationYaw;
                this.prevRotationYaw = this.rotationYaw;
                this.rotationPitch = entity.rotationPitch * 0.5F;
                this.setRotation(this.rotationYaw, this.rotationPitch);
                this.renderYawOffset = this.rotationYaw;
                this.rotationYawHead = this.rotationYaw;
                this.stepHeight = 1.0F;
                this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

                if (this.canPassengerSteer()) {
                    float f = (float)this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue() * 0.225F;

                    this.setAIMoveSpeed(f);
                    super.travel(new Vec3d((double)strafe, p_213352_1_.y, (double)forward));
                    this.newPosRotationIncrements = 0;
                } else {
                    this.setMotion(Vec3d.ZERO);
                }

                this.prevLimbSwingAmount = this.limbSwingAmount;
                double d1 = this.getPosX() - this.prevPosX;
                double d0 = this.getPosZ() - this.prevPosZ;
                float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
                if (f1 > 1.0F) {
                    f1 = 1.0F;
                }

                this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
                this.limbSwing += this.limbSwingAmount;
            } else {
                this.stepHeight = 0.5F;
                this.jumpMovementFactor = 0.02F;
                super.travel(p_213352_1_);
            }
        }
    }
    public static class OtterData extends AgeableEntity.AgeableData {
        public final int subspecies;
        public OtterData(int subspecies) { this.subspecies = subspecies; }
    };
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        int i;
        if (spawnDataIn instanceof OtterData)
            i = ((OtterData)spawnDataIn).subspecies;
        else {
            Biome biome = worldIn.getBiome(this.getPosition());
            if (biome instanceof SwampBiome)
                i = 1;
            else
                i = 0;
            spawnDataIn = new OtterData(i);
        }
        this.setOtterSubspecies(i);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}
