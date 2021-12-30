package ca.otterspace.ottercraft;

import ca.otterspace.skeletal.Animations;
import ca.otterspace.skeletal.Bone;
import ca.otterspace.skeletal.Model;
import ca.otterspace.skeletal.Pose;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.mojang.math.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class OtterModel extends EntityModel<Otter> {
    static final Animations animations = Animations.loadAnimations(new ResourceLocation(Ottercraft.MODID, "animations/otter.animation.json"));
    static final Model model = Model.loadGeometry(new ResourceLocation(Ottercraft.MODID, "geo/otter.geo.json"));
    
    Pose currentPose = new Pose();
    
    protected void animateTail(float angleX, float angleY) {
        Vector3f rotation = new Vector3f(angleX, angleY, 0);
        for (String name : ImmutableList.of("tail", "tail1", "tail2", "tail3", "tail4", "tail5")) {
            currentPose.getOrCreateLocal(name).rotate(rotation);
            rotation.mul(0.5f);
        }
    }
    
    @Override
    public void setupAnim(Otter entity, float f, float g, float animationProgress, float headYaw, float headPitch) {
        // This must be done first or it will overwrite our tweaks!
        currentPose = entity.animationController.apply(model, animations);
    
        if (entity.isBaby())
            model.getBone("head").scale.mul(1.25f,1.25f,1.25f);
        else
            model.getBone("head").scale.set(1f,1f,1f);
        
        float[] color = entity.getCollarColor().getTextureDiffuseColors();
    
        model.getBone("harness").visible = entity.isTame();
        model.getBone("harness").getChild(0).color = new Vector4f(color[0], color[1], color[2], 1f);
        model.getBone("tail").visible = !entity.isPassenger();
    
        if (Minecraft.getInstance().isPaused())
            return;
    
        headYaw *= Mth.DEG_TO_RAD;
        headPitch *= Mth.DEG_TO_RAD;
        headYaw = (float) Mth.clamp(headYaw, -Math.PI/4,Math.PI/4);
    
        if (!entity.animationController.getAnimation().equals("animation.otter.beg")) {
            currentPose.getOrCreateLocal("head").rotate(new Vector3f(headPitch, headYaw, 0));
        }
        
        if (entity.isInWater()) {
            // Tilt body up and down visually when in water
            double dx = entity.getDeltaMovement().x;
            double dz = entity.getDeltaMovement().z;
            float angle = (float) (Mth.atan2(entity.getDeltaMovement().y, Mth.sqrt((float)(dx * dx + dz * dz))));
            angle = (float) Mth.clamp(angle, -Math.PI / 8.0, Math.PI / 8.0);
            
            currentPose.getOrCreateLocal("root").rotate(new Vector3f(-angle,0,0));
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
        if (this.young)
            stack.scale(0.3f, 0.3f, 0.3f);
        else
            stack.scale(0.6f, 0.6f, 0.6f);
        model.render(currentPose, stack, vertexConsumer, i,j,f,g,h,k);
        stack.popPose();
    }
}