package ca.otterspace.skeletal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Cube {
    private final Polygon[] polygons;
    public final float minX;
    public final float minY;
    public final float minZ;
    public final float maxX;
    public final float maxY;
    public final float maxZ;

    public Cube(int $$0, int $$1, float $$2, float $$3, float $$4, float $$5, float $$6, float $$7, float $$8, float $$9, float $$10, boolean $$11, float $$12, float $$13) {
        this.minX = $$2;
        this.minY = $$3;
        this.minZ = $$4;
        this.maxX = $$2 + $$5;
        this.maxY = $$3 + $$6;
        this.maxZ = $$4 + $$7;
        this.polygons = new Polygon[6];
        float $$14 = $$2 + $$5;
        float $$15 = $$3 + $$6;
        float $$16 = $$4 + $$7;
        $$2 -= $$8;
        $$3 -= $$9;
        $$4 -= $$10;
        $$14 += $$8;
        $$15 += $$9;
        $$16 += $$10;
        if ($$11) {
            float $$17 = $$14;
            $$14 = $$2;
            $$2 = $$17;
        }

        Vertex $$18 = new Vertex($$2, $$3, $$4, 0.0F, 0.0F);
        Vertex $$19 = new Vertex($$14, $$3, $$4, 0.0F, 8.0F);
        Vertex $$20 = new Vertex($$14, $$15, $$4, 8.0F, 8.0F);
        Vertex $$21 = new Vertex($$2, $$15, $$4, 8.0F, 0.0F);
        Vertex $$22 = new Vertex($$2, $$3, $$16, 0.0F, 0.0F);
        Vertex $$23 = new Vertex($$14, $$3, $$16, 0.0F, 8.0F);
        Vertex $$24 = new Vertex($$14, $$15, $$16, 8.0F, 8.0F);
        Vertex $$25 = new Vertex($$2, $$15, $$16, 8.0F, 0.0F);
        float $$26 = (float)$$0;
        float $$27 = (float)$$0 + $$7;
        float $$28 = (float)$$0 + $$7 + $$5;
        float $$29 = (float)$$0 + $$7 + $$5 + $$5;
        float $$30 = (float)$$0 + $$7 + $$5 + $$7;
        float $$31 = (float)$$0 + $$7 + $$5 + $$7 + $$5;
        float $$32 = (float)$$1;
        float $$33 = (float)$$1 + $$7;
        float $$34 = (float)$$1 + $$7 + $$6;
        this.polygons[2] = new Polygon(new Vertex[]{$$23, $$22, $$18, $$19}, $$27, $$32, $$28, $$33, $$12, $$13, $$11, Direction.DOWN);
        this.polygons[3] = new Polygon(new Vertex[]{$$20, $$21, $$25, $$24}, $$28, $$33, $$29, $$32, $$12, $$13, $$11, Direction.UP);
        this.polygons[1] = new Polygon(new Vertex[]{$$18, $$22, $$25, $$21}, $$26, $$33, $$27, $$34, $$12, $$13, $$11, Direction.WEST);
        this.polygons[4] = new Polygon(new Vertex[]{$$19, $$18, $$21, $$20}, $$27, $$33, $$28, $$34, $$12, $$13, $$11, Direction.NORTH);
        this.polygons[0] = new Polygon(new Vertex[]{$$23, $$19, $$20, $$24}, $$28, $$33, $$30, $$34, $$12, $$13, $$11, Direction.EAST);
        this.polygons[5] = new Polygon(new Vertex[]{$$22, $$23, $$24, $$25}, $$30, $$33, $$31, $$34, $$12, $$13, $$11, Direction.SOUTH);
    }

    public void compile(MatrixStack.Entry $$0, IVertexBuilder $$1, int $$2, int $$3, float $$4, float $$5, float $$6, float $$7) {
        Matrix4f $$8 = $$0.pose();
        Matrix3f $$9 = $$0.normal();
        Polygon[] var11 = this.polygons;
        int var12 = var11.length;

        for(int var13 = 0; var13 < var12; ++var13) {
            Polygon $$10 = var11[var13];
            Vector3f $$11 = $$10.normal.copy();
            $$11.transform($$9);
            float $$12 = $$11.x();
            float $$13 = $$11.y();
            float $$14 = $$11.z();
            Vertex[] var19 = $$10.vertices;
            int var20 = var19.length;

            for(int var21 = 0; var21 < var20; ++var21) {
                Vertex $$15 = var19[var21];
                float $$16 = $$15.pos.x() / 16.0F;
                float $$17 = $$15.pos.y() / 16.0F;
                float $$18 = $$15.pos.z() / 16.0F;
                Vector4f $$19 = new Vector4f($$16, $$17, $$18, 1.0F);
                $$19.transform($$8);
                $$1.vertex($$19.x(), $$19.y(), $$19.z(), $$4, $$5, $$6, $$7, $$15.u, $$15.v, $$3, $$2, $$12, $$13, $$14);
            }
        }

    }
}

@OnlyIn(Dist.CLIENT)
class Vertex {
    public final Vector3f pos;
    public final float u;
    public final float v;

    public Vertex(float $$0, float $$1, float $$2, float $$3, float $$4) {
        this(new Vector3f($$0, $$1, $$2), $$3, $$4);
    }

    public Vertex remap(float $$0, float $$1) {
        return new Vertex(this.pos, $$0, $$1);
    }

    public Vertex(Vector3f $$0, float $$1, float $$2) {
        this.pos = $$0;
        this.u = $$1;
        this.v = $$2;
    }
}

@OnlyIn(Dist.CLIENT)
class Polygon {
    public final Vertex[] vertices;
    public final Vector3f normal;

    public Polygon(Vertex[] $$0, float $$1, float $$2, float $$3, float $$4, float $$5, float $$6, boolean $$7, Direction $$8) {
        this.vertices = $$0;
        float $$9 = 0.0F / $$5;
        float $$10 = 0.0F / $$6;
        $$0[0] = $$0[0].remap($$3 / $$5 - $$9, $$2 / $$6 + $$10);
        $$0[1] = $$0[1].remap($$1 / $$5 + $$9, $$2 / $$6 + $$10);
        $$0[2] = $$0[2].remap($$1 / $$5 + $$9, $$4 / $$6 - $$10);
        $$0[3] = $$0[3].remap($$3 / $$5 - $$9, $$4 / $$6 - $$10);
        if ($$7) {
            int $$11 = $$0.length;

            for(int $$12 = 0; $$12 < $$11 / 2; ++$$12) {
                Vertex $$13 = $$0[$$12];
                $$0[$$12] = $$0[$$11 - 1 - $$12];
                $$0[$$11 - 1 - $$12] = $$13;
            }
        }

        this.normal = $$8.step();
        if ($$7) {
            this.normal.mul(-1.0F, 1.0F, 1.0F);
        }

    }
}