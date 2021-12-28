package ca.otterspace.ottercraft;

import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

class Tail {
    final int length;
    protected boolean[] ground;
    protected Vector3f[] lastChain;
    protected Vector3f[] chain;
    protected float[] dists;
    
    class Constraint {
        int i, j;
        float dist;
        boolean canBeFarther;
        float springConst;
        
        Constraint(int i, int j, float dist, float springConst, boolean canBeFarther) {
            this.i = i;
            this.j = j;
            this.dist = dist;
            this.springConst = springConst;
            this.canBeFarther = canBeFarther;
        }
    }
    protected List<Constraint> constraints;
    
    /**
     * Create a chain.
     * @param dists Sequence of distances between points.
     */
    public Tail(float [] dists) {
        this.length = dists.length+1;
        this.dists = dists;
        this.ground = new boolean[length];
        this.lastChain = new Vector3f[length];
        this.chain = new Vector3f[length];
        for (int i = 0; i < length; i++)
            this.ground[i] = false;
        
        this.constraints = new ArrayList<>();
        for (int i = 0; i < length-1; i++)
            this.constraints.add(new Constraint(i,i+1,dists[i]/16f, 0.9f,false));
        float COS_PHI = (float)Math.cos(2*Math.PI/3);
        for (int i = 0; i < length-2; i++) {
            float a = dists[i]/16f;
            float b = dists[i+1]/16f;
            float c = Mth.sqrt(a*a+b*b-2*a*b*COS_PHI);
            this.constraints.add(new Constraint(i, i + 2, c, 0.9f,true));
        }
    }
    
    /**
     * Initialize the chain, with the root at the given location.
     * @param x,y,z The coordinates of the root.
     */
    public void init(float x, float y, float z) {
        chain[0] = new Vector3f(x, y, z);
        lastChain[0] = chain[0].copy();
        for (int i = 1; i < length; i++) {
            chain[i] = chain[i - 1].copy();
            chain[i].add(new Vector3f(0, 0, dists[i-1]/16f));
            lastChain[i] = chain[i].copy();
        }
    }
    
    /**
     * Update a list of bones to reflect the current state of the chain.
     * @param dt Interpolation value between last and next position.
     * @param bones The bones to transform.
     */
    public void apply(float dt, List<ModelPart> bones) {
        float ox = Mth.lerp(dt, lastChain[0].x(), chain[0].x());
        float oy = Mth.lerp(dt, lastChain[0].y(), chain[0].y());
        float oz = Mth.lerp(dt, lastChain[0].z(), chain[0].z());
        for (int i = 0; i < length-1; i++) {
            float x = Mth.lerp(dt, lastChain[i].x(), chain[i].x());
            float y = Mth.lerp(dt, lastChain[i].y(), chain[i].y());
            float z = Mth.lerp(dt, lastChain[i].z(), chain[i].z());
            float dx = Mth.lerp(dt, lastChain[i + 1].x() - lastChain[i].x(),
                    chain[i + 1].x() - chain[i].x());
            float dy = Mth.lerp(dt, lastChain[i + 1].y() - lastChain[i].y(),
                    chain[i + 1].y() - chain[i].y());
            float dz = Mth.lerp(dt, lastChain[i + 1].z() - lastChain[i].z(),
                    chain[i + 1].z() - chain[i].z());
            
            float dangleY = (float)Math.atan2(dz, dx);
            float dangleX = Mth.PI/2.0f - (float)Math.acos(dy/ Math.sqrt(dx*dx+dy*dy+dz*dz));
            
            bones.get(i).setPos(-(x-ox)*16f,24-(y-oy)*16f,(z-oz)*16f);
            bones.get(i).setRotation(dangleX, dangleY - Mth.PI/2f, 0);
        }
    }
    
    /**
     * Enforce a distance constraint between two points on the chain.
     * @param j Index to the first point.
     * @param i Index to the second point.
     * @param strict Enforce constraint even if distance is already greater.
     */
    protected void constrainDist(int j, int i, float dist, float springConst, boolean strict) {
        Vector3f delta = chain[i].copy();
        delta.sub(chain[j]);
        
        float curDist = (delta.dot(delta));
        if (curDist > dist*dist && !strict)
            return;
        delta.mul(springConst *(dist * Mth.fastInvSqrt(curDist) - 1.0f) / 2.0f);
        chain[i].add(delta);
        if (j != 0)
            chain[j].sub(delta);
    }
    
    /**
     * Enforce an angular constraint between three points.
     * @param j Index to first point.
     * @param i Index to middle point.
     * @param k Index to last point.
     * @param angle Desired interior angle.
     */
    /*protected void constrainAngle(int j, int i, int k, float angle) {
        Vector3f delta1 = chain[j].copy();
        delta1.sub(chain[i]);

        Vector3f delta2 = chain[k].copy();
        delta2.sub(chain[i]);

        float a = dists[i-1]/16f;
        float b = dists[i]/16f;
        float c = Mth.sqrt(a*a+b*b-2*a*b*(float)Math.cos(angle));

        float dtheta = delta1.dot(delta2)/Mth.sqrt(delta1.dot(delta1)*delta2.dot(delta2)) - angle;
        //if (dtheta < 0f) {
            constrainDist(j,k,c,false);
        //}
    }*/
    
    /**
     * Enforce collision between level and a point.
     * @param level Level to collide with.
     * @param i Index to point.
     */
    protected void collide(Level level, int i) {
        BlockPos bp = new BlockPos(chain[i].x(), chain[i].y(), chain[i].z());
        if (!level.getBlockState(bp).isAir()) {
            ground[i] = true;
            
            float diff = (bp.getY() + 1) - chain[i].y();
            lastChain[i].set(lastChain[i].x(), (bp.getY() + 1) - diff, lastChain[i].z());
            chain[i].set(chain[i].x(), bp.getY() + 1, chain[i].z());
        } else
            ground[i] = false;
    }
    
    /**
     * Integrate and add forces to a point.
     * @param i Index to point.
     */
    protected void update(int i) {
        final Vector3f gravity = new Vector3f(0, -0.015f, 0);
        final float airVelocity = 0.97f;
        final float groundVelocity = 0.6f;
        
        Vector3f velocity = chain[i].copy();
        velocity.sub(lastChain[i]);
        if (ground[i] && i != 0) {
            Vector3f diff = chain[i].copy();
            diff.sub(chain[0]);
            diff.normalize();
            diff.mul(0.1f);
            velocity.add(diff);
        }
        velocity.mul(ground[i] ? groundVelocity : airVelocity);
        
        lastChain[i] = chain[i].copy();
        chain[i].add(gravity);
        chain[i].add(velocity);
    }
    
    /**
     * Set the position of a point. Stores current position as its new last position.
     * @param i Index to point.
     * @param x,y,z New location of point.
     */
    public void setPoint(int i, float x, float y, float z) {
        lastChain[i] = chain[i].copy();
        chain[i].set(x, y, z);
    }
    
    public void simulate(Level level) {
        for (int i = 1; i < length; i++) {
            update(i);
            collide(level, i);
        }
        
        for (int k = 0; k < 50; k++) {
            for (Constraint constraint : constraints)
                constrainDist(constraint.i, constraint.j, constraint.dist, constraint.springConst, !constraint.canBeFarther);
        }
    }
}
