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

import static ca.otterspace.ottercraft.Ottercraft.OTTER;

// The value here should match an entry in the META-INF/mods.toml file
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@Mod(Ottercraft.MODID)
public class OttercraftClient {
    public static final Logger LOGGER = LogManager.getLogger();


    public OttercraftClient() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
   
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.<Otter>registerEntityRenderingHandler(OTTER, OtterRenderer::new);
        EntitySpawnPlacementRegistry.register(OTTER, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS,
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
                    .add(new MobSpawnInfo.Spawners(OTTER, 200, 3, 5));
        else if (biome.getBiomeCategory() == Biome.Category.SWAMP)
            event.getSpawns().getSpawner(EntityClassification.AMBIENT.CREATURE)
                    .add(new MobSpawnInfo.Spawners(OTTER, 200, 3, 5));
    }


    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        OTTER.setRegistryName(Ottercraft.OTTER_ID);
        event.getRegistry().register(OTTER);
    }
    
    @SubscribeEvent
    public static void attributeCreationEvent(EntityAttributeCreationEvent event) {
        event.put(OTTER, Otter.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        // This needs to be created here rather than in the registerEntities method,
        // since the spawn eggs require the type to be defined, and items are created
        // before entity types.
        OTTER = new EntityType<>(Otter::new,
                EntityClassification.AMBIENT.CREATURE, true,
                true, false, false, ImmutableSet.of(),
                EntitySize.fixed(0.9f, 1.0f), 4, 1);
        
        Ottercraft.OTTER_SPAWN_EGG = new SpawnEggItem(OTTER, 0x996633, 0x663300, new Item.Properties().tab(ItemGroup.TAB_MISC));
        Ottercraft.OTTER_SPAWN_EGG.setRegistryName(Ottercraft.OTTER_SPAWN_EGG_ID);
        event.getRegistry().register(Ottercraft.OTTER_SPAWN_EGG);
    }
}