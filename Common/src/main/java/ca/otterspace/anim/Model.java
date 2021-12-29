package ca.otterspace.anim;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;

import java.util.Collection;
import java.util.Map;

public class Model {
    //Multimap<String, String> hierarchy;
    Map<String, ModelPart> partMap = Maps.newHashMap();
    Pose idlePose = new Pose();
    
    public ModelPart getPart(String name) {
        return partMap.get(name);
    }
    
    public void applyPose(Pose pose) {
        for (Map.Entry<String, PartPose> partEntry : idlePose.poseMap.entrySet()) {
            ModelPart part = partMap.get(partEntry.getKey());
            part.loadPose(partEntry.getValue());
            PartPose partPose = pose.poseMap.get(partEntry.getKey());
            if (partPose == null)
                continue;
            part.x += partPose.x; part.y += partPose.y; part.z += partPose.z;
            part.xRot += partPose.xRot; part.yRot += partPose.yRot; part.zRot += partPose.zRot;
        }
    }
    
    public void render(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        partMap.get("_root").render(poseStack, vertexConsumer, i, j, f, g, h, k);
    }
}