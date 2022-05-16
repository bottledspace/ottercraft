package ca.otterspace.ottercraft;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Ottercraft.MODID)
public class OttercraftCommon {
    public static final Logger LOGGER = LogManager.getLogger();


    public OttercraftCommon() {
        LOGGER.info("init!");

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private static void registerEntityTypes() {
        if (Ottercraft.OTTER != null)
            return;
        LOGGER.info("register Entity!");
        Ottercraft.OTTER = new EntityType<>(EntityOtter::new,
            EntityClassification.AMBIENT.CREATURE, true,
            true, false, false, ImmutableSet.of(),
            EntitySize.fixed(0.9f, 1.0f), 4, 1);
    }

    private void setup(final FMLCommonSetupEvent event) {
   
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        registerEntityTypes();
        RenderingRegistry.<EntityOtter>registerEntityRenderingHandler(Ottercraft.OTTER, RendererOtter::new);
        EntitySpawnPlacementRegistry.register(Ottercraft.OTTER, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkMobSpawnRules);
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
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if (biome == null)
            return;
        else if (biome.getBiomeCategory() == Biome.Category.RIVER)
            event.getSpawns().getSpawner(EntityClassification.AMBIENT.CREATURE)
                    .add(new MobSpawnInfo.Spawners(Ottercraft.OTTER, 200, 3, 5));
        else if (biome.getBiomeCategory() == Biome.Category.SWAMP)
            event.getSpawns().getSpawner(EntityClassification.AMBIENT.CREATURE)
                    .add(new MobSpawnInfo.Spawners(Ottercraft.OTTER, 200, 3, 5));
    }


    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        registerEntityTypes();
        Ottercraft.OTTER.setRegistryName(Ottercraft.OTTER_ID);
        event.getRegistry().register(Ottercraft.OTTER);
    }
    
    @SubscribeEvent
    public static void attributeCreationEvent(EntityAttributeCreationEvent event) {
        registerEntityTypes();
        event.put(Ottercraft.OTTER, EntityOtter.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        registerEntityTypes();
        Ottercraft.OTTER_SPAWN_EGG = new SpawnEggItem(Ottercraft.OTTER, 0x996633, 0x663300, new Item.Properties().tab(ItemGroup.TAB_MISC));
        Ottercraft.OTTER_SPAWN_EGG.setRegistryName(Ottercraft.OTTER_SPAWN_EGG_ID);
        event.getRegistry().register(Ottercraft.OTTER_SPAWN_EGG);
    }
}
