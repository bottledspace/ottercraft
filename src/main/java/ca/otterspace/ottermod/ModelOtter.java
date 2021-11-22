package ca.otterspace.ottermod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ModelOtter extends AnimatedGeoModel<EntityOtter> {
    @Override
    public ResourceLocation getModelLocation(EntityOtter object) {
        return new ResourceLocation("ottermod", "geo/otter.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityOtter object) {
        if (object.isTame())
            return new ResourceLocation("ottermod", "textures/entity/otter_tame.png");
        else
            return new ResourceLocation("ottermod", "textures/entity/otter.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityOtter object) {
        return new ResourceLocation("ottermod", "animations/otter.animation.json");
    }

    @Override
    public void setLivingAnimations(EntityOtter entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        LivingEntity entityIn = (LivingEntity) entity;
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (!entity.isBegging()) {
            head.setRotationX(head.getRotationX() + extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(head.getRotationY() + extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}