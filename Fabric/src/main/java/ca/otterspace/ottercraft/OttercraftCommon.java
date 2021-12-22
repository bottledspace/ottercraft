package ca.otterspace.ottercraft;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OttercraftCommon implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();

    
    
    @Override
    public void onInitialize() {
        Ottercraft.OTTER = Registry.register(Registry.ENTITY_TYPE, Ottercraft.OTTER_ID,
                EntityType.Builder.of(Otter::new, MobCategory.CREATURE)
                        .sized(0.9f, 1.0f)
                        .clientTrackingRange(4)
                        .updateInterval(1)
                        .build(Ottercraft.OTTER_ID.toString()));
    
        FabricDefaultAttributeRegistry.register(Ottercraft.OTTER, Otter.createAttributes());
        
        BiomeModifications.addSpawn((BiomeSelectionContext bsc) -> {
            Biome biome = bsc.getBiome();
            if (biome != null)
                return (biome.getBiomeCategory() == Biome.BiomeCategory.RIVER ||
                        biome.getBiomeCategory() == Biome.BiomeCategory.SWAMP);
            else return false;
        }, MobCategory.CREATURE, Ottercraft.OTTER, 200, 3, 5);
        
        Ottercraft.OTTER_SPAWN_EGG = new SpawnEggItem(Ottercraft.OTTER, 0x996633, 0x663300, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
        Registry.register(Registry.ITEM, Ottercraft.OTTER_SPAWN_EGG_ID, Ottercraft.OTTER_SPAWN_EGG);
    }
}
