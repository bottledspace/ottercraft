package ca.otterspace.anim;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


public class Geometry {
    
    /**
     * Bones as provided to us by the JSON file. In this form they are not usable
     * for animating directly, and are simply used to generate a MeshDefinition.
     */
    class RawBone {
        Vector3f uv = null;
        Vector3f origin = null;
        Vector3f size = null;
        Vector3f pivot = null;
        Vector3f rotation = null;
    }
    
    Pose idlePose = new Pose();
    MeshDefinition meshDefinition = new MeshDefinition();
    Multimap<String, String> hierarchy = TreeMultimap.create();
    
    public LayerDefinition createLayerDefinition() {
        return LayerDefinition.create(meshDefinition, 128, 128);
    }
    
    private void generateMeshDefinition(Map<String, RawBone> bones) {
        Deque<ImmutablePair<String, PartDefinition>> pending = new ArrayDeque<>();
        pending.push(new ImmutablePair<>("_root", meshDefinition.getRoot()));
        
        do {
            ImmutablePair<String,PartDefinition> top = pending.pop();
            PartDefinition parentPart = top.right;
            RawBone parent = bones.get(top.left);
            
            for (String childName : hierarchy.get(top.left)) {
                RawBone bone = bones.get(childName);
        
                // Pivots are relative to parent, with the exception of root.
                PartPose partPose;
                if (parent != null) {
                    partPose = PartPose.offsetAndRotation(
                            -(parent.pivot.x() - bone.pivot.x()), parent.pivot.y() - bone.pivot.y(), -(parent.pivot.z() - bone.pivot.z()),
                            Mth.DEG_TO_RAD * bone.rotation.x(), Mth.DEG_TO_RAD * bone.rotation.y(), Mth.DEG_TO_RAD * bone.rotation.z());
                } else
                    partPose = PartPose.offsetAndRotation(
                            -bone.pivot.x(), bone.pivot.y()+24, -bone.pivot.z(),
                            Mth.DEG_TO_RAD * bone.rotation.x(), Mth.DEG_TO_RAD * bone.rotation.y(), Mth.DEG_TO_RAD * bone.rotation.z());
                idlePose.poseMap.put(childName, partPose);
                
                // Leaf bones have a single cube.
                CubeListBuilder cubeBuilder;
                if (bone.size != null)
                    cubeBuilder = CubeListBuilder.create()
                            .texOffs((int) bone.uv.x(), (int) bone.uv.y())
                            .addBox(bone.origin.x() - bone.pivot.x(), -(bone.origin.y() - bone.pivot.y()) - bone.size.y(), bone.origin.z() - bone.pivot.z(),
                                    bone.size.x(), bone.size.y(), bone.size.z());
                else
                    cubeBuilder = CubeListBuilder.create();
                
                PartDefinition part = parentPart.addOrReplaceChild(childName, cubeBuilder, partPose);
                pending.push(new ImmutablePair<>(childName, part));
            }
        } while (!pending.isEmpty());
    }
    
    protected static Vector3f parseVec3(JsonElement element) {
        if (element == null || !element.isJsonArray())
            return null;
        JsonArray array = element.getAsJsonArray();
        float x = (array.size() >= 1)? array.get(0).getAsFloat() : 0;
        float y = (array.size() >= 2)? array.get(1).getAsFloat() : 0;
        float z = (array.size() >= 3)? array.get(2).getAsFloat() : 0;
        return new Vector3f(x, y, z);
    }
    
    protected RawBone parseCube(JsonObject boneElement) {
        RawBone bone = new RawBone();
        bone.size = parseVec3(boneElement.get("size"));
        bone.origin = parseVec3(boneElement.get("origin"));
        bone.pivot = parseVec3(boneElement.get("pivot"));
        bone.rotation = parseVec3(boneElement.get("rotation"));
        if (bone.rotation == null)
            bone.rotation = Vector3f.ZERO;
        bone.uv = parseVec3(boneElement.get("uv"));
        if (bone.uv == null)
            bone.uv = Vector3f.ZERO;
        return bone;
    }
    
    protected void parseBone(Map<String, RawBone> bones, JsonObject boneElement) {
        RawBone bone = new RawBone();
        bone.pivot = parseVec3(boneElement.get("pivot"));
        bone.rotation = parseVec3(boneElement.get("rotation"));
        if (bone.rotation == null)
            bone.rotation = Vector3f.ZERO;
        
        String boneName = boneElement.get("name").getAsString();
        if (boneElement.get("parent") == null) {
            bone.pivot = Vector3f.ZERO;
            this.hierarchy.put("_root", boneName);
        } else {
            String parentName = boneElement.get("parent").getAsString();
            this.hierarchy.put(parentName, boneName);
        }
        bones.put(boneName, bone);
        
        if (boneElement.get("cubes") != null) {
            JsonArray cubes = boneElement.get("cubes").getAsJsonArray();
            for (int j = 0; j < cubes.size(); j++) {
                // Generate a unique name for our leaf bone.
                String cubeName = String.format("%s_%d", boneElement.get("name").getAsString(), j);
                
                RawBone cubeBone = parseCube(cubes.get(j).getAsJsonObject());
                if (cubeBone.pivot == null)
                    cubeBone.pivot = bone.pivot;
                bones.put(cubeName, cubeBone);
                
                this.hierarchy.put(boneName, cubeName);
            }
        }
    }
    
    protected void parseGeometry(JsonElement root) {
        Map<String, RawBone> bones = new HashMap<>();
    
        JsonElement geometry = root.getAsJsonObject().get("minecraft:geometry");
        JsonArray bonesElement = geometry.getAsJsonArray().get(0)
                                  .getAsJsonObject().get("bones")
                                  .getAsJsonArray();
    
        for (int i = 0; i < bonesElement.size(); i++) {
            JsonObject boneElement = bonesElement.get(i).getAsJsonObject();
            parseBone(bones, boneElement);
        }
        generateMeshDefinition(bones);
    }
    
    public static Geometry loadGeometry(ResourceLocation location) {
        try {
            InputStream stream = Minecraft.getInstance().getResourceManager().getResource(location).getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            JsonElement root = JsonParser.parseReader(reader);
            Geometry model = new Geometry();
            model.parseGeometry(root);
            return model;
        } catch (IOException ex) {
            return null;
        }
    }
    
    public Model compileModel(ModelPart root) {
        Model model = new Model();
        model.hierarchy = hierarchy;
        model.idlePose = idlePose;
        Deque<Map.Entry<String,ModelPart>> pending = new ArrayDeque<>();
        pending.push(new ImmutablePair<>("_root", root));
        do {
            Map.Entry<String,ModelPart> top = pending.pop();
            for (String childName : hierarchy.get(top.getKey())) {
                pending.push(new ImmutablePair<>(childName, top.getValue().getChild(childName)));
            }
            model.partMap.put(top.getKey(), top.getValue());
        } while (!pending.isEmpty());
        
        return model;
    }
}