package ca.otterspace.skeletal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.vertex.IVertexConsumer;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class Bone {
    public boolean visible = true;
    public Vector4f color = new Vector4f(1,1,1,1);
    public Vector3f scale = new Vector3f(1,1,1);
    public LocRot locrot = new LocRot();
    public String name = null;
    public Bone parent = null;
    public List<Bone> children = new ArrayList<>();
    public Cube cube = null;
    public Matrix4f transform = Matrix4f.createScaleMatrix(1,1,1);
    
    public void render(MatrixStack matstack, IVertexBuilder vbuf, int i, int j, float f, float g, float h, float k) {
        if (!visible)
            return;
        matstack.pushPose();
        matstack.scale(scale.x(), scale.y(), scale.z());
        matstack.last().pose().multiply(transform);
        matstack.last().pose().multiply(locrot.getMatrix());
        if (cube != null) {
            cube.compile(matstack.last(), vbuf, i,j,color.x(),color.y(),color.z(),color.w());
        } else {
            for (Bone child : children)
                child.render(matstack, vbuf, i,j,color.x(),color.y(),color.z(),color.w());
        }
        matstack.popPose();
    }
}
