package ca.otterspace.ottercraft;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber
@Mod(Ottercraft.MODID)
public final class Ottercraft {
    public static final String MODID = "ottercraft";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Ottercraft.MODID);
    public static final RegistryObject<EntityType<OtterEntity>> OTTER = ENTITY_TYPES.register("otter", () ->
            EntityType.Builder.<OtterEntity>create(OtterEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.WOLF.getWidth(), EntityType.WOLF.getHeight())
                    .build(new ResourceLocation(Ottercraft.MODID, "otter").toString())
    );

    public Ottercraft() {
        LOGGER.debug("registering stuff!");

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(Ottercraft::setupCommon);
        modEventBus.addListener(Ottercraft::setupClient);
    }

    @SubscribeEvent
    public static void setupCommon(final FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        RenderingRegistry.<OtterEntity>registerEntityRenderingHandler(OTTER.get(), OtterRenderer::new);
        //RenderingRegistry.registerEntityRenderingHandler(EmpiresEntities.ELEPHANT.get(), ElephantEntityRenderer::new);
    }
}
