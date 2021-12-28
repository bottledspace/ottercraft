package ca.otterspace.ottercraft;

import ca.otterspace.anim.Animation;
import ca.otterspace.anim.Animations;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.TooltipFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Ottercraft {
    public static final Logger LOGGER = LogManager.getLogger();
    
    public static final String MODID = "ottercraft";
    
    public static ResourceLocation OTTER_SQUEAK_ID = new ResourceLocation(Ottercraft.MODID, "otter_squeak");
    public static ResourceLocation OTTER_ANGRY_ID = new ResourceLocation(Ottercraft.MODID, "otter_angry");
    public static ResourceLocation OTTER_SPAWN_EGG_ID = new ResourceLocation(Ottercraft.MODID, "spawn_egg_otter");
    public static ResourceLocation OTTER_ID = new ResourceLocation(Ottercraft.MODID, "otter");
    public static ResourceLocation GIANT_OTTER_ID = new ResourceLocation(Ottercraft.MODID, "giant_otter");
    
    public static SoundEvent OTTER_SQUEAK = new SoundEvent(OTTER_SQUEAK_ID);
    public static SoundEvent OTTER_ANGRY = new SoundEvent(OTTER_ANGRY_ID);
    
    public static final ModelLayerLocation GIANT_OTTER_LAYER = new ModelLayerLocation(GIANT_OTTER_ID, "main");
    public static final ModelLayerLocation OTTER_LAYER = new ModelLayerLocation(OTTER_ID, "main");
    
    public static EntityType<Otter> OTTER;
    public static EntityType<GiantOtter> GIANT_OTTER;
    
    public static SpawnEggItem OTTER_SPAWN_EGG;
}