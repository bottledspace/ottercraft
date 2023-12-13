package ca.otterspace.ottercraft.goals;


import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class GoalSwimWithPlayer extends Goal {
    private static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = TargetingConditions.forNonCombat().range(10.0D);

    private final Mob mob;
    private final double speedModifier;
    private Player player;

    public GoalSwimWithPlayer(Mob mob, double speedModifier) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        this.player = this.mob.level().getNearestPlayer(SWIM_WITH_PLAYER_TARGETING, this.mob);
        if (this.player == null) {
            return false;
        } else {
            return this.player.isSwimming() && this.mob.getTarget() != this.player;
        }
    }

    public boolean canContinueToUse() {
        return this.player != null && this.player.isSwimming() && this.mob.distanceToSqr(this.player) < 256.0D;
    }

    public void start() {
        //this.player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100));
    }

    public void stop() {
        this.player = null;
        this.mob.getNavigation().stop();
    }

    public void tick() {
        this.mob.getLookControl().setLookAt(this.player, (float)(this.mob.getMaxHeadYRot() + 20), (float)this.mob.getMaxHeadXRot());
        if (this.mob.distanceToSqr(this.player) < 6.25D) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.player, this.speedModifier);
        }

        if (this.player.isSwimming() && this.player.level().random.nextInt(6) == 0) {
            //this.player.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100));
        }

    }
}
