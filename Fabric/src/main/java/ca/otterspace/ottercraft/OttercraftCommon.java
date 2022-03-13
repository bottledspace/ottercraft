package ca.otterspace.ottercraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
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
    
    SimpleConfig CONFIG = SimpleConfig.of( Ottercraft.MODID ).provider( this::provider ).request();
    
    private String provider( String filename ) {
        return "otter.biomes=river,swamp\notter.weight=200\notter.min=3\notter.max=5\n";
    }
    public String[] OTTER_SPAWN_BIOMES = CONFIG.getOrDefault("otter.biomes", "river,swamp").split(",");
    public int OTTER_SPAWN_WEIGHT = CONFIG.getOrDefault("otter.weight", 200);
    public int OTTER_SPAWN_MIN = CONFIG.getOrDefault("otter.min", 3);
    public int OTTER_SPAWN_MAX = CONFIG.getOrDefault("otter.max", 5);
    
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
                return OTTER_SPAWN_BIOMES.equals(biome.getBiomeCategory().getName());
            else
                return false;
        }, MobCategory.CREATURE, Ottercraft.OTTER, OTTER_SPAWN_WEIGHT, OTTER_SPAWN_MIN, OTTER_SPAWN_MAX);
    
        Ottercraft.OTTER_SPAWN_EGG = new SpawnEggItem(Ottercraft.OTTER, 0x996633, 0x663300, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
        Registry.register(Registry.ITEM, Ottercraft.OTTER_SPAWN_EGG_ID, Ottercraft.OTTER_SPAWN_EGG);
    }
}
