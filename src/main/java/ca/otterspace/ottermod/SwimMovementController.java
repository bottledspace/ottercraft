package ca.otterspace.ottermod;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.util.math.MathHelper;

public class SwimMovementController extends MovementController {
    private final CreatureEntity entity;
    private float speedMulti;
    private float ySpeedMod = 1;
    private float yawLimit = 10.0F;

    public SwimMovementController(CreatureEntity entity, float speedMulti, float ySpeedMod) {
        super(entity);
        this.entity = entity;
        this.speedMulti = speedMulti;
        this.ySpeedMod = ySpeedMod;
    }

    public SwimMovementController(CreatureEntity entity, float speedMulti, float ySpeedMod, float yawLimit) {
        super(entity);
        this.entity = entity;
        this.speedMulti = speedMulti;
        this.ySpeedMod = ySpeedMod;
        this.yawLimit = yawLimit;
    }

    public void tick() {
        //if (entity instanceof ISemiAquatic && ((ISemiAquatic) entity).shouldStopMoving()) {
        //    this.entity.setAIMoveSpeed(0.0F);
        //    return;
        //}
        if (this.operation == Action.MOVE_TO && !this.entity.getNavigation().isDone()) {
            double lvt_1_1_ = this.wantedX - this.entity.getX();
            double lvt_3_1_ = this.wantedY - this.entity.getY();
            double lvt_5_1_ = this.wantedZ - this.entity.getZ();
            double lvt_7_1_ = lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_;
            if (lvt_7_1_ < 2.500000277905201E-7D) {
                this.mob.setZza(0.0F);
                return;
            }
            float lvt_9_1_ = (float) (MathHelper.atan2(lvt_5_1_, lvt_1_1_) * 57.2957763671875D) - 90.0F;
            this.entity.yRot = this.rotlerp(this.entity.yRot, lvt_9_1_, yawLimit);
            this.entity.yBodyRot = this.entity.yRot;
            this.entity.yHeadRot = this.entity.yRot;
            float lvt_10_1_ = (float) (this.speedModifier * speedMulti * 3 * this.entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
            if (!this.entity.isInWater()) {
                this.entity.setSpeed(lvt_10_1_ * 0.1F);
                return;
            }

            if (lvt_3_1_ > 0 && entity.horizontalCollision) {
                this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, 0.08F, 0.0D));
            } else {
                this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0.0D, (double) this.entity.getSpeed() * lvt_3_1_ * 0.6D * ySpeedMod, 0.0D));
            }
            this.entity.setSpeed(lvt_10_1_ * 0.02F);
            float lvt_11_1_ = -((float) (MathHelper.atan2(lvt_3_1_, MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_5_1_ * lvt_5_1_)) * 57.2957763671875D));
            lvt_11_1_ = MathHelper.clamp(MathHelper.wrapDegrees(lvt_11_1_), -85.0F, 85.0F);
            this.entity.xRot = this.rotlerp(this.entity.xRot, lvt_11_1_, 5.0F);
            float lvt_12_1_ = MathHelper.cos(this.entity.xRot * 0.017453292F);
            float lvt_13_1_ = MathHelper.sin(this.entity.xRot * 0.017453292F);
            this.entity.zza = lvt_12_1_ * lvt_10_1_;
            this.entity.yya = -lvt_13_1_ * lvt_10_1_;
        } else {
            this.entity.setSpeed(0.0F);
            this.entity.setZza(0.0f);
            this.entity.setXxa(0.0f);
            this.entity.setYya(0.0f);
        }
    }
}