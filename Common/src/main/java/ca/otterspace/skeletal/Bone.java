package ca.otterspace.skeletal;

import ca.otterspace.ottercraft.Ottercraft;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.mojang.math.Vector4f;
import net.minecraft.client.model.geom.ModelPart;

import java.util.ArrayList;
import java.util.List;

public class Bone {
    public boolean visible = true;
    public Vector4f color = new Vector4f(1,1,1,1);
    public Vector3f scale = new Vector3f(1,1,1);
    String name = null;
    Bone parent = null;
    List<Bone> children = new ArrayList<>();
    ModelPart.Cube cube = null;
    Matrix4f transform = Matrix4f.createScaleMatrix(1,1,1);
    
    public Bone getChild(int i) {
        return children.get(i);
    }
    
    public void render(Pose pose, PoseStack matstack, VertexConsumer vbuf, int i, int j, float f, float g, float h, float k) {
        if (!visible)
            return;
        Pose.LocRotScale local = pose.getLocal(name);
        matstack.pushPose();
        matstack.scale(scale.x(), scale.y(), scale.z());
        matstack.mulPoseMatrix(transform);
        if (local != null)
            matstack.mulPoseMatrix(local.getMatrix());
        if (cube != null) {
            cube.compile(matstack.last(), vbuf, i,j,color.x(),color.y(),color.z(),color.w());
        } else {
            for (Bone child : children)
                child.render(pose, matstack, vbuf, i,j,color.x(),color.y(),color.z(),color.w());
        }
        matstack.popPose();
    }
}
