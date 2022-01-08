package ca.otterspace.skeletal;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Map;

public class Animation {
    boolean loop;
    float length;
    Map<String, LinearCurve> positionCurves = Maps.newHashMap();
    Map<String, LinearCurve> rotationCurves = Maps.newHashMap();
    
    Pose at(float time) {
        time = time % length;
        
        Pose pose = new Pose();
        ImmutableSet<String> bones = ImmutableSet.<String>builder()
                .addAll(positionCurves.keySet())
                .addAll(rotationCurves.keySet())
                .build();
        for (String boneName : bones) {
            Vector3f position = new Vector3f(0,0,0);
            if (positionCurves.get(boneName) != null)
                position = positionCurves.get(boneName).at(time);
            Vector3f rotation = new Vector3f(0,0,0);
            if (rotationCurves.get(boneName) != null)
                rotation = rotationCurves.get(boneName).at(time);
            
            // Convert Euler angles to Quaternion
            Quaternion quat = Quaternion.ONE.copy();
            quat.mul(Vector3f.ZP.rotation(rotation.z()));
            quat.mul(Vector3f.YP.rotation(rotation.y()));
            quat.mul(Vector3f.XP.rotation(rotation.x()));
            
            pose.poseMap.put(boneName, new LocRot(position, quat));
        }
        return pose;
    }
}
