package ca.otterspace.ottercraft;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

// The value here should match an entry in the META-INF/mods.toml file
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Ottercraft.MODID)
public class OttercraftClient {
    public static final Logger LOGGER = LogManager.getLogger();
    
    public OttercraftClient() {
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, Config.COMMON_SPEC);
        
        Ottercraft.OTTER = new EntityType<Otter>(Otter::new,
                MobCategory.CREATURE, true,
                true, false, false, ImmutableSet.of(),
                EntityDimensions.fixed(0.9f, 1.0f), 4, 1);
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {
    
    }
    
    @SubscribeEvent
    public static void doClientStuff(final FMLClientSetupEvent event) {
        EntityRenderers.register(Ottercraft.OTTER, OtterRenderer::new);
    }
    
    @SubscribeEvent
    public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
        Ottercraft.OTTER_SQUEAK.setRegistryName(Ottercraft.OTTER_SQUEAK_ID);
        event.getRegistry().register(Ottercraft.OTTER_SQUEAK);
        Ottercraft.OTTER_ANGRY.setRegistryName(Ottercraft.OTTER_ANGRY_ID);
        event.getRegistry().register(Ottercraft.OTTER_ANGRY);
    }

    @SubscribeEvent
    public void onBiomesLoad(BiomeLoadingEvent event) {
        LOGGER.info("%d".formatted(Config.COMMON.otterSpawnWeight.get()));
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if (biome == null)
            return;
        else if (Arrays.asList(Config.COMMON.otterSpawnBiomes.get().split(",")).contains(biome.toString()))
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(Ottercraft.OTTER,
                            Config.COMMON.otterSpawnWeight.get(),
                            Config.COMMON.otterSpawnMin.get(),
                            Config.COMMON.otterSpawnMax.get()));
    }
    
    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        Ottercraft.OTTER.setRegistryName(Ottercraft.OTTER_ID);
        event.getRegistry().register(Ottercraft.OTTER);
    }
    
    @SubscribeEvent
    public static void attributeCreationEvent(final EntityAttributeCreationEvent event) {
        event.put(Ottercraft.OTTER, Otter.createAttributes().build());
        SpawnPlacements.register(Ottercraft.OTTER, SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);
    }
    
    @SubscribeEvent
    public static void registerItem(final RegistryEvent.Register<Item> event) {
        Ottercraft.OTTER_SPAWN_EGG = new SpawnEggItem(Ottercraft.OTTER, 0x996633, 0x663300, new Item.Properties().tab(CreativeModeTab.TAB_MISC));
        Ottercraft.OTTER_SPAWN_EGG.setRegistryName(Ottercraft.OTTER_SPAWN_EGG_ID);
        event.getRegistry().register(Ottercraft.OTTER_SPAWN_EGG);
    }
}