package ca.otterspace.ottercraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class OttercraftCommon implements ModInitializer {
    public static final String MODID = "ottercraft";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final ResourceLocation OTTER_SQUEAK_ID = new ResourceLocation(MODID, "otter_squeak");
    public static final ResourceLocation OTTER_ANGRY_ID = new ResourceLocation(MODID, "otter_angry");
    public static final ResourceLocation OTTER_SPAWN_EGG_ID = new ResourceLocation(MODID, "spawn_egg_otter");
    public static final ResourceLocation OTTER_ID = new ResourceLocation(MODID, "otter");
    public static final SoundEvent OTTER_SQUEAK = new SoundEvent(OTTER_SQUEAK_ID);
    public static final SoundEvent OTTER_ANGRY = new SoundEvent(OTTER_ANGRY_ID);

    public static final EntityType<Otter> OTTER = Registry.register(Registry.ENTITY_TYPE,
            OTTER_ID, FabricEntityTypeBuilder.create(MobCategory.CREATURE, Otter::new)
                    .dimensions(EntityDimensions.fixed(0.9f, 1.0f))
                    .build());

    public static final SpawnEggItem OTTER_SPAWN_EGG = new SpawnEggItem(OTTER, 0x996633, 0x663300, new Item.Properties().tab(CreativeModeTab.TAB_MISC));


    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(OTTER, Otter.createAttributes());
        BiomeModifications.addSpawn((BiomeSelectionContext bsc) -> {
            Biome biome = bsc.getBiome();
            if (biome == null)
                return false;
            return Arrays.stream(OttercraftConfig.otterSpawnBiomes.split(","))
                    .anyMatch(bsc.getBiomeKey().location().toString()::equals);
        }, MobCategory.CREATURE, OTTER,
           OttercraftConfig.otterSpawnWeight,
           OttercraftConfig.otterSpawnMin,
           OttercraftConfig.otterSpawnMax);

        Registry.register(Registry.ITEM, OTTER_SPAWN_EGG_ID, OTTER_SPAWN_EGG);
        Registry.register(Registry.SOUND_EVENT, OTTER_SQUEAK_ID, OTTER_SQUEAK);
        Registry.register(Registry.SOUND_EVENT, OTTER_ANGRY_ID, OTTER_ANGRY);
    }
}
