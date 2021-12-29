package ca.otterspace.ottercraft;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OtterRenderer extends MobRenderer<Otter,OtterModel> {
    static final ResourceLocation OTTER_TEXTURE_LOCATION = new ResourceLocation(Ottercraft.MODID, "textures/entity/otter.png");
    
    public OtterRenderer(EntityRendererProvider.Context context) {
        super(context, new OtterModel(context.bakeLayer(Ottercraft.OTTER_LAYER)), 0.5f);
        this.addLayer(new OtterHarnessLayer(this));
        this.shadowRadius = 0.7F; //change 0.7 to the desired shadow size.
    }
    
    @Override
    public ResourceLocation getTextureLocation(Otter entity) {
        return OTTER_TEXTURE_LOCATION;
    }
}
