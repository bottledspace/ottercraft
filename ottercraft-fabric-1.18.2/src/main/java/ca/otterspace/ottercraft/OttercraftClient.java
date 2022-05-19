package ca.otterspace.ottercraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.core.Registry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;


@Environment(EnvType.CLIENT)
public class OttercraftClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(OttercraftCommon.OTTER, OtterRenderer::new);
    
        Registry.register(Registry.SOUND_EVENT, OttercraftCommon.OTTER_SQUEAK_ID, OttercraftCommon.OTTER_SQUEAK);
        Registry.register(Registry.SOUND_EVENT, OttercraftCommon.OTTER_ANGRY_ID, OttercraftCommon.OTTER_ANGRY);
    }
}
