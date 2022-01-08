package ca.otterspace.skeletal;

import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class LocRot {
    public Vector3f offset = new Vector3f(0,0,0);
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