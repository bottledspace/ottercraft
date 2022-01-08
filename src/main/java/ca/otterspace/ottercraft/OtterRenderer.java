package ca.otterspace.ottercraft;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class OtterRenderer extends MobRenderer<Otter,OtterModel> {
    static final ResourceLocation OTTER_TEXTURE_LOCATION = new ResourceLocation(Ottercraft.MODID, "textures/entity/otter.png");
    
    public OtterRenderer(EntityRendererManager context) {
        super(context, new OtterModel(), 0.5f);
        //this.addLayer(new OtterHarnessLayer(this));
        this.shadowRadius = 0.7F; //change 0.7 to the desired shadow size.
    }
    
    @Override
    public ResourceLocation getTextureLocation(Otter entity) {
        return OTTER_TEXTURE_LOCATION;
    }
}
