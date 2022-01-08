package ca.otterspace.skeletal;

import net.minecraft.util.math.vector.Vector3f;

import java.util.Map;
import java.util.TreeMap;

public class LinearCurve {
    TreeMap<Float, Vector3f> points = new TreeMap<>();
    
    public void put(float time, Vector3f point) {
        points.put(time, point);
    }
    
    public Vector3f at(float t) {
        Map.Entry<Float, Vector3f> a = points.floorEntry(t);
        Map.Entry<Float, Vector3f> b = points.ceilingEntry(t);
        if (a == null) {
            return b.getValue();
        } else if (b == null) {
            return a.getValue();
        } else if (a.equals(b)) {
            return a.getValue();
        } else {
            float t1 = (t - a.getKey()) / (b.getKey() - a.getKey());
            Vector3f result = a.getValue().copy();
            result.lerp(b.getValue(), t1);
            return result;
        }
    }
    
}
