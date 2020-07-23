package ca.otterspace.ottercraft;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.world.World;

public class OtterEntity extends WolfEntity {
    public final static String NAME = "otter";
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
}
