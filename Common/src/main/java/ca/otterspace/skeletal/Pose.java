package ca.otterspace.skeletal;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.mojang.math.Vector4f;

import java.util.Map;

public class Pose {
    public static class LocRotScale {
        Vector4f color = new Vector4f(1,1,1,1);
        Vector3f offset = Vector3f.ZERO.copy();
        Vector3f rotation = Vector3f.ZERO.copy();
        
        public LocRotScale() {}
        public LocRotScale(Vector3f offset, Vector3f rotation) {
            this.offset = offset;
            this.rotation = rotation;
        }
        
        public Matrix4f getMatrix() {
            Matrix4f mat = Matrix4f.createScaleMatrix(1, 1, 1);
            mat.multiply(Matrix4f.createTranslateMatrix(offset.x()/16f, offset.y()/16f, offset.z()/16f));
            mat.multiply(Vector3f.ZP.rotation(rotation.z()));
            mat.multiply(Vector3f.YP.rotation(rotation.y()));
            mat.multiply(Vector3f.XP.rotation(rotation.x()));
            return mat;
        }
        
        public static LocRotScale lerp(LocRotScale a, LocRotScale b, float t) {
            LocRotScale result = new LocRotScale();
            result.offset = a.offset.copy();
            result.offset.lerp(b.offset, t);
            result.rotation = a.rotation.copy();
            result.rotation.lerp(b.rotation, t);
            return result;
        }
        
        public void translate(Vector3f offset) {
            this.offset.add(offset);
        }
        public void rotate(Vector3f rotation) {
            this.rotation.add(rotation);
        }
        public void setColor(Vector4f color) {
            this.color = color;
        }
    }
    
    Map<String, LocRotScale> poseMap = Maps.newHashMap();

    public LocRotScale getOrCreateLocal(String boneName) {
        if (poseMap.containsKey(boneName))
            return poseMap.get(boneName);
        LocRotScale result = new LocRotScale();
        poseMap.put(boneName, result);
        return result;
    }
    
    public LocRotScale getLocal(String boneName) {
        return poseMap.get(boneName);
    }
    
    static public Pose lerpPose(Pose last, Pose next, float t) {
        Pose result = new Pose();
        ImmutableSet<String> bones = ImmutableSet.<String>builder()
                .addAll(last.poseMap.keySet())
                .addAll(next.poseMap.keySet())
                .build();
        for (String boneName : bones) {
            LocRotScale lastPose = last.poseMap.get(boneName);
            LocRotScale nextPose = next.poseMap.get(boneName);
            if (lastPose == null) {
                result.poseMap.put(boneName, nextPose);
            } else if (nextPose == null) {
                result.poseMap.put(boneName, lastPose);
            } else {
                result.poseMap.put(boneName, LocRotScale.lerp(lastPose,nextPose,t));
            }
        }
        return result;
    }
}
