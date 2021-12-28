package ca.otterspace.anim;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.util.Mth;

import java.util.Map;

public class Pose {
    Map<String, PartPose> poseMap = Maps.newHashMap();
    
    static Pose lerpPose(Pose last, Pose next, float t) {
        Pose result = new Pose();
        ImmutableSet<String> bones = ImmutableSet.<String>builder()
                .addAll(last.poseMap.keySet())
                .addAll(next.poseMap.keySet())
                .build();
        for (String boneName : bones) {
            PartPose lastPose = last.poseMap.get(boneName);
            PartPose nextPose = next.poseMap.get(boneName);
            if (lastPose == null) {
                result.poseMap.put(boneName, nextPose);
            } else if (nextPose == null) {
                result.poseMap.put(boneName, lastPose);
            } else {
                PartPose pose = PartPose.offsetAndRotation(
                        Mth.lerp(t, lastPose.x, nextPose.x),
                        Mth.lerp(t, lastPose.y, nextPose.y),
                        Mth.lerp(t, lastPose.z, nextPose.z),
                        Mth.lerp(t, lastPose.xRot, nextPose.xRot),
                        Mth.lerp(t, lastPose.yRot, nextPose.yRot),
                        Mth.lerp(t, lastPose.zRot, nextPose.zRot));
                result.poseMap.put(boneName, pose);
            }
        }
        return result;
    }
}
