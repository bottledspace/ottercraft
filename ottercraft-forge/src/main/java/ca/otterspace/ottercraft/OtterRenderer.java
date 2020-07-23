package ca.otterspace.ottercraft;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class OtterRenderer extends MobRenderer<OtterEntity, OtterModel<OtterEntity>> {
    private static final ResourceLocation OTTER_TEXTURE = new ResourceLocation(Ottercraft.MODID, "textures/entity/otter/otter.png");

    public OtterRenderer(EntityRendererManager renderManagerIn, OtterModel<OtterEntity> entityModelIn, float shadowSizeIn) {
        super(renderManagerIn, entityModelIn, shadowSizeIn);
    }

    public OtterRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new OtterModel<OtterEntity>(), 1.0f);
    }

    public ResourceLocation getEntityTexture(OtterEntity entity) {
        return OTTER_TEXTURE;
    }
}