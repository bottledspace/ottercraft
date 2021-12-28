package ca.otterspace.anim;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.geom.PartPose;

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
            Vector3f position = Vector3f.ZERO;
            if (positionCurves.get(boneName) != null)
                position = positionCurves.get(boneName).at(time);
            Vector3f rotation = Vector3f.ZERO;
            if (rotationCurves.get(boneName) != null)
                rotation = rotationCurves.get(boneName).at(time);
            pose.poseMap.put(boneName, PartPose.offsetAndRotation(
                    position.x(), position.y(), position.z(),
                    rotation.x(), rotation.y(), rotation.z()));
        }
        return pose;
    }
}
