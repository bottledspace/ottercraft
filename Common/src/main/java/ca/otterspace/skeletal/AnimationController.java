package ca.otterspace.skeletal;

import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.tuple.MutablePair;


public class AnimationController {
    MutablePair<String,Float> last = null;
    MutablePair<String,Float> next = null;
    boolean transition = false;
    
    public void setAnimation(String anim) {
        if (this.next != null && this.next.left.equals(anim))
            return; // Setting to the same animation, ignore!
        last = next;
        next = new MutablePair<>(anim, 0.0f);
        if (last == null)
            last = next;
        transition = true;
    }
    
    public String getAnimation() {
        return (last == null)? "" : last.left;
    }
    
    public void tick() {
        if (transition) {
            transition = false;
        } else {
            float dt = 0.05f;
            last = next;
            next = new MutablePair<>(next.left, next.right+dt);
            if (last == null)
                last = next;
        }
    }
    
    public Pose apply(Animations animations) {
        float dt = Minecraft.getInstance().getFrameTime();
        if (next != null && last != null) {
            Pose a = animations.getAnimation(last.left).at(last.right);
            Pose b = animations.getAnimation(next.left).at(next.right);
            return Pose.lerpPose(a, b, dt);
        }
        else return new Pose();
    }
}
