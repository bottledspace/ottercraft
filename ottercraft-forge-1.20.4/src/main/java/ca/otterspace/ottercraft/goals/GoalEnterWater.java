package ca.otterspace.ottercraft.goals;

import ca.otterspace.ottercraft.ISemiAquatic;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.Random;

// Modified from Alex's Mobs
public class GoalEnterWater extends Goal {
    private final Mob creature;
    private BlockPos targetPos;
    private int executionChance = 30;

    public GoalEnterWater(Mob creature) {
        this.creature = creature;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.creature.onGround() && !this.creature.level().getFluidState(this.creature.blockPosition()).is(FluidTags.WATER)) {
            if (this.creature instanceof ISemiAquatic && ((ISemiAquatic) this.creature).shouldEnterWater()
                    && (this.creature.getTarget() != null || this.creature.getRandom().nextInt(executionChance) == 0)) {
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
    }

    @Override
    public boolean canContinueToUse() {
        if(this.creature instanceof ISemiAquatic && !((ISemiAquatic) this.creature).shouldEnterWater()) {
            this.creature.getNavigation().stop();
            return false;
        }
        return !this.creature.getNavigation().isDone() && targetPos != null
                && !this.creature.level().getFluidState(this.creature.blockPosition()).is(FluidTags.WATER);
    }

    public BlockPos generateTarget() {
        BlockPos blockpos = null;
        Random random = new Random();
        int range = 14;
        for (int i = 0; i < 15; i++) {
            BlockPos blockpos1 = this.creature.blockPosition().offset(random.nextInt(range) - range/2, 3, random.nextInt(range) - range/2);
            while (this.creature.level().isEmptyBlock(blockpos1) && blockpos1.getY() > 1) {
                blockpos1 = blockpos1.below();
            }
            if(this.creature.level().getFluidState(blockpos1).is(FluidTags.WATER)){
                blockpos = blockpos1;
            }
        }
        return blockpos;
    }
}