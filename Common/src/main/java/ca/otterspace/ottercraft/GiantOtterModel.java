package ca.otterspace.ottercraft;

import ca.otterspace.anim.Animation;
import ca.otterspace.anim.AnimationController;
import ca.otterspace.anim.Animations;
import ca.otterspace.anim.Geometry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class GiantOtterModel extends EntityModel<GiantOtter> {
    private static final Geometry geometry = Geometry.loadGeometry(new ResourceLocation(Ottercraft.MODID, "geo/otter.geo.json"));
    private static final Animations animations = Animations.loadAnimations(new ResourceLocation(Ottercraft.MODID, "animations/otter.animation.json"));
    
    private static final ca.otterspace.anim.Animation ANIMATION_RUN = animations.getAnimation("animation.otter.run");
    
    //private final List<ModelPart> chain;
    private final ModelPart root;
    private final ca.otterspace.anim.Model model;
    
    public GiantOtterModel(ModelPart root) {
        this.model = geometry.compileModel(root);
        this.root = root;
        /*chain = new ArrayList<ModelPart>();
        for (int i = 0; i < 9; i++)
            chain.add(this.root.getChild(String.format("%d", i)));*/
    }
    
    protected static float []sz = { 7,13,14, 12,9,10, 11,12,8 };
    //protected static float []sxy = { 7,6,8, 12,10,8, 6,4,2 };
    
    public static LayerDefinition getTexturedModelData() {
        /*MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition root = meshdefinition.getRoot();
        for (int i = 0; i < 9; i++) {
            root.addOrReplaceChild(String.format("%d",i),
                    CubeListBuilder.create().texOffs(0, 0)
                            .addBox(-sxy[i]/2, -sxy[i]/2, -1, sxy[i], sxy[i], sz[i]+1, new CubeDeformation(0.0F)),
                    i==0? PartPose.offset(0,24,0) : PartPose.offset(0.0F, 0,sz[i-1]));
        }*/
        return geometry.createLayerDefinition();
    }
    
    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
    
    @Override
    public void prepareMobModel(GiantOtter entity, float f, float g, float h) {
    }
    
    @Override
    public void setupAnim(GiantOtter entity, float f, float g, float h, float i_, float j) {
        /*float dt = Minecraft.getInstance().getFrameTime();
        if (entity.tail == null) {
            entity.tail = new Tail(sz);
            entity.tail.init((float)entity.getX(), (float)entity.getY(), (float)entity.getZ());
        }
        entity.tail.apply(dt, chain);*/
        //animController.apply();
    }
}
