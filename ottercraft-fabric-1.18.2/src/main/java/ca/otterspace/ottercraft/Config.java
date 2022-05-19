package ca.otterspace.ottercraft;

import net.fabricmc.loader.api.FabricLoader;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

public class Config {
    public static String otterSpawnBiomes;
    public static int otterSpawnWeight;
    public static int otterSpawnMin;
    public static int otterSpawnMax;
    
    static private boolean tryRead(Path source) {
        try {
            TomlParseResult result = Toml.parse(source);
            otterSpawnBiomes = result.getString("General.otterSpawnBiomes",()->"minecraft:river,minecraft:swamp");
            otterSpawnWeight = (int)result.getLong("General.otterSpawnWeight", ()->200L);
            otterSpawnMin = (int)result.getLong("General.otterSpawnMin", ()->3L);
            otterSpawnMax = (int)result.getLong("General.otterSpawnMax", ()->5L);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private static boolean tryWrite(Path source) {
        try {
            PrintWriter writer = new PrintWriter(source.toFile(), "UTF-8");
            writer.write("[General]\n  otterSpawnBiomes=\"minecraft:river,minecraft:swamp\"\n  otterSpawnWeight=200\n  otterSpawnMin=3\n  otterSpawnMax=5\n");
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
        Path source = FabricLoader.getInstance().getConfigDir().resolve(OttercraftCommon.MODID + ".properties");
        if (!tryRead(source)) {
            tryWrite(source);
            tryRead(source);
        }
    }
}
