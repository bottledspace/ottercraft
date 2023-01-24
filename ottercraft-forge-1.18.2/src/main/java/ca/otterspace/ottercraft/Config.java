package ca.otterspace.ottercraft;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<String> otterSpawnBiomes;
        public final ForgeConfigSpec.ConfigValue<Integer> otterSpawnWeight;
        public final ForgeConfigSpec.ConfigValue<Integer> otterSpawnMin;
        public final ForgeConfigSpec.ConfigValue<Integer> otterSpawnMax;
        
        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("General");
            this.otterSpawnBiomes = builder.worldRestart()
                    .define("otterSpawnBiomes", "minecraft:swamp,minecraft:river");
            this.otterSpawnWeight = builder.worldRestart()
                    .define("otterSpawnRate", 100);
            this.otterSpawnMin = builder.worldRestart()
                    .define("otterSpawnMin", 2);
            this.otterSpawnMax = builder.worldRestart()
                    .define("otterSpawnMax", 3);
            builder.pop();
        }
    }
    
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;
    
    static {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }
}