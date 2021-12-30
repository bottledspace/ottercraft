package ca.otterspace.skeletal;

import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

public class LocRot {
    Vector3f offset = Vector3f.ZERO.copy();
    Vector3f rotation = Vector3f.ZERO.copy();
    
    public LocRot() {}
    public LocRot(Vector3f offset, Vector3f rotation) {
        this.offset = offset;
        this.rotation = rotation;
    }
    
    public Matrix4f getMatrix() {
        Matrix4f mat = Matrix4f.createScaleMatrix(1, 1, 1);
        mat.multiply(Matrix4f.createTranslateMatrix(offset.x()/16f, offset.y()/16f, offset.z()/16f));
        mat.multiply(Vector3f.ZP.rotation(rotation.z()));
        mat.multiply(Vector3f.YP.rotation(rotation.y()));
        mat.multiply(Vector3f.XP.rotation(rotation.x()));
        return mat;
    }
    
    public static LocRot lerp(LocRot a, LocRot b, float t) {
        LocRot result = new LocRot();
        result.offset = a.offset.copy();
        result.offset.lerp(b.offset, t);
        result.rotation = a.rotation.copy();
        result.rotation.lerp(b.rotation, t);
        return result;
    }
    
    public void translate(Vector3f offset) {
        this.offset.add(offset);
    }
    public void rotate(Vector3f rotation) {
        this.rotation.add(rotation);
    }
}