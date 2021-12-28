package ca.otterspace.ottercraft;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OtterRenderer extends MobRenderer<Otter,OtterModel> {
    public OtterRenderer(EntityRendererProvider.Context context) {
        super(context, new OtterModel(context.bakeLayer(Ottercraft.OTTER_LAYER)), 0.5f);
        this.shadowRadius = 0.7F; //change 0.7 to the desired shadow size.
    }
    
    @Override
    public ResourceLocation getTextureLocation(Otter entity) {
        return new ResourceLocation(Ottercraft.MODID, "textures/entity/otter.png");
    }
}
