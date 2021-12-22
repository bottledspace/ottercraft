package ca.otterspace.ottercraft;

import com.google.common.collect.ImmutableSet;
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

import java.util.List;

public class Ottercraft {
    public static final String MODID = "ottercraft";
    
    public static ResourceLocation OTTER_SQUEAK_ID = new ResourceLocation(Ottercraft.MODID, "otter_squeak");
    public static ResourceLocation OTTER_ANGRY_ID = new ResourceLocation(Ottercraft.MODID, "otter_angry");
    public static ResourceLocation OTTER_SPAWN_EGG_ID = new ResourceLocation(Ottercraft.MODID, "spawn_egg_otter");
    public static ResourceLocation OTTER_ID = new ResourceLocation(Ottercraft.MODID, "otter");
    
    public static SoundEvent OTTER_SQUEAK = new SoundEvent(OTTER_SQUEAK_ID);
    public static SoundEvent OTTER_ANGRY = new SoundEvent(OTTER_ANGRY_ID);
    
    public static EntityType<Otter> OTTER;
    public static SpawnEggItem OTTER_SPAWN_EGG;
}