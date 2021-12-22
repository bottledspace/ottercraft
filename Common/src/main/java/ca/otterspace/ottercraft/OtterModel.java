package ca.otterspace.ottercraft;

import ca.otterspace.anim.Animations;
import ca.otterspace.anim.Geometry;
import ca.otterspace.anim.Model;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class OtterModel extends EntityModel<Otter> {
    private static final Geometry geometry = Geometry.loadGeometry(new ResourceLocation(Ottercraft.MODID, "geo/otter.geo.json"));
    private static final Animations animations = Animations.loadAnimations(new ResourceLocation(Ottercraft.MODID, "animations/otter.animation.json"));

    Model model;
    ModelPart root;
    ImmutableList<ModelPart> tail;
    ModelPart harness;
    ModelPart head;
    
    public OtterModel(ModelPart root) {
        this.model = geometry.compileModel(root);
        this.root = model.getPart("root");
        this.tail = ImmutableList.of(
                model.getPart("tail"),
                model.getPart("tail2"),
                model.getPart("tail3"),
                model.getPart("tail4"),
                model.getPart("tail5"));
        this.harness = model.getPart("harness");
        this.head = model.getPart("head");
    }
    
    protected void animateTail(float angleX, float angleY) {
        for (ModelPart part : tail) {
            part.yRot += angleY;
            part.xRot += angleX;
            
            angleX /= 2.0f;
            angleY /= 2.0f;
        }
    }
    
    @Override
    public void setupAnim(Otter entity, float f, float g, float animationProgress, float headYaw, float headPitch) {
        // This must be done first or it will overwrite our tweaks!
        entity.animationController.apply(model, animations);
        
        harness.visible = entity.isTame();
        tail.get(0).visible = !entity.isPassenger();
    
        if (Minecraft.getInstance().isPaused())
            return;
    
        headYaw *= Mth.DEG_TO_RAD;
        headPitch *= Mth.DEG_TO_RAD;
        headYaw = (float) Mth.clamp(headYaw, -Math.PI/4,Math.PI/4);
    
        if (entity.animationController.getAnimation().equals("animation.otter.beg")) {
            head.xRot = (float)Math.PI/2f + headPitch;
            head.zRot = headYaw;
        } else {
            head.xRot = headPitch;
            head.yRot = headYaw;
        }
        
        if (entity.isInWater()) {
            // Tilt body up and down visually when in water
            double dx = entity.getDeltaMovement().x;
            double dz = entity.getDeltaMovement().z;
            float angle = (float) (Mth.atan2(entity.getDeltaMovement().y, Mth.sqrt((float)(dx * dx + dz * dz))));
            angle = (float) Mth.clamp(angle, -Math.PI / 4.0, Math.PI / 4.0);
            root.xRot = -angle;
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
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        model.render(poseStack, vertexConsumer, i, j, f, g, h, k);
    }
    
    public static LayerDefinition getTexturedModelData() {
        return geometry.createLayerDefinition();
    }
    
}