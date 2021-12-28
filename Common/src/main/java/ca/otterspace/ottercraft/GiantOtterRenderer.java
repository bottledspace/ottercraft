package ca.otterspace.ottercraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GiantOtterRenderer extends MobRenderer<GiantOtter, GiantOtterModel> {
    public GiantOtterRenderer(EntityRendererProvider.Context context) {
        super(context, new GiantOtterModel(context.bakeLayer(Ottercraft.GIANT_OTTER_LAYER)), 0.5f);
    }
    
    @Override
    public ResourceLocation getTextureLocation(GiantOtter entity) {
        return new ResourceLocation(Ottercraft.MODID, "textures/entity/otter.png");
    }
    
    @Override
    public void render(GiantOtter mob, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        super.render(mob, f, g, poseStack, multiBufferSource, i);
    }
    
    @Override
    protected void setupRotations(GiantOtter livingEntity, PoseStack poseStack, float f, float g, float h) {
    }
}
