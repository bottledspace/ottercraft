package ca.otterspace.ottercraft;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(OttercraftCommon.MODID)
public class OttercraftCommon {
    public static final String MODID = "ottercraft";

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<SoundEvent> OTTER_SQUEAK = SOUNDS.register("otter_squeak",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "otter_squeak")));
    public static final RegistryObject<SoundEvent> OTTER_ANGRY = SOUNDS.register("otter_angry",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "otter_angry")));

    public static final RegistryObject<EntityType<Otter>> OTTER = ENTITIES.register("otter",
            () -> EntityType.Builder.of(Otter::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(MODID, "otter").toString()));
    public static final RegistryObject<SpawnEggItem> OTTER_SPAWN_EGG = ITEMS.register("spawn_egg_otter",
            () -> new ForgeSpawnEggItem(OTTER, 0x996633, 0x663300, new Item.Properties().stacksTo(16)));


    public OttercraftCommon() {
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, Config.COMMON_SPEC);
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {

    }
    
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(OTTER.get(), OtterRenderer::new);
    }

    // Replaced with BiomeModifiers
    // https://forge.gemwire.uk/wiki/Biome_Modifiers
    /*@SubscribeEvent
    public void loadBiome(BiomeLoadingEvent event) {
        if (event.getName() == null)
            return; // Apparently this is a possibility!
        if (Arrays.asList(Config.COMMON.otterSpawnBiomes.get().split(",")).contains(event.getName().toString()))
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(OttercraftCommon.OTTER.get(),
                            Config.COMMON.otterSpawnWeight.get(),
                            Config.COMMON.otterSpawnMin.get(),
                            Config.COMMON.otterSpawnMax.get()));
    }*/
    
    @SubscribeEvent
    public static void createAttributes(final EntityAttributeCreationEvent event) {
        event.put(OTTER.get(), Otter.createAttributes().build());
        SpawnPlacements.register(OTTER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);
    }

    @SubscribeEvent
    public static void createSpawnEggs(final CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.SPAWN_EGGS)
            event.accept(OTTER_SPAWN_EGG);
    }
}