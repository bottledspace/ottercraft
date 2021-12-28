package ca.otterspace.ottercraft;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;

public class GiantOtter extends PathfinderMob {
    public Tail tail = null;
    
    protected GiantOtter(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }
    
    @Override
    public void tick() {
        super.tick();
        if (this.tail != null) {
            this.tail.setPoint(0, (float)this.getX(), (float)this.getY(), (float)this.getZ());
            this.tail.simulate(this.level);
        }
    }
}