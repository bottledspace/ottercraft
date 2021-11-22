package ca.otterspace.ottermod.goals;

import ca.otterspace.ottermod.EntityOtter;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.EnumSet;

public class GoalBeg extends Goal {
    private final EntityOtter otter;
    private PlayerEntity player;
    private final World level;
    private final float lookDistance;
    private int lookTime;
    private final EntityPredicate begTargeting;

    public GoalBeg(EntityOtter p_i1617_1_, float p_i1617_2_) {
        this.otter = p_i1617_1_;
        this.level = p_i1617_1_.level;
        this.lookDistance = p_i1617_2_;
        this.begTargeting = (new EntityPredicate()).range((double)p_i1617_2_)
                .allowInvulnerable().allowSameTeam().allowNonAttackable();
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
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
        this.player = null;
    }

    public void tick() {
        this.otter.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float)this.otter.getMaxHeadXRot());
        --this.lookTime;
    }

    private boolean playerHoldingInteresting(PlayerEntity p_75382_1_) {
        for (Hand hand : Hand.values()) {
            ItemStack itemstack = p_75382_1_.getItemInHand(hand);
            if (this.otter.isFood(itemstack)) {
                return true;
            }
        }

        return false;
    }
}