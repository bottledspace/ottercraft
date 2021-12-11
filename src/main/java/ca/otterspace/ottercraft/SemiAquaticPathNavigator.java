package ca.otterspace.ottercraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SemiAquaticPathNavigator extends WaterBoundPathNavigation {

    public SemiAquaticPathNavigator(Mob entitylivingIn, Level worldIn) {
        super(entitylivingIn, worldIn);
    }

    @Override
    protected PathFinder createPathFinder(int p_179679_1_) {
        boolean allowBreaching = false;
        this.nodeEvaluator = new SwimNodeEvaluator(allowBreaching);
        return new PathFinder(this.nodeEvaluator, p_179679_1_);
    }

    @Override
    protected boolean canUpdatePath() {
        return true;
    }


    @Override
    protected Vec3 getTempMobPos() {
        return new Vec3(this.mob.getX(), this.mob.getY(0.5D), this.mob.getZ());
    }

    @Override
    protected boolean canMoveDirectly(Vec3 posVec31, Vec3 posVec32) {
        Vec3 vector3d = new Vec3(posVec32.x, posVec32.y + (double)this.mob.getBbHeight() * 0.5D, posVec32.z);
        return this.level.clip(new ClipContext(posVec31, vector3d, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.mob)).getType() == BlockHitResult.Type.MISS;
    }

    @Override
    public boolean isStableDestination(BlockPos pos) {
        return  !this.level.getBlockState(pos.below()).isAir();
    }

    @Override
    public void setCanFloat(boolean canSwim) {
    }
}