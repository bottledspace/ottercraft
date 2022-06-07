package ca.otterspace.ottercraft;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(OttercraftCommon.MODID)
public class OttercraftCommon {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "ottercraft";

    public static final ResourceLocation OTTER_SQUEAK_ID = new ResourceLocation(MODID, "otter_squeak");
    public static final ResourceLocation OTTER_ANGRY_ID = new ResourceLocation(MODID, "otter_angry");
    public static final ResourceLocation OTTER_ID = new ResourceLocation(MODID, "otter");

    public static final SoundEvent OTTER_SQUEAK = new SoundEvent(OTTER_SQUEAK_ID);
    public static final SoundEvent OTTER_ANGRY = new SoundEvent(OTTER_ANGRY_ID);

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<EntityType<Otter>> OTTER = ENTITIES.register("otter",
            () -> EntityType.Builder.of(Otter::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(OTTER_ID.toString()));

    public OttercraftCommon() {
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, Config.COMMON_SPEC);
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
    
    }
    
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(OTTER.get(), OtterRenderer::new);
    }
    
    @SubscribeEvent
    public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
        OTTER_SQUEAK.setRegistryName(OTTER_SQUEAK_ID);
        event.getRegistry().register(OTTER_SQUEAK);
        OTTER_ANGRY.setRegistryName(OTTER_ANGRY_ID);
        event.getRegistry().register(OTTER_ANGRY);
    }

    @SubscribeEvent
    public void loadBiome(BiomeLoadingEvent event) {
        if (event.getName() == null)
            return; // Apparently this is a possibility!
        if (Arrays.asList(Config.COMMON.otterSpawnBiomes.get().split(",")).contains(event.getName().toString()))
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(OttercraftCommon.OTTER.get(),
                            Config.COMMON.otterSpawnWeight.get(),
                            Config.COMMON.otterSpawnMin.get(),
                            Config.COMMON.otterSpawnMax.get()));
    }
    
    @SubscribeEvent
    public static void createAttributes(final EntityAttributeCreationEvent event) {
        event.put(OTTER.get(), Otter.createAttributes().build());
        SpawnPlacements.register(OTTER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);
    }
    
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        // Spawn eggs require entities and don't use deferred registry
        event.getRegistry().register(new ForgeSpawnEggItem(OTTER, 0x996633, 0x663300, new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName("ottercraft:spawn_egg_otter"));
    }
}