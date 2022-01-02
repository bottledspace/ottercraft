package ca.otterspace.skeletal;

import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

public class LocRot {
    public Vector3f offset = Vector3f.ZERO.copy();
    public Quaternion rotation = Quaternion.ONE.copy();
    
    public LocRot() {}
    public LocRot(Vector3f offset, Quaternion rotation) {
        this.offset = offset;
        this.rotation = rotation;
    }
    
    public Matrix4f getMatrix() {
        Matrix4f mat = Matrix4f.createScaleMatrix(1, 1, 1);
        mat.multiply(Matrix4f.createTranslateMatrix(offset.x()/16f, offset.y()/16f, offset.z()/16f));
        mat.multiply(rotation);
        //mat.multiply(Vector3f.ZP.rotation(rotation.z()));
        //mat.multiply(Vector3f.YP.rotation(rotation.y()));
        //mat.multiply(Vector3f.XP.rotation(rotation.x()));
        return mat;
    }
    
    public static LocRot lerp(LocRot a, LocRot b, float t) {
        LocRot result = new LocRot();
        result.offset = a.offset.copy();
        result.offset.lerp(b.offset, t);
        result.rotation = Util.slerp(a.rotation,b.rotation,t);
        return result;
    }
}