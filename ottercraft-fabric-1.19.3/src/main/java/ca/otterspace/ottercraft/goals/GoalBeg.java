package ca.otterspace.ottercraft.goals;

import ca.otterspace.ottercraft.IBegger;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class GoalBeg<E extends TamableAnimal & IBegger>  extends Goal {
    private final E otter;
    private Player player;
    private final Level level;
    private final float lookDistance;
    private int lookTime;
    private final TargetingConditions begTargeting;

    public GoalBeg(E p_i1617_1_, float p_i1617_2_) {
        this.otter = p_i1617_1_;
        this.level = p_i1617_1_.level;
        this.lookDistance = p_i1617_2_;
        this.begTargeting = TargetingConditions.forNonCombat().range((double)p_i1617_2_);
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public boolean canUse() {
        this.player = this.level.getNearestPlayer(this.begTargeting, this.otter);
        return this.player == null ? false : this.playerHoldingInteresting(this.player);
    }

    public boolean canContinueToUse() {
        if (!this.player.isAlive()) {
            return false;
        } else if (this.otter.distanceToSqr(this.player) > (double)(this.lookDistance * this.lookDistance)) {
            return false;
        } else {
            return this.lookTime > 0 && this.playerHoldingInteresting(this.player);
        }
    }

    public void start() {
        this.otter.setBegging(true);
        this.lookTime = 40 + this.otter.getRandom().nextInt(40);
    }

    public void stop() {
        this.otter.setBegging(false);
        this.otter.getNavigation().stop();
        this.player = null;
    }

    public void tick() {
        this.otter.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float)this.otter.getMaxHeadXRot());

        if (this.otter.distanceToSqr(this.player) < 6.25D) {
            this.otter.getNavigation().stop();
            --this.lookTime;
        } else {
            this.otter.getNavigation().moveTo(this.player, 1.0f);
        }
    }

    private boolean playerHoldingInteresting(Player p_75382_1_) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack itemstack = p_75382_1_.getItemInHand(hand);
            if (this.otter.isFood(itemstack)) {
                return true;
            }
        }

        return false;
    }
}