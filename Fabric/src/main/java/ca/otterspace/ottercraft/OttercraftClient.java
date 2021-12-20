package ca.otterspace.ottercraft;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Environment(EnvType.CLIENT)
public class OttercraftClient implements ClientModInitializer {
    
    public static SoundEvent OTTER_SQUEAK;
    public static SoundEvent OTTER_ANGRY;
    
    
    @Override
    public void onInitializeClient() {
        GeckoLib.initialize();
        
        EntityRendererRegistry.register(OttercraftCommon.OTTER, (context) -> {
            return new OtterRenderer(context);
        });
    }
}
