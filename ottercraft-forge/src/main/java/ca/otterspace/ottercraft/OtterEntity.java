package ca.otterspace.ottercraft;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.world.World;

public class OtterEntity extends WolfEntity {
    public enum Animation {
        IDLE,
        RUNNING
    };
    public Animation animation;
    public int frame;
    public OtterEntity(EntityType<? extends WolfEntity> type, World worldIn) {
        super(type, worldIn);
        this.animation = Animation.IDLE;
        this.frame = 0;
    }
    public void livingTick() {
        super.livingTick();
        double dx = Math.abs(this.getMotion().getX());
        double dz = Math.abs(this.getMotion().getZ());
        this.animation = (dx*dx+dz*dz <= 0.01)? Animation.IDLE : Animation.RUNNING;
        this.frame = (this.frame+1) % 20;
    }
}
