package ca.otterspace.ottermod;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
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
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@Mod("ottermod")
public class Ottermod {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public Ottermod() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        bus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        bus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        bus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        bus.addListener(this::doClientStuff);

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
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
        loc = new ResourceLocation("ottermod", "otter_squeak");
        OTTER_SQUEAK = new SoundEvent(loc);
        OTTER_SQUEAK.setRegistryName(loc);
        event.getRegistry().register(OTTER_SQUEAK);
        loc = new ResourceLocation("ottermod", "otter_angry");
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

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("ottermod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static EntityType<EntityOtter> OTTER;

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        LOGGER.info("HELLO from registerEntities");

        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        OTTER = new EntityType<>(EntityOtter::new, EntityClassification.CREATURE, true, true, false, false, ImmutableSet.of(), EntitySize.fixed(0.9f, 1.0f), 4, 1);
        OTTER.setRegistryName("ottermod", "otter");
        registry.register(OTTER);
    }

    @SubscribeEvent
    public static void attributeCreationEvent(EntityAttributeCreationEvent event) {
        LOGGER.info("HELLO from attributeCreationEvent");

        event.put(OTTER, EntityOtter.createAttributes().build());
    }
}
