package ca.otterspace.ottercraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class OtterHarnessLayer extends RenderLayer<Otter,OtterModel> {
    static final ResourceLocation OTTER_HARNESS_LOCATION = new ResourceLocation(Ottercraft.MODID, "textures/entity/otter_harness.png");
    
    public OtterHarnessLayer(RenderLayerParent<Otter, OtterModel> $$0) {
        super($$0);
    }
    
    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Otter otter, float v, float v1, float v2, float v3, float v4, float v5) {
        if (otter.isTame() && !otter.isInvisible()) {
            float[] afloat = otter.getCollarColor().getTextureDiffuseColors();
            renderColoredCutoutModel(this.getParentModel(), OTTER_HARNESS_LOCATION, poseStack, multiBufferSource, i, otter, afloat[0], afloat[1], afloat[2]);
        }
    }
}
