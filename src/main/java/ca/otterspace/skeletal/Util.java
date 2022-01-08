package ca.otterspace.skeletal;

import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class Util {
    public static float DEG_TO_RAD = 0.01745329251f;
    public static float RAD_TO_DEG = 57.2957795131f;

    public static Quaternion fromXYZ(float f, float g, float h) {
        Quaternion quaternion = Quaternion.ONE.copy();
        quaternion.mul(new Quaternion((float)Math.sin(f / 2.0f), 0.0f, 0.0f, (float)Math.cos(f / 2.0f)));
        quaternion.mul(new Quaternion(0.0f, (float)Math.sin(g / 2.0f), 0.0f, (float)Math.cos(g / 2.0f)));
        quaternion.mul(new Quaternion(0.0f, 0.0f, (float)Math.sin(h / 2.0f), (float)Math.cos(h / 2.0f)));
        return quaternion;
    }
    public static Quaternion fromXYZ(Vector3f rotation) {
        return fromXYZ(rotation.x(), rotation.y(), rotation.z());
    }

    public static Quaternion slerp(Quaternion q0, Quaternion q1, float t) {
        /* Adapted from https://cs.stanford.edu/~acoates/quaternion.h */
        float omega = (float)Math.acos(
                q0.i()*q1.i() +
                q0.j()*q1.j() +
                q0.k()*q1.k() +
                q0.r()*q1.r());
        if (Math.abs(omega) < 1e-10)
            omega = 1e-10f;
        
        float som = (float)Math.sin(omega);
        float st0 = (float)Math.sin((1-t) * omega) / som;
        float st1 = (float)Math.sin(t * omega) / som;
        
        return new Quaternion(q0.i()*st0 + q1.i()*st1,
                q0.j()*st0 + q1.j()*st1,
                q0.k()*st0 + q1.k()*st1,
                q0.r()*st0 + q1.r()*st1);
    }
    
    public static Quaternion lookAt(Vector3f F) {
        /* Ported from https://stackoverflow.com/questions/52413464/52551983#52551983 */
        F.normalize();
        Vector3f R = Vector3f.YP.copy();
        R.cross(F);
        R.normalize();
        Vector3f U = F.copy();
        U.cross(R);
        
        float x,y,z,w;
        float trace = R.x() + U.y() + F.z();
        if (trace > 0.0f) {
            float s = 0.5f / (float)Math.sqrt(trace + 1.0f);
            w = 0.25f / s;
            x = (U.z() - F.y()) * s;
            y = (F.x() - R.z()) * s;
            z = (R.y() - U.x()) * s;
        } else {
            if (R.x() > U.y() && R.x() > F.z()) {
                float s = 2.0f * (float)Math.sqrt(1.0f + R.x() - U.y() - F.z());
                w = (U.z() - F.y()) / s;
                x = 0.25f * s;
                y = (U.x() + R.y()) / s;
                z = (F.x() + R.z()) / s;
            } else if (U.y() > F.z()) {
                float s = 2.0f * (float)Math.sqrt(1.0f + U.y() - R.x() - F.z());
                w = (F.x() - R.z()) / s;
                x = (U.x() + R.y()) / s;
                y = 0.25f * s;
                z = (F.y() + U.z()) / s;
            } else {
                float s = 2.0f * (float)Math.sqrt(1.0f + F.z() - R.x() - U.y());
                w = (R.y() - U.x()) / s;
                x = (F.x() + R.z()) / s;
                y = (F.y() + U.z()) / s;
                z = 0.25f * s;
            }
        }
        return new Quaternion(x,y,z,w);
    }
}
