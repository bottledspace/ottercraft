package ca.otterspace.ottercraft;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@Mod(Ottercraft.MODID)
public class Ottercraft {
    public static final String MODID = "ottercraft";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public Ottercraft() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.<EntityOtter>registerEntityRenderingHandler(OTTER, RendererOtter::new);
        EntitySpawnPlacementRegistry.register(OTTER, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkMobSpawnRules);
    }

    public static SoundEvent OTTER_SQUEAK;
    public static SoundEvent OTTER_ANGRY;

    @SubscribeEvent
    public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
        ResourceLocation loc;
        loc = new ResourceLocation(MODID, "otter_squeak");
        OTTER_SQUEAK = new SoundEvent(loc);
        OTTER_SQUEAK.setRegistryName(loc);
        event.getRegistry().register(OTTER_SQUEAK);
        loc = new ResourceLocation(MODID, "otter_angry");
        OTTER_ANGRY = new SoundEvent(loc);
        OTTER_ANGRY.setRegistryName(loc);
        event.getRegistry().register(OTTER_ANGRY);
    }

    @SubscribeEvent
    public void onBiomesLoad(BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if (biome == null)
            return;
        else if (biome.getBiomeCategory() == Biome.Category.RIVER)
            event.getSpawns().getSpawner(EntityClassification.CREATURE)
                 .add(new MobSpawnInfo.Spawners(OTTER, 10, 3, 5));
        else if (biome.getBiomeCategory() == Biome.Category.SWAMP)
            event.getSpawns().getSpawner(EntityClassification.CREATURE)
                    .add(new MobSpawnInfo.Spawners(OTTER, 10, 3, 5));
    }

    public static EntityType<EntityOtter> OTTER;

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        OTTER = new EntityType<>(EntityOtter::new, EntityClassification.CREATURE, true, true, false, false, ImmutableSet.of(), EntitySize.fixed(0.9f, 1.0f), 4, 1);
        OTTER.setRegistryName(MODID, "otter");
        registry.register(OTTER);
    }

    @SubscribeEvent
    public static void attributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(OTTER, EntityOtter.createAttributes().build());
    }
}
