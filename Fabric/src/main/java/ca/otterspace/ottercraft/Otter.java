package ca.otterspace.ottercraft;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.IAnimatable;

public class Otter extends AbstractOtter implements IAnimatable {
    Otter(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }
    
    private final AnimationFactory factory = new AnimationFactory(this);
    
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    
    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        if (this.isInWater())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.slide", true));
        else if (event.isMoving())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.run", true));
        else if (this.isInSittingPose())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.sit", true));
        else if (this.isBegging() || this.isPassenger())
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.beg", true));
        else
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otter.idle", true));
        return PlayState.CONTINUE;
    }
    
    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<Otter> controller = new AnimationController<>(this, "controller", 3, this::animationPredicate);
        data.addAnimationController(controller);
    }
    
    
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob parent) {
        return OttercraftCommon.OTTER.create(world);
    }
    
    
    @Override
    protected SoundEvent getAmbientSound() {
        return OttercraftClient.OTTER_SQUEAK;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return OttercraftClient.OTTER_ANGRY;
    }
    
}