package ca.otterspace.skeletal;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import java.util.Map;

public class Pose {
    Map<String, LocRot> poseMap = Maps.newHashMap();
    
    public LocRot getLocal(String boneName) {
        return poseMap.get(boneName);
    }
    
    static public Pose lerpPose(Pose last, Pose next, float t) {
        Pose result = new Pose();
        ImmutableSet<String> bones = ImmutableSet.<String>builder()
                .addAll(last.poseMap.keySet())
                .addAll(next.poseMap.keySet())
                .build();
        for (String boneName : bones) {
            LocRot lastPose = last.poseMap.get(boneName);
            LocRot nextPose = next.poseMap.get(boneName);
            if (lastPose == null) {
                result.poseMap.put(boneName, nextPose);
            } else if (nextPose == null) {
                result.poseMap.put(boneName, lastPose);
            } else {
                result.poseMap.put(boneName, LocRot.lerp(lastPose,nextPose,t));
            }
        }
        return result;
    }
}
