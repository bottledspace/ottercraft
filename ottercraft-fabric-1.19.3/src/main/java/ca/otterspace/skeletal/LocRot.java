package ca.otterspace.skeletal;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class LocRot {
    public Vector3f offset = new Vector3f(0.0f,0.0f,0.0f);
    public Quaternionf rotation = new Quaternionf(0.0f,0.0f,0.0f,1.0f);
    
    public LocRot() {}
    public LocRot(Vector3f offset, Quaternionf rotation) {
        this.offset = offset;
        this.rotation = rotation;
    }
    
    public Matrix4f getMatrix() {
        Matrix4f mat = new Matrix4f();
        mat.translate(offset.x()/16f, offset.y()/16f, offset.z()/16f);
        mat.rotate(rotation);
        //mat.multiply(Vector3f.ZP.rotation(rotation.z()));
        //mat.multiply(Vector3f.YP.rotation(rotation.y()));
        //mat.multiply(Vector3f.XP.rotation(rotation.x()));
        return mat;
    }
    
    public static LocRot lerp(LocRot a, LocRot b, float t) {
        LocRot result = new LocRot();
        result.offset = new Vector3f(a.offset);
        result.offset.lerp(b.offset, t);
        result.rotation = new Quaternionf(a.rotation);
        result.rotation.slerp(b.rotation, t);
        return result;
    }
}