package ca.otterspace.ottercraft.goals;


import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class GoalPlayWithItems extends Goal {
    protected int cooldown;
    protected Mob mob;

    public GoalPlayWithItems(Mob mob) {
        this.mob = mob;
    }

    public boolean canUse() {
        if (this.cooldown > this.mob.tickCount) {
            return false;
        } else {
            List<ItemEntity> list = this.mob.level().getEntitiesOfClass(ItemEntity.class, this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Dolphin.ALLOWED_ITEMS);
            return !list.isEmpty() || !this.mob.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
        }
    }

    public void start() {
        List<ItemEntity> list = this.mob.level().getEntitiesOfClass(ItemEntity.class, this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Dolphin.ALLOWED_ITEMS);
        if (!list.isEmpty()) {
            this.mob.getNavigation().moveTo(list.get(0), (double)1.2F);
            //this.mob.playSound(SoundEvents.DOLPHIN_PLAY, 1.0F, 1.0F);
        }

        this.cooldown = 0;
    }

    public void stop() {
        ItemStack itemstack = this.mob.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!itemstack.isEmpty()) {
            this.drop(itemstack);
            this.mob.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            this.cooldown = this.mob.tickCount + this.mob.getRandom().nextInt(100);
        }

    }

    public void tick() {
        List<ItemEntity> list = this.mob.level().getEntitiesOfClass(ItemEntity.class, this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Dolphin.ALLOWED_ITEMS);
        ItemStack itemstack = this.mob.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!itemstack.isEmpty()) {
            this.drop(itemstack);
            this.mob.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        } else if (!list.isEmpty()) {
            this.mob.getNavigation().moveTo(list.get(0), (double)1.2F);
        }

    }

    private void drop(ItemStack p_220810_1_) {
        if (!p_220810_1_.isEmpty()) {
            double d0 = this.mob.getEyeY() - (double)0.3F;
            ItemEntity itementity = new ItemEntity(this.mob.level(), this.mob.getX(), d0, this.mob.getZ(), p_220810_1_);
            itementity.setPickUpDelay(40);
            itementity.setThrower(this.mob);
            float f = 0.3F;
            float f1 = this.mob.getRandom().nextFloat() * ((float)Math.PI * 2F);
            float f2 = 0.02F * this.mob.getRandom().nextFloat();
            itementity.setDeltaMovement((double)(0.3F * -Mth.sin(this.mob.getYRot() * ((float)Math.PI / 180F))
                    * Mth.cos(this.mob.getXRot() * ((float)Math.PI / 180F)) + Mth.cos(f1) * f2),
                    (double)(0.3F * Mth.sin(this.mob.getXRot() * ((float)Math.PI / 180F)) * 1.5F),
                    (double)(0.3F * Mth.cos(this.mob.getYRot() * ((float)Math.PI / 180F))
                            * Mth.cos(this.mob.getXRot() * ((float)Math.PI / 180F)) + Mth.sin(f1) * f2));
            this.mob.level().addFreshEntity(itementity);
        }
    }
}