package ca.otterspace.ottercraft;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.SpawnEggItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    
    public static EntityType<Otter> OTTER;
    
    public static SpawnEggItem OTTER_SPAWN_EGG;
}