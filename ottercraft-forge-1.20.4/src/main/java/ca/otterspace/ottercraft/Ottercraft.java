package ca.otterspace.ottercraft;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.world.item.CreativeModeTabs;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Ottercraft.MODID)
public class Ottercraft {
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
            () -> new ForgeSpawnEggItem(OTTER, 0x996633, 0x663300, new Item.Properties()));


    public Ottercraft() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        SpawnPlacements.register(OTTER.get(), SpawnPlacements.Type.NO_RESTRICTIONS,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkMobSpawnRules);
    }
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        EntityRenderers.register(OTTER.get(), OtterRenderer::new);
    }

    // Add entity attributes
    @SubscribeEvent
    public static void createAttributes(final EntityAttributeCreationEvent event) {
        event.put(OTTER.get(), Otter.createAttributes().build());
    }

    // Add the spawn eggs to creative mode tab
    @SubscribeEvent
    public static void createSpawnEggs(final BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(OTTER_SPAWN_EGG);
        }
    }
}