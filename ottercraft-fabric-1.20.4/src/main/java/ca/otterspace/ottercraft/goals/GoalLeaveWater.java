package ca.otterspace.ottercraft.goals;

import ca.otterspace.ottercraft.ISemiAquatic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

// Modified from Alex's Mobs
public class GoalLeaveWater extends Goal {
    private final PathfinderMob creature;
    private BlockPos targetPos;
    private int executionChance = 30;

    public GoalLeaveWater(PathfinderMob creature) {
        this.creature = creature;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        if (this.creature.isInWater()//this.creature.level.getFluidState(this.creature.blockPosition()).is(FluidTags.WATER)
                && this.creature.getRandom().nextInt(executionChance) == 0) {
            if (this.creature instanceof ISemiAquatic && ((ISemiAquatic) this.creature).shouldLeaveWater()) {
                targetPos = generateTarget();
                return targetPos != null;
            }
        }
        return false;
    }

    @Override
    public void start() {
        if (targetPos != null) {
            this.creature.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1D);
        }
    }

    @Override
    public void tick() {
        if (targetPos != null) {
            this.creature.getNavigation().moveTo(targetPos.getX(), targetPos.getY(), targetPos.getZ(), 1D);
        }
        if (this.creature.horizontalCollision && this.creature.isInWater()) {
            float f1 = creature.getYRot() * ((float)Math.PI / 180F);
            creature.setDeltaMovement(creature.getDeltaMovement().add((double)(-Mth.sin(f1) * 0.2F), 0.2D, (double)(Mth.cos(f1) * 0.2F)));

        }
    }

    @Override
    public boolean canContinueToUse() {
        if(this.creature instanceof ISemiAquatic && !((ISemiAquatic) this.creature).shouldLeaveWater()) {
            this.creature.getNavigation().stop();
            return false;
        }
        return !this.creature.getNavigation().isDone() && targetPos != null
                && !this.creature.isInWater();
        //!this.creature.level.getFluidState(targetPos).is(FluidTags.WATER);
    }

    public BlockPos generateTarget() {
        Vec3 vector3d = LandRandomPos.getPos(this.creature, 23, 7);
        int tries = 0;
        while(vector3d != null && tries < 8){
            boolean waterDetected = false;
            for(BlockPos blockpos1 : BlockPos.betweenClosed(
                    Mth.floor(vector3d.x - 2.0D), Mth.floor(vector3d.y - 1.0D),
                    Mth.floor(vector3d.z - 2.0D), Mth.floor(vector3d.x + 2.0D),
                    Mth.floor(vector3d.y), Mth.floor(vector3d.z + 2.0D))) {
                if (this.creature.level().getFluidState(blockpos1).is(FluidTags.WATER)) {
                    waterDetected = true;
                    break;
                }
            }
            if(waterDetected){
                vector3d = LandRandomPos.getPos(this.creature, 23, 7);
            }else{
                return new BlockPos((int)vector3d.x, (int)vector3d.y, (int)vector3d.z);
            }
            tries++;
        }
        return null;
    }
}