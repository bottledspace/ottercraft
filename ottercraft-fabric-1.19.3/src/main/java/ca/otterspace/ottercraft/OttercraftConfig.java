package ca.otterspace.ottercraft;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;

public class OttercraftConfig {
    public static String otterSpawnBiomes = "minecraft:river,minecraft:swamp";
    public static int otterSpawnWeight = 200;
    public static int otterSpawnMin = 3;
    public static int otterSpawnMax = 5;
    
    static private boolean tryRead(Path source) {
        try {
            JsonObject root = JsonParser.parseReader(new FileReader(source.toFile())).getAsJsonObject();
            if (root.get("otterSpawnBiomes") != null)
                otterSpawnBiomes = root.get("otterSpawnBiomes").getAsString();
            if (root.get("otterSpawnWeight") != null)
                otterSpawnWeight = root.get("otterSpawnWeight").getAsInt();
            if (root.get("otterSpawnMin") != null)
                otterSpawnMin = root.get("otterSpawnMin").getAsInt();
            if (root.get("otterSpawnMax") != null)
                otterSpawnMax = root.get("otterSpawnMax").getAsInt();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static boolean tryWrite(Path source) {
        try {
            PrintWriter writer = new PrintWriter(source.toFile(), "UTF-8");
            writer.write("{\n\"otterSpawnBiomes\":\"minecraft:river,minecraft:swamp\",\n\"otterSpawnWeight\":100,\n\"otterSpawnMin\":2,\n\"otterSpawnMax\":3\n}");
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    static {
        Path source = FabricLoader.getInstance().getConfigDir().resolve(OttercraftCommon.MODID + ".json");
        if (!tryRead(source)) {
            tryWrite(source);
        }
    }
}
