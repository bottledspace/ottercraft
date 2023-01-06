package ca.otterspace.skeletal;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Util {
    public static Quaternionf slerp(Quaternionf q0, Quaternionf q1, float t) {
        /* Adapted from https://cs.stanford.edu/~acoates/quaternion.h */
        float omega = (float)Math.acos(
                q0.x()*q1.x() +
                q0.y()*q1.y() +
                q0.z()*q1.z() +
                q0.w()*q1.w());
        if (Math.abs(omega) < 1e-10)
            omega = 1e-10f;
        
        float som = (float)Math.sin(omega);
        float st0 = (float)Math.sin((1-t) * omega) / som;
        float st1 = (float)Math.sin(t * omega) / som;
        
        return new Quaternionf(q0.x()*st0 + q1.x()*st1,
                q0.y()*st0 + q1.y()*st1,
                q0.z()*st0 + q1.z()*st1,
                q0.w()*st0 + q1.w()*st1);
    }
    
    public static Quaternionf lookAt(Vector3f F) {
        /* Ported from https://stackoverflow.com/questions/52413464/52551983#52551983 */
        F.normalize();
        Vector3f R = new Vector3f(0.0f,1.0f,0.0f);
        R.cross(F);
        R.normalize();
        Vector3f U = new Vector3f(F);
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
        return new Quaternionf(x,y,z,w);
    }
}
