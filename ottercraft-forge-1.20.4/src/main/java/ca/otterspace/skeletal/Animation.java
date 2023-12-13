package ca.otterspace.skeletal;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import org.joml.Vector3f;
import org.joml.Quaternionf;

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
            Vector3f position = new Vector3f(0.0f,0.0f,0.0f);
            if (positionCurves.get(boneName) != null)
                position = positionCurves.get(boneName).at(time);
            Vector3f rotation = new Vector3f(0.0f,0.0f,0.0f);
            if (rotationCurves.get(boneName) != null)
                rotation = rotationCurves.get(boneName).at(time);
            
            // Convert Euler angles to Quaternion
            Quaternionf quat = new Quaternionf(0.0f,0.0f,0.0f,1.0f);
            quat.rotateZ(rotation.z());
            quat.rotateY(rotation.y());
            quat.rotateX(rotation.x());

            pose.poseMap.put(boneName, new LocRot(position, quat));
        }
        return pose;
    }
}
