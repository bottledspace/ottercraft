package ca.otterspace.ottercraft;

import ca.otterspace.skeletal.Animations;
import ca.otterspace.skeletal.Bone;
import ca.otterspace.skeletal.Model;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.mojang.math.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class OtterModel extends EntityModel<Otter> {
    final Animations animations = Animations.loadAnimations(new ResourceLocation(Ottercraft.MODID, "animations/otter.animation.json"));
    final Model model = Model.loadGeometry(new ResourceLocation(Ottercraft.MODID, "geo/otter.geo.json"));
    
    final Bone root = model.getBone("root");
    final Bone head = model.getBone("head");
    final Bone harness = model.getBone("harness");
    final Bone tail = model.getBone("tail");
    
    protected void animateTail(float angleX, float angleY) {
        Vector3f rotation = new Vector3f(angleX, angleY, 0);
        for (String name : ImmutableList.of("tail", "tail1", "tail2", "tail3", "tail4", "tail5")) {
            Bone tailBone = model.getBone(name);
            if (tailBone != null)
                tailBone.locrot.rotation.mul(Quaternion.fromXYZ(rotation));
            rotation.mul(0.5f);
        }
    }
    
    @Override
    public void prepareMobModel(Otter otter, float $$1, float $$2, float $$3) {
        // Tint harness (tamed otters only)
        harness.visible = otter.isTame();
        float[] color = otter.getCollarColor().getTextureDiffuseColors();
        harness.children.get(0).color = new Vector4f(color[0], color[1], color[2], 1f);
    }
    
    @Override
    public void setupAnim(Otter entity, float f, float g, float animationProgress, float headYaw, float headPitch) {
        // Set current animation frame
        model.applyPose(entity.animationController.apply(animations));
        
        if (Minecraft.getInstance().isPaused())
            return;
    
        headYaw *= Mth.DEG_TO_RAD;
        headPitch *= Mth.DEG_TO_RAD;
        headYaw = (float) Mth.clamp(headYaw, -Math.PI/4,Math.PI/4);
        if (!entity.animationController.getAnimation().equals("animation.otter.beg")) {
            head.locrot.rotation.mul(Quaternion.fromXYZ(headPitch, headYaw, 0));
        }
    
        // Tilt body up and down visually when in water
        if (entity.isInWater()) {
            double dx = entity.getDeltaMovement().x;
            double dz = entity.getDeltaMovement().z;
            float angle = (float) (Mth.atan2(entity.getDeltaMovement().y, Mth.sqrt((float)(dx * dx + dz * dz))));
            angle = (float) Mth.clamp(angle, -Math.PI / 8.0, Math.PI / 8.0);
            
            root.locrot.rotation.mul(Quaternion.fromXYZ(-angle,0,0));
        }
    
        // Wag tail
        float wagAmplitude;
        if (entity.isBegging())
            wagAmplitude = 0.4f;
        else if (entity.isInWater() && entity.getDeltaMovement().lengthSqr() > 0.001f)
            wagAmplitude = 0.3f;
        else
            wagAmplitude = 0.05f;
        
        this.animateTail(0, Mth.cos(animationProgress * 0.3331f) * wagAmplitude);
    }
    
    @Override
    public void renderToBuffer(PoseStack stack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        stack.pushPose();
        stack.translate(0,1.5,0);  // Cancel out the translation added by Minecraft
    
        // Give baby otters larger heads, smaller bodies.
        if (this.young) {
            stack.scale(0.3f, 0.3f, 0.3f);
            head.scale.set(1.25f, 1.25f, 1.25f);
        } else {
            stack.scale(0.6f, 0.6f, 0.6f);
            head.scale.set(1f, 1f, 1f);
        }
        
        // Avoid tail clipping through boat
        tail.visible = this.young || !this.riding;
        
        model.render(stack, vertexConsumer, i,j,f,g,h,k);
        stack.popPose();
    }
}