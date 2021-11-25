package ca.otterspace.ottercraft;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ModelOtter extends AnimatedGeoModel<EntityOtter> {
    @Override
    public ResourceLocation getModelLocation(EntityOtter object) {
        return new ResourceLocation(Ottercraft.MODID, "geo/otter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityOtter object) {
        if (object.isTame())
            return new ResourceLocation(Ottercraft.MODID, "textures/entity/otter_tame.png");
        else
            return new ResourceLocation(Ottercraft.MODID, "textures/entity/otter.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityOtter object) {
        return new ResourceLocation(Ottercraft.MODID, "animations/otter.animation.json");
    }

    protected void animateTail(GeoBone bone, float angleX, float angleY) {
        while (bone != null) {
            bone.setRotationY(bone.getRotationY() + angleY);
            bone.setRotationX(bone.getRotationX() + angleX);

            angleX /= 2.0f;
            angleY /= 2.0f;

            if (bone.childBones.isEmpty())
                bone = null;
            else
                bone = bone.childBones.get(0);
        }
    }

    @Override
    public void setLivingAnimations(EntityOtter entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (Minecraft.getInstance().isPaused())
            return;

        IBone head = this.getAnimationProcessor().getBone("head");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        extraData.netHeadYaw = (float)MathHelper.clamp(extraData.netHeadYaw, -Math.PI/4,Math.PI/4);
        if (entity.isBegging() || entity.isPassenger()) {
            head.setRotationY((float) (Math.PI));
            head.setRotationX((float) (Math.PI / 2.0 + Math.toRadians(extraData.headPitch)));
            head.setRotationZ((float) (Math.PI - Math.toRadians(extraData.netHeadYaw)));
        } else {
            head.setRotationX((float) Math.toRadians(extraData.headPitch));
            head.setRotationY((float) Math.toRadians(extraData.netHeadYaw));
        }

        if (entity.isInWater()) {
            // Tilt body up and down visually when in water
            IBone root = this.getAnimationProcessor().getBone("root");
            double dx = entity.getDeltaMovement().x;
            double dz = entity.getDeltaMovement().z;
            float angle = (float) (MathHelper.atan2(entity.getDeltaMovement().y, MathHelper.sqrt(dx * dx + dz * dz)));
            angle = (float) MathHelper.clamp(angle, -Math.PI / 4.0, Math.PI / 4.0);
            root.setRotationX(angle + root.getRotationX());
        }

        // Wag tail
        float wagAmplitude;
        if (entity.isBegging())
            wagAmplitude = 0.4f;
        else if (entity.isInWater() && entity.getDeltaMovement().lengthSqr() > 0.001f)
            wagAmplitude = 0.3f;
        else
            wagAmplitude = 0.05f;
        GeoBone bone = this.getModel(getModelLocation(entity)).getBone("tail").orElse(null);
        this.animateTail(bone, 0, MathHelper.cos((float) getCurrentTick() * 0.3331f) * wagAmplitude);
        bone.isHidden = entity.isPassenger();
    }
}