package ca.otterspace.skeletal;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class Model {
    Map<String, Bone> boneMap = Maps.newHashMap();
    Bone root = new Bone();

    
    class RawCube {
        int[] uv;
        float[] origin;
        float[] size;
        float[] pivot;
        float[] rotation;
    }
    class RawBone {
        String name;
        String parent;
        float[] pivot;
        float[] rotation;
        RawCube[] cubes;
        
        transient Bone bone;
    }
    
    protected void parseGeometry(JsonElement rootEl) {
        Map<String, RawBone> rbones = Maps.newHashMap();
        Gson gson = new Gson();
        rootEl.getAsJsonObject().get("minecraft:geometry")
                .getAsJsonArray().get(0)
                .getAsJsonObject().get("bones")
                .getAsJsonArray().forEach(boneEl -> {
                    RawBone rbone = gson.fromJson(boneEl, RawBone.class);
                    rbone.bone = new Bone();
                    rbone.bone.name = rbone.name;
                    rbones.put(rbone.name, rbone);
                });
        
        for (RawBone rbone : rbones.values()) {
            RawBone rparent = rbones.get(rbone.parent);
            
            Bone bone = rbone.bone;
            if (rbone.parent == null)
                bone.parent = this.root;
            else
                bone.parent = rparent.bone;
            bone.parent.children.add(bone);
            boneMap.put(bone.name, bone);
            
            Matrix4f boneTransform = Matrix4f.createScaleMatrix(1,1,1);
            if (rbone.pivot != null) {
                if (rparent != null)
                    boneTransform.multiply(Matrix4f.createTranslateMatrix(-rparent.pivot[0]/16f, rparent.pivot[1]/16f, -rparent.pivot[2]/16f));
                boneTransform.multiply(Matrix4f.createTranslateMatrix(rbone.pivot[0]/16f, -rbone.pivot[1]/16f, rbone.pivot[2]/16f));
            }
            if (rbone.rotation != null) {
                boneTransform.multiply(Vector3f.ZP.rotationDegrees(rbone.rotation[2]));
                boneTransform.multiply(Vector3f.YP.rotationDegrees(rbone.rotation[1]));
                boneTransform.multiply(Vector3f.XP.rotationDegrees(rbone.rotation[0]));
            }
            bone.transform = boneTransform;
            
            if (rbone.cubes != null) {
                for (RawCube rcube : rbone.cubes) {
                    Bone leaf = new Bone();
                    leaf.parent = bone;
                    Matrix4f leafTransform = Matrix4f.createScaleMatrix(1,1,1);
                    if (rcube.pivot != null) {
                        leafTransform.multiply(Matrix4f.createTranslateMatrix(-rbone.pivot[0]/16f, rbone.pivot[1]/16f, -rbone.pivot[2]/16f));
                        leafTransform.multiply(Matrix4f.createTranslateMatrix(rcube.pivot[0]/16f, -rcube.pivot[1]/16f, rcube.pivot[2]/16f));
                    }
                    if (rcube.rotation != null) {
                        leafTransform.multiply(Vector3f.ZP.rotationDegrees(rcube.rotation[2]));
                        leafTransform.multiply(Vector3f.YP.rotationDegrees(rcube.rotation[1]));
                        leafTransform.multiply(Vector3f.XP.rotationDegrees(rcube.rotation[0]));
                    }
                    if (rcube.pivot == null)
                        rcube.pivot = rbone.pivot;
                    leaf.transform = leafTransform;
                    leaf.cube = new ModelPart.Cube(rcube.uv[0], rcube.uv[1],
                            rcube.origin[0] - rcube.pivot[0],
                            -(rcube.origin[1] - rcube.pivot[1]) - rcube.size[1],
                            rcube.origin[2] - rcube.pivot[2],
                            rcube.size[0], rcube.size[1], rcube.size[2],
                            0, 0, 0, false, 128f, 128f);
                    bone.children.add(leaf);
                }
            }
        }
    }
    
    public void applyPose(Pose pose) {
        for (Map.Entry<String, Bone> entry : boneMap.entrySet()) {
            LocRot locrot = pose.getLocal(entry.getKey());
            if (locrot != null)
                entry.getValue().locrot = locrot;
            else
                entry.getValue().locrot = new LocRot();
        }
    }
    
    public static Model loadGeometry(ResourceLocation location) {
        Model result = new Model();
        try {
            InputStream stream = Minecraft.getInstance().getResourceManager().getResource(location).get().open();
            InputStreamReader reader = new InputStreamReader(stream);
            JsonElement root = JsonParser.parseReader(reader);
            result.parseGeometry(root);
            return result;
        } catch (IOException ex) {
            return null;
        }
    }
    
    public Bone getBone(String name) {
        return boneMap.get(name);
    }
    
    public void render(PoseStack mats, VertexConsumer vbuf, int i, int j, float f, float g, float h, float k) {
        root.render(mats, vbuf, i,j,f,g,h,k);
    }
}