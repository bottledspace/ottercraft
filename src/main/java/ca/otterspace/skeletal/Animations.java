package ca.otterspace.skeletal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Animations {
    Map<String, Animation> animMap = new HashMap<>();
    
    public Animation getAnimation(String name) {
        return animMap.get(name);
    }
    
    protected LinearCurve parseCurve(JsonElement channel) {
        if (channel == null)
            return null;
        else if (channel.isJsonArray()) {
            JsonArray array = channel.getAsJsonArray();
            LinearCurve curve = new LinearCurve();
            curve.put(0f, new Vector3f(array.get(0).getAsFloat(),array.get(1).getAsFloat(), array.get(2).getAsFloat()));
            return curve;
        } else if (channel.isJsonObject()) {
            JsonObject object = channel.getAsJsonObject();
            LinearCurve curve = new LinearCurve();
            for (Map.Entry<String,JsonElement> it : object.entrySet()) {
                JsonArray array = it.getValue().getAsJsonArray();
                float time = Float.parseFloat(it.getKey());
                Vector3f value = new Vector3f(array.get(0).getAsFloat(), array.get(1).getAsFloat(), array.get(2).getAsFloat());
                curve.put(time, value);
            }
            return curve;
        } else
            return null;
    }
    
    protected void parseAnimations(JsonElement root) {
        JsonObject animationsElement = root.getAsJsonObject().get("animations").getAsJsonObject();
        for (Map.Entry<String, JsonElement> animationEntry : animationsElement.entrySet()) {
            JsonObject animationObject = animationEntry.getValue().getAsJsonObject();
            String animationName = animationEntry.getKey();
            
            Animation animation = new Animation();
            JsonObject bones = animationObject.get("bones").getAsJsonObject();
            for (Map.Entry<String,JsonElement> boneEntry : bones.entrySet()) {
                String boneName = boneEntry.getKey();
                JsonObject channel = boneEntry.getValue().getAsJsonObject();
                
                LinearCurve positionCurve = parseCurve(channel.get("position"));
                if (positionCurve != null) {
                    positionCurve.points.replaceAll((key, value) -> {
                        value.mul(1, -1, 1);
                        return value;
                    });
                    animation.positionCurves.put(boneName, positionCurve);
                }
                LinearCurve rotationCurve = parseCurve(channel.get("rotation"));
                if (rotationCurve != null) {
                    rotationCurve.points.replaceAll((key, value) -> {
                        value.mul(Mth.DEG_TO_RAD);
                        return value;
                    });
                    animation.rotationCurves.put(boneName, rotationCurve);
                }
            }
            
            if (animationObject.get("loop") != null)
                animation.loop = animationObject.get("loop").getAsBoolean();
            if (animationObject.get("animation_length") != null)
                animation.length = animationObject.get("animation_length").getAsFloat();
            animMap.put(animationName, animation);
        }
    }
    
    public static Animations loadAnimations(ResourceLocation location) {
        Animations animations = new Animations();
        try {
            ResourceManager manager = Minecraft.getInstance().getResourceManager();
            InputStream stream = manager.getResource(location).getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            JsonParser parser = new JsonParser();
            JsonElement root = parser.parse(reader);
            animations.parseAnimations(root);
            return animations;
        } catch (IOException ex) {
            return null;
        }
    }
}
