package ca.otterspace.skeletal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.joml.Vector4f;
import net.minecraft.client.model.geom.ModelPart;

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
    public ModelPart.Cube cube = null;
    public Matrix4f transform = new Matrix4f();
    
    public void render(PoseStack matstack, VertexConsumer vbuf, int i, int j, float f, float g, float h, float k) {
        if (!visible)
            return;
        matstack.pushPose();
        matstack.scale(scale.x(), scale.y(), scale.z());
        matstack.mulPoseMatrix(transform);
        matstack.mulPoseMatrix(locrot.getMatrix());
        if (cube != null) {
            cube.compile(matstack.last(), vbuf, i,j,color.x(),color.y(),color.z(),color.w());
        } else {
            for (Bone child : children)
                child.render(matstack, vbuf, i,j,color.x(),color.y(),color.z(),color.w());
        }
        matstack.popPose();
    }
}
