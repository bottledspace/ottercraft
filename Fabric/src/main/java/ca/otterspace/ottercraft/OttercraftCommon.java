package ca.otterspace.ottercraft;

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
import net.minecraft.world.level.biome.Biomes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

public class OttercraftCommon implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final EntityType<Otter> OTTER = Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(Ottercraft.MODID, "otter"),
            FabricEntityTypeBuilder.create(MobCategory.CREATURE, Otter::new)
                    .dimensions(EntityDimensions.fixed(0.75f, 0.75f))
                    .forceTrackedVelocityUpdates(true)
                    .trackedUpdateRate(1)
                    .build()
    );
    
    SpawnEggItem OTTER_SPAWN_EGG = new SpawnEggItem(OttercraftCommon.OTTER, 0x996633, 0x663300, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
    
    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(OTTER, AbstractOtter.createAttributes());
        BiomeModifications.addSpawn((BiomeSelectionContext bsc) -> {
            Biome biome = bsc.getBiome();
            if (biome != null)
                return (biome.getBiomeCategory() == Biome.BiomeCategory.RIVER ||
                        biome.getBiomeCategory() == Biome.BiomeCategory.SWAMP);
            else return false;
        }, MobCategory.CREATURE, OttercraftCommon.OTTER, 200, 3, 5);
        Registry.register(Registry.ITEM, new ResourceLocation(Ottercraft.MODID, "spawn_egg_otter"), OTTER_SPAWN_EGG);
    }
}
