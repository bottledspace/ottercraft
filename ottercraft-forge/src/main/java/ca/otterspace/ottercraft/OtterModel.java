package ca.otterspace.ottercraft;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) class OtterModel<T extends OtterEntity> extends EntityModel<T> {
    protected ModelRenderer ArmL;
    protected ModelRenderer ArmR;
    protected ModelRenderer Chest;
    protected ModelRenderer EarL;
    protected ModelRenderer EarR;
    protected ModelRenderer FootBL;
    protected ModelRenderer FootL;
    protected ModelRenderer FootR;
    protected ModelRenderer Head;
    protected ModelRenderer LowerLegBR;
    protected ModelRenderer Neck;
    protected ModelRenderer Nose;
    protected ModelRenderer Tail1;
    protected ModelRenderer Tail2;
    protected ModelRenderer Tail3;
    protected ModelRenderer Tail4;
    protected ModelRenderer Tail5;
    protected ModelRenderer Torso;
    protected ModelRenderer UpperLegBL;
    protected ModelRenderer FootBR;
    protected ModelRenderer LowerLegBL;
    protected ModelRenderer UpperLegBR;
    private void init() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.ArmL = new ModelRenderer(this, 166, 82);
        this.ArmR = new ModelRenderer(this, 137, 82);
        this.Chest = new ModelRenderer(this, 198, 77);
        this.EarL = new ModelRenderer(this, 199, 189);
        this.EarR = new ModelRenderer(this, 237, 190);
        this.FootBL = new ModelRenderer(this, 91, 193);
        this.FootL = new ModelRenderer(this, 140, 112);
        this.FootR = new ModelRenderer(this, 169, 112);
        this.Head = new ModelRenderer(this, 206, 149);
        this.Neck = new ModelRenderer(this, 203, 115);
        this.Nose = new ModelRenderer(this, 217, 177);
        this.Tail1 = new ModelRenderer(this, 0, 0);
        this.Tail2 = new ModelRenderer(this, 63, 30);
        this.Tail3 = new ModelRenderer(this, 43, 69);
        this.Tail4 = new ModelRenderer(this, 106, 10);
        this.Tail5 = new ModelRenderer(this, 16, 33);
        this.Torso = new ModelRenderer(this, 8, 217);
        this.UpperLegBL = new ModelRenderer(this, 99, 81);
        this.FootBR = new ModelRenderer(this, 92, 222);
        this.LowerLegBL = new ModelRenderer(this, 92, 164);
        this.UpperLegBR = new ModelRenderer(this, 98, 106);
        this.LowerLegBR = new ModelRenderer(this, 91, 137);

        //this.ArmL = new ModelRenderer(this);
        this.ArmL.addBox(-2.50f,-5.50f,-2.50f,5.00f,11.00f,5.00f);
        //this.ArmR = new ModelRenderer(this);
        this.ArmR.addBox(-2.50f,-5.50f,-2.50f,5.00f,11.00f,5.00f);
        //this.Chest = new ModelRenderer(this);
        this.Chest.addBox(-5.50f,-5.50f,-9.00f,11.00f,11.00f,18.00f);
        //this.EarL = new ModelRenderer(this);
        this.EarL.addBox(-2.00f,-2.00f,-1.50f,4.00f,4.00f,3.00f);
        //this.EarR = new ModelRenderer(this);
        this.EarR.addBox(-2.00f,-2.00f,-1.50f,4.00f,4.00f,3.00f);
        //this.FootBL = new ModelRenderer(this);
        this.FootBL.addBox(-2.50f,-2.00f,-7.00f,5.00f,4.00f,14.00f);
        //this.FootL = new ModelRenderer(this);
        this.FootL.addBox(-2.00f,-7.00f,-2.00f,4.00f,14.00f,4.00f);
        //this.FootR = new ModelRenderer(this);
        this.FootR.addBox(-2.00f,-7.00f,-2.00f,4.00f,14.00f,4.00f);
        //this.Head = new ModelRenderer(this);
        this.Head.addBox(-5.00f,-4.50f,-4.50f,10.00f,9.00f,9.00f);
        //this.LowerLegBR = new ModelRenderer(this);
        this.LowerLegBR.addBox(-2.50f,-2.50f,-7.00f,5.00f,5.00f,14.00f);
        //this.Neck = new ModelRenderer(this);
        this.Neck.addBox(-4.50f,-4.50f,-7.00f,9.00f,9.00f,14.00f);
        //this.Nose = new ModelRenderer(this);
        this.Nose.addBox(-3.00f,-3.00f,-1.50f,6.00f,6.00f,3.00f);
        this.Nose.mirror = true;
        //this.Tail1 = new ModelRenderer(this);
        this.Tail1.addBox(-5.00f,-5.00f,-6.00f,10.00f,10.00f,12.00f);
        //this.Tail2 = new ModelRenderer(this);
        this.Tail2.addBox(-4.00f,-4.00f,-7.00f,8.00f,8.00f,14.00f);
        //this.Tail3 = new ModelRenderer(this);
        this.Tail3.addBox(-3.00f,-3.00f,-6.00f,6.00f,6.00f,12.00f);
        //this.Tail4 = new ModelRenderer(this);
        this.Tail4.addBox(-2.00f,-2.00f,-5.00f,4.00f,4.00f,10.00f);
        //this.Tail5 = new ModelRenderer(this);
        this.Tail5.addBox(-1.00f,-1.00f,-5.00f,2.00f,2.00f,10.00f);
        //this.Torso = new ModelRenderer(this);
        this.Torso.addBox(-7.00f,-7.00f,-9.00f,14.00f,14.00f,18.00f);

        //this.UpperLegBL = new ModelRenderer(this);
        this.UpperLegBL.addBox(-3.00f,-6.00f,-3.00f,6.00f,12.00f,6.00f);
        
        //this.FootBR = new ModelRenderer(this);
        this.FootBR.addBox(-2.50f,-2.00f,-7.00f,5.00f,4.00f,14.00f);
        //this.LowerLegBL = new ModelRenderer(this);
        this.LowerLegBL.addBox(-2.50f,-2.50f,-7.00f,5.00f,5.00f,14.00f);
        //this.UpperLegBR = new ModelRenderer(this);
        this.UpperLegBR.addBox(-3.00f,-6.00f,-3.00f,6.00f,12.00f,6.00f);
    }
    public void render(MatrixStack ms, IVertexBuilder b, int f, int g, float red, float green, float blue, float alpha) {
        this.ArmL.render(ms, b, f, g, red, green, blue, alpha);
        this.ArmR.render(ms, b, f, g, red, green, blue, alpha);
        this.Chest.render(ms, b, f, g, red, green, blue, alpha);
        this.EarL.render(ms, b, f, g, red, green, blue, alpha);
        this.EarR.render(ms, b, f, g, red, green, blue, alpha);
        this.FootBL.render(ms, b, f, g, red, green, blue, alpha);
        this.FootL.render(ms, b, f, g, red, green, blue, alpha);
        this.FootR.render(ms, b, f, g, red, green, blue, alpha);
        this.Head.render(ms, b, f, g, red, green, blue, alpha);
        this.LowerLegBR.render(ms, b, f, g, red, green, blue, alpha);
        this.Neck.render(ms, b, f, g, red, green, blue, alpha);
        this.Nose.render(ms, b, f, g, red, green, blue, alpha);
        this.Tail1.render(ms, b, f, g, red, green, blue, alpha);
        this.Tail2.render(ms, b, f, g, red, green, blue, alpha);
        this.Tail3.render(ms, b, f, g, red, green, blue, alpha);
        this.Tail4.render(ms, b, f, g, red, green, blue, alpha);
        this.Tail5.render(ms, b, f, g, red, green, blue, alpha);
        this.Torso.render(ms, b, f, g, red, green, blue, alpha);
        this.UpperLegBL.render(ms, b, f, g, red, green, blue, alpha);
        this.FootBR.render(ms, b, f, g, red, green, blue, alpha);
        this.LowerLegBL.render(ms, b, f, g, red, green, blue, alpha);
        this.UpperLegBR.render(ms, b, f, g, red, green, blue, alpha);
    }
    protected void poseRun(int frame) {
        final float[] ArmL_rotpos = new float[]{
                5.61f,-13.15f,10.91f,1.55f,-0.00f,-0.00f,
                5.61f,-13.36f,12.16f,1.47f,-0.00f,-0.00f,
                5.61f,-13.61f,13.42f,1.33f,-0.00f,-0.00f,
                5.61f,-13.88f,14.06f,1.10f,-0.00f,-0.00f,
                5.61f,-14.31f,13.57f,0.78f,-0.00f,-0.00f,
                5.61f,-14.99f,12.14f,0.38f,-0.00f,-0.00f,
                5.61f,-15.70f,10.78f,-0.04f,-0.00f,-0.00f,
                5.61f,-15.96f,9.80f,-0.37f,-0.00f,-0.00f,
                5.61f,-15.81f,9.06f,-0.58f,-0.00f,-0.00f,
                5.61f,-15.64f,8.57f,-0.66f,-0.00f,-0.00f,
                5.61f,-15.77f,7.92f,-0.57f,-0.00f,-0.00f,
                5.61f,-15.83f,6.87f,-0.30f,-0.00f,-0.00f,
                5.61f,-15.19f,6.17f,0.13f,-0.00f,-0.00f,
                5.61f,-14.08f,6.73f,0.64f,-0.00f,-0.00f,
                5.61f,-13.30f,8.16f,1.07f,-0.00f,-0.00f,
                5.61f,-13.01f,9.31f,1.33f,-0.00f,-0.00f,
                5.61f,-13.00f,9.96f,1.47f,-0.00f,-0.00f,
                5.61f,-13.03f,10.25f,1.54f,-0.00f,-0.00f,
                5.61f,-13.04f,10.34f,1.57f,-0.00f,-0.00f,
                5.61f,-13.04f,10.34f,1.58f,-0.00f,-0.00f,
        };
        this.ArmL.rotateAngleX   = ArmL_rotpos[frame*6+3];
        this.ArmL.rotateAngleY   = ArmL_rotpos[frame*6+5];
        this.ArmL.rotateAngleZ   = ArmL_rotpos[frame*6+4];
        this.ArmL.rotationPointX = ArmL_rotpos[frame*6];
        this.ArmL.rotationPointY = 24-ArmL_rotpos[frame*6+2];
        this.ArmL.rotationPointZ = ArmL_rotpos[frame*6+1];
        final float[] ArmR_rotpos = new float[]{
                -5.61f,-13.15f,10.91f,1.55f,0.00f,3.14f,
                -5.61f,-13.36f,12.16f,1.47f,0.00f,3.14f,
                -5.61f,-13.61f,13.42f,1.33f,0.00f,3.14f,
                -5.61f,-13.88f,14.06f,1.10f,0.00f,3.14f,
                -5.61f,-14.31f,13.57f,0.78f,0.00f,3.14f,
                -5.61f,-14.99f,12.14f,0.38f,0.00f,3.14f,
                -5.61f,-15.70f,10.78f,-0.04f,0.00f,3.14f,
                -5.61f,-15.96f,9.80f,-0.37f,0.00f,3.14f,
                -5.61f,-15.81f,9.06f,-0.58f,0.00f,3.14f,
                -5.61f,-15.64f,8.57f,-0.66f,0.00f,3.14f,
                -5.61f,-15.77f,7.92f,-0.57f,0.00f,3.14f,
                -5.61f,-15.83f,6.87f,-0.30f,0.00f,3.14f,
                -5.61f,-15.19f,6.17f,0.13f,0.00f,3.14f,
                -5.61f,-14.08f,6.73f,0.64f,0.00f,3.14f,
                -5.61f,-13.30f,8.16f,1.07f,0.00f,3.14f,
                -5.61f,-13.01f,9.31f,1.33f,0.00f,3.14f,
                -5.61f,-13.00f,9.96f,1.47f,0.00f,3.14f,
                -5.61f,-13.03f,10.25f,1.54f,0.00f,3.14f,
                -5.61f,-13.04f,10.34f,-1.57f,3.14f,-0.00f,
                -5.61f,-13.04f,10.34f,-1.56f,3.14f,-0.00f,
        };
        this.ArmR.rotateAngleX   = ArmR_rotpos[frame*6+3];
        this.ArmR.rotateAngleY   = ArmR_rotpos[frame*6+5];
        this.ArmR.rotateAngleZ   = ArmR_rotpos[frame*6+4];
        this.ArmR.rotationPointX = ArmR_rotpos[frame*6];
        this.ArmR.rotationPointY = 24-ArmR_rotpos[frame*6+2];
        this.ArmR.rotationPointZ = ArmR_rotpos[frame*6+1];
        final float[] Chest_rotpos = new float[]{
                -0.00f,-10.59f,14.35f,-2.64f,-0.00f,-0.00f,
                -0.00f,-10.47f,14.34f,-2.65f,-0.00f,-0.00f,
                -0.00f,-10.34f,14.24f,-2.67f,-0.00f,-0.00f,
                -0.00f,-10.27f,14.02f,-2.71f,-0.00f,-0.00f,
                -0.00f,-10.32f,13.65f,-2.77f,-0.00f,-0.00f,
                -0.00f,-10.53f,13.25f,-2.86f,-0.00f,-0.00f,
                -0.00f,-10.82f,12.96f,-2.96f,-0.00f,-0.00f,
                -0.00f,-11.03f,12.77f,-3.05f,-0.00f,-0.00f,
                -0.00f,-11.12f,12.57f,-3.12f,-0.00f,-0.00f,
                -0.00f,-11.10f,12.27f,3.14f,-0.00f,-0.00f,
                -0.00f,-11.20f,12.12f,-3.12f,-0.00f,-0.00f,
                -0.00f,-11.44f,12.49f,-3.05f,-0.00f,-0.00f,
                -0.00f,-11.34f,13.24f,-2.97f,-0.00f,-0.00f,
                -0.00f,-10.81f,13.87f,-2.88f,-0.00f,-0.00f,
                -0.00f,-10.25f,13.87f,-2.79f,-0.00f,-0.00f,
                -0.00f,-10.04f,13.65f,-2.72f,-0.00f,-0.00f,
                -0.00f,-10.09f,13.74f,-2.68f,-0.00f,-0.00f,
                -0.00f,-10.31f,13.97f,-2.65f,-0.00f,-0.00f,
                -0.00f,-10.54f,14.22f,-2.64f,-0.00f,-0.00f,
                -0.00f,-10.66f,14.34f,-2.64f,-0.00f,-0.00f,
        };
        this.Chest.rotateAngleX   = Chest_rotpos[frame*6+3];
        this.Chest.rotateAngleY   = Chest_rotpos[frame*6+5];
        this.Chest.rotateAngleZ   = Chest_rotpos[frame*6+4];
        this.Chest.rotationPointX = Chest_rotpos[frame*6];
        this.Chest.rotationPointY = 24-Chest_rotpos[frame*6+2];
        this.Chest.rotationPointZ = Chest_rotpos[frame*6+1];
        final float[] EarL_rotpos = new float[]{
                4.78f,-28.19f,19.17f,0.02f,0.69f,0.01f,
                4.78f,-27.83f,20.09f,0.00f,0.69f,0.00f,
                4.78f,-27.28f,21.25f,-0.03f,0.69f,-0.02f,
                4.78f,-26.60f,22.39f,-0.08f,0.69f,-0.05f,
                4.78f,-25.94f,23.27f,-0.16f,0.68f,-0.10f,
                4.78f,-25.78f,23.54f,-0.24f,0.68f,-0.15f,
                4.78f,-26.20f,23.22f,-0.28f,0.67f,-0.18f,
                4.78f,-26.75f,22.59f,-0.30f,0.67f,-0.19f,
                4.78f,-27.16f,21.89f,-0.31f,0.67f,-0.20f,
                4.78f,-27.25f,21.34f,-0.31f,0.67f,-0.20f,
                4.78f,-27.60f,20.76f,-0.28f,0.67f,-0.18f,
                4.78f,-28.33f,20.09f,-0.20f,0.68f,-0.13f,
                4.78f,-28.76f,19.51f,-0.11f,0.69f,-0.07f,
                4.78f,-28.66f,18.75f,-0.01f,0.69f,-0.00f,
                4.78f,-28.34f,17.54f,0.08f,0.69f,0.05f,
                4.78f,-28.19f,16.76f,0.11f,0.69f,0.07f,
                4.78f,-28.16f,16.97f,0.10f,0.69f,0.07f,
                4.78f,-28.23f,17.68f,0.07f,0.69f,0.05f,
                4.78f,-28.30f,18.45f,0.04f,0.69f,0.02f,
                4.78f,-28.33f,18.80f,0.02f,0.69f,0.01f,
        };
        this.EarL.rotateAngleX   = EarL_rotpos[frame*6+3];
        this.EarL.rotateAngleY   = EarL_rotpos[frame*6+5];
        this.EarL.rotateAngleZ   = EarL_rotpos[frame*6+4];
        this.EarL.rotationPointX = EarL_rotpos[frame*6];
        this.EarL.rotationPointY = 24-EarL_rotpos[frame*6+2];
        this.EarL.rotationPointZ = EarL_rotpos[frame*6+1];
        final float[] EarR_rotpos = new float[]{
                -4.78f,-28.19f,19.17f,0.02f,-0.69f,-0.01f,
                -4.78f,-27.83f,20.09f,0.00f,-0.69f,-0.00f,
                -4.78f,-27.28f,21.25f,-0.03f,-0.69f,0.02f,
                -4.78f,-26.60f,22.39f,-0.08f,-0.69f,0.05f,
                -4.78f,-25.94f,23.27f,-0.16f,-0.68f,0.10f,
                -4.78f,-25.78f,23.54f,-0.24f,-0.68f,0.15f,
                -4.78f,-26.20f,23.22f,-0.28f,-0.67f,0.18f,
                -4.78f,-26.75f,22.59f,-0.30f,-0.67f,0.19f,
                -4.78f,-27.16f,21.89f,-0.31f,-0.67f,0.20f,
                -4.78f,-27.25f,21.34f,-0.31f,-0.67f,0.20f,
                -4.78f,-27.60f,20.76f,-0.28f,-0.67f,0.18f,
                -4.78f,-28.33f,20.09f,-0.20f,-0.68f,0.13f,
                -4.78f,-28.76f,19.51f,-0.11f,-0.69f,0.07f,
                -4.78f,-28.66f,18.75f,-0.01f,-0.69f,0.00f,
                -4.78f,-28.34f,17.54f,0.08f,-0.69f,-0.05f,
                -4.78f,-28.19f,16.76f,0.11f,-0.69f,-0.07f,
                -4.78f,-28.16f,16.97f,0.10f,-0.69f,-0.07f,
                -4.78f,-28.23f,17.68f,0.07f,-0.69f,-0.05f,
                -4.78f,-28.30f,18.45f,0.04f,-0.69f,-0.02f,
                -4.78f,-28.33f,18.80f,0.02f,-0.69f,-0.01f,
        };
        this.EarR.rotateAngleX   = EarR_rotpos[frame*6+3];
        this.EarR.rotateAngleY   = EarR_rotpos[frame*6+5];
        this.EarR.rotateAngleZ   = EarR_rotpos[frame*6+4];
        this.EarR.rotationPointX = EarR_rotpos[frame*6];
        this.EarR.rotationPointY = 24-EarR_rotpos[frame*6+2];
        this.EarR.rotationPointZ = EarR_rotpos[frame*6+1];
        final float[] FootBL_rotpos = new float[]{
                7.81f,-0.57f,1.00f,0.21f,-0.07f,0.26f,
                7.81f,0.80f,1.92f,0.19f,-0.06f,0.26f,
                7.81f,2.82f,2.98f,0.18f,-0.06f,0.26f,
                7.81f,5.31f,3.82f,0.23f,-0.08f,0.26f,
                7.81f,8.18f,4.11f,0.38f,-0.11f,0.25f,
                7.81f,11.88f,3.27f,0.70f,-0.19f,0.20f,
                7.81f,17.01f,2.20f,1.20f,-0.26f,0.08f,
                7.81f,22.45f,2.56f,1.75f,-0.26f,-0.06f,
                7.81f,26.08f,3.88f,2.14f,-0.22f,-0.16f,
                7.81f,27.15f,4.41f,2.29f,-0.20f,-0.19f,
                7.81f,26.18f,3.70f,2.22f,-0.21f,-0.17f,
                7.81f,23.78f,2.43f,2.03f,-0.24f,-0.13f,
                7.81f,20.13f,1.20f,1.73f,-0.27f,-0.06f,
                7.81f,15.97f,0.52f,1.40f,-0.27f,0.03f,
                7.81f,12.15f,0.15f,1.10f,-0.25f,0.11f,
                7.81f,8.58f,-0.19f,0.84f,-0.21f,0.17f,
                7.81f,5.09f,-0.20f,0.61f,-0.17f,0.21f,
                7.81f,2.00f,0.07f,0.41f,-0.12f,0.24f,
                7.81f,-0.23f,0.43f,0.28f,-0.09f,0.26f,
                7.81f,-1.07f,0.60f,0.23f,-0.08f,0.26f,
        };
        this.FootBL.rotateAngleX   = FootBL_rotpos[frame*6+3];
        this.FootBL.rotateAngleY   = FootBL_rotpos[frame*6+5];
        this.FootBL.rotateAngleZ   = FootBL_rotpos[frame*6+4];
        this.FootBL.rotationPointX = FootBL_rotpos[frame*6];
        this.FootBL.rotationPointY = 24-FootBL_rotpos[frame*6+2];
        this.FootBL.rotationPointZ = FootBL_rotpos[frame*6+1];
        final float[] FootL_rotpos = new float[]{
                5.61f,-9.17f,5.12f,3.09f,-0.00f,-0.00f,
                5.61f,-10.53f,6.21f,2.89f,-0.00f,-0.00f,
                5.61f,-12.39f,7.51f,2.61f,-0.00f,-0.00f,
                5.61f,-14.47f,8.54f,2.26f,-0.00f,-0.00f,
                5.61f,-16.71f,8.80f,1.89f,-0.00f,-0.00f,
                5.61f,-19.12f,8.05f,1.60f,-0.00f,-0.00f,
                5.61f,-21.55f,7.11f,1.47f,-0.00f,-0.00f,
                5.61f,-23.25f,6.34f,1.48f,-0.00f,-0.00f,
                5.61f,-23.96f,5.64f,1.55f,-0.00f,-0.00f,
                5.61f,-24.07f,5.13f,1.58f,-0.00f,-0.00f,
                5.61f,-23.86f,3.93f,1.64f,-0.00f,-0.00f,
                5.61f,-22.72f,1.38f,1.82f,-0.00f,-0.00f,
                5.61f,-19.56f,-1.12f,2.13f,-0.00f,-0.00f,
                5.61f,-14.95f,-1.36f,2.51f,-0.00f,-0.00f,
                5.61f,-11.36f,0.63f,2.82f,-0.00f,-0.00f,
                5.61f,-9.66f,2.59f,3.00f,-0.00f,-0.00f,
                5.61f,-9.00f,3.80f,3.10f,-0.00f,-0.00f,
                5.61f,-8.75f,4.40f,-3.14f,-0.00f,-0.00f,
                5.61f,-8.65f,4.62f,-3.12f,-0.00f,-0.00f,
                5.61f,-8.63f,4.65f,-3.12f,-0.00f,-0.00f,
        };
        this.FootL.rotateAngleX   = FootL_rotpos[frame*6+3];
        this.FootL.rotateAngleY   = FootL_rotpos[frame*6+5];
        this.FootL.rotateAngleZ   = FootL_rotpos[frame*6+4];
        this.FootL.rotationPointX = FootL_rotpos[frame*6];
        this.FootL.rotationPointY = 24-FootL_rotpos[frame*6+2];
        this.FootL.rotationPointZ = FootL_rotpos[frame*6+1];
        final float[] FootR_rotpos = new float[]{
                -5.61f,-9.17f,5.12f,-0.05f,-3.14f,-0.00f,
                -5.61f,-10.53f,6.21f,-0.25f,-3.14f,-0.00f,
                -5.61f,-12.39f,7.51f,-0.53f,-3.14f,-0.00f,
                -5.61f,-14.47f,8.54f,-0.88f,-3.14f,-0.00f,
                -5.61f,-16.71f,8.80f,-1.25f,-3.14f,-0.00f,
                -5.61f,-19.12f,8.05f,-1.55f,-3.14f,-0.00f,
                -5.61f,-21.55f,7.11f,1.47f,-0.00f,3.14f,
                -5.61f,-23.25f,6.34f,1.48f,-0.00f,3.14f,
                -5.61f,-23.96f,5.64f,1.55f,-0.00f,3.14f,
                -5.61f,-24.07f,5.13f,-1.56f,-3.14f,-0.00f,
                -5.61f,-23.86f,3.93f,-1.50f,-3.14f,-0.00f,
                -5.61f,-22.72f,1.38f,-1.32f,-3.14f,-0.00f,
                -5.61f,-19.56f,-1.12f,-1.01f,-3.14f,-0.00f,
                -5.61f,-14.95f,-1.36f,-0.63f,-3.14f,-0.00f,
                -5.61f,-11.36f,0.63f,-0.32f,-3.14f,-0.00f,
                -5.61f,-9.66f,2.59f,-0.14f,-3.14f,-0.00f,
                -5.61f,-9.00f,3.80f,-0.04f,-3.14f,-0.00f,
                -5.61f,-8.75f,4.40f,0.00f,-3.14f,-0.00f,
                -5.61f,-8.65f,4.62f,0.02f,-3.14f,-0.00f,
                -5.61f,-8.63f,4.65f,0.03f,-3.14f,-0.00f,
        };
        this.FootR.rotateAngleX   = FootR_rotpos[frame*6+3];
        this.FootR.rotateAngleY   = FootR_rotpos[frame*6+5];
        this.FootR.rotateAngleZ   = FootR_rotpos[frame*6+4];
        this.FootR.rotationPointX = FootR_rotpos[frame*6];
        this.FootR.rotationPointY = 24-FootR_rotpos[frame*6+2];
        this.FootR.rotationPointZ = FootR_rotpos[frame*6+1];
        final float[] Head_rotpos = new float[]{
                -0.00f,-30.15f,14.15f,0.25f,-0.00f,-0.00f,
                -0.00f,-29.85f,15.09f,0.23f,-0.00f,-0.00f,
                -0.00f,-29.42f,16.31f,0.21f,-0.00f,-0.00f,
                -0.00f,-28.95f,17.53f,0.17f,-0.00f,-0.00f,
                -0.00f,-28.58f,18.57f,0.11f,-0.00f,-0.00f,
                -0.00f,-28.70f,19.00f,0.05f,-0.00f,-0.00f,
                -0.00f,-29.26f,18.78f,0.01f,-0.00f,-0.00f,
                -0.00f,-29.90f,18.21f,-0.01f,-0.00f,-0.00f,
                -0.00f,-30.33f,17.54f,-0.01f,-0.00f,-0.00f,
                -0.00f,-30.44f,16.99f,-0.01f,-0.00f,-0.00f,
                -0.00f,-30.67f,16.32f,0.01f,-0.00f,-0.00f,
                -0.00f,-31.12f,15.47f,0.07f,-0.00f,-0.00f,
                -0.00f,-31.19f,14.70f,0.15f,-0.00f,-0.00f,
                -0.00f,-30.71f,13.77f,0.23f,-0.00f,-0.00f,
                -0.00f,-30.08f,12.44f,0.29f,-0.00f,-0.00f,
                -0.00f,-29.78f,11.61f,0.32f,-0.00f,-0.00f,
                -0.00f,-29.79f,11.83f,0.31f,-0.00f,-0.00f,
                -0.00f,-29.97f,12.58f,0.29f,-0.00f,-0.00f,
                -0.00f,-30.18f,13.39f,0.26f,-0.00f,-0.00f,
                -0.00f,-30.27f,13.77f,0.25f,-0.00f,-0.00f,
        };
        this.Head.rotateAngleX   = Head_rotpos[frame*6+3];
        this.Head.rotateAngleY   = Head_rotpos[frame*6+5];
        this.Head.rotateAngleZ   = Head_rotpos[frame*6+4];
        this.Head.rotationPointX = Head_rotpos[frame*6];
        this.Head.rotationPointY = 24-Head_rotpos[frame*6+2];
        this.Head.rotationPointZ = Head_rotpos[frame*6+1];
        final float[] LowerLegBR_rotpos = new float[]{
                -7.70f,2.13f,8.05f,-0.92f,-0.08f,-0.26f,
                -7.70f,3.12f,8.48f,-0.84f,-0.06f,-0.26f,
                -7.70f,4.57f,8.95f,-0.72f,-0.03f,-0.27f,
                -7.70f,6.31f,9.29f,-0.57f,0.01f,-0.27f,
                -7.70f,8.25f,9.39f,-0.38f,0.06f,-0.26f,
                -7.70f,10.39f,9.25f,-0.21f,0.11f,-0.25f,
                -7.70f,12.62f,9.18f,-0.09f,0.14f,-0.23f,
                -7.70f,14.55f,9.29f,-0.02f,0.15f,-0.22f,
                -7.70f,15.79f,9.42f,0.01f,0.16f,-0.22f,
                -7.70f,16.09f,9.32f,0.02f,0.16f,-0.22f,
                -7.70f,15.48f,8.81f,0.03f,0.16f,-0.22f,
                -7.70f,14.17f,8.07f,0.05f,0.17f,-0.21f,
                -7.70f,12.32f,7.50f,0.05f,0.17f,-0.21f,
                -7.70f,10.32f,7.36f,-0.02f,0.15f,-0.22f,
                -7.70f,8.47f,7.48f,-0.18f,0.12f,-0.25f,
                -7.70f,6.74f,7.59f,-0.39f,0.06f,-0.26f,
                -7.70f,5.03f,7.67f,-0.59f,0.01f,-0.27f,
                -7.70f,3.44f,7.75f,-0.77f,-0.04f,-0.27f,
                -7.70f,2.23f,7.82f,-0.90f,-0.08f,-0.26f,
                -7.70f,1.75f,7.85f,-0.95f,-0.09f,-0.25f,
        };
        this.LowerLegBR.rotateAngleX   = LowerLegBR_rotpos[frame*6+3];
        this.LowerLegBR.rotateAngleY   = LowerLegBR_rotpos[frame*6+5];
        this.LowerLegBR.rotateAngleZ   = LowerLegBR_rotpos[frame*6+4];
        this.LowerLegBR.rotationPointX = LowerLegBR_rotpos[frame*6];
        this.LowerLegBR.rotationPointY = 24-LowerLegBR_rotpos[frame*6+2];
        this.LowerLegBR.rotationPointZ = LowerLegBR_rotpos[frame*6+1];
        final float[] Neck_rotpos = new float[]{
                -0.00f,-21.11f,13.49f,-0.18f,-0.00f,-0.00f,
                -0.00f,-20.95f,14.05f,-0.25f,-0.00f,-0.00f,
                -0.00f,-20.74f,14.73f,-0.34f,-0.00f,-0.00f,
                -0.00f,-20.52f,15.35f,-0.44f,-0.00f,-0.00f,
                -0.00f,-20.40f,15.77f,-0.52f,-0.00f,-0.00f,
                -0.00f,-20.58f,15.84f,-0.56f,-0.00f,-0.00f,
                -0.00f,-21.00f,15.59f,-0.53f,-0.00f,-0.00f,
                -0.00f,-21.40f,15.20f,-0.46f,-0.00f,-0.00f,
                -0.00f,-21.63f,14.76f,-0.41f,-0.00f,-0.00f,
                -0.00f,-21.65f,14.32f,-0.38f,-0.00f,-0.00f,
                -0.00f,-21.80f,13.89f,-0.35f,-0.00f,-0.00f,
                -0.00f,-22.13f,13.59f,-0.29f,-0.00f,-0.00f,
                -0.00f,-22.08f,13.51f,-0.22f,-0.00f,-0.00f,
                -0.00f,-21.54f,13.27f,-0.14f,-0.00f,-0.00f,
                -0.00f,-20.89f,12.52f,-0.08f,-0.00f,-0.00f,
                -0.00f,-20.60f,11.93f,-0.05f,-0.00f,-0.00f,
                -0.00f,-20.63f,12.04f,-0.07f,-0.00f,-0.00f,
                -0.00f,-20.83f,12.51f,-0.10f,-0.00f,-0.00f,
                -0.00f,-21.06f,13.02f,-0.14f,-0.00f,-0.00f,
                -0.00f,-21.17f,13.26f,-0.16f,-0.00f,-0.00f,
        };
        this.Neck.rotateAngleX   = Neck_rotpos[frame*6+3];
        this.Neck.rotateAngleY   = Neck_rotpos[frame*6+5];
        this.Neck.rotateAngleZ   = Neck_rotpos[frame*6+4];
        this.Neck.rotationPointX = Neck_rotpos[frame*6];
        this.Neck.rotationPointY = 24-Neck_rotpos[frame*6+2];
        this.Neck.rotationPointZ = Neck_rotpos[frame*6+1];
        final float[] Nose_rotpos = new float[]{
                -0.00f,-35.61f,11.24f,0.25f,-0.00f,-0.00f,
                -0.00f,-35.35f,12.25f,0.23f,-0.00f,-0.00f,
                -0.00f,-34.98f,13.60f,0.21f,-0.00f,-0.00f,
                -0.00f,-34.62f,15.06f,0.17f,-0.00f,-0.00f,
                -0.00f,-34.39f,16.45f,0.11f,-0.00f,-0.00f,
                -0.00f,-34.62f,17.23f,0.05f,-0.00f,-0.00f,
                -0.00f,-35.25f,17.21f,0.01f,-0.00f,-0.00f,
                -0.00f,-35.91f,16.75f,-0.01f,-0.00f,-0.00f,
                -0.00f,-36.35f,16.12f,-0.01f,-0.00f,-0.00f,
                -0.00f,-36.46f,15.57f,-0.01f,-0.00f,-0.00f,
                -0.00f,-36.65f,14.75f,0.01f,-0.00f,-0.00f,
                -0.00f,-36.99f,13.53f,0.07f,-0.00f,-0.00f,
                -0.00f,-36.90f,12.32f,0.15f,-0.00f,-0.00f,
                -0.00f,-36.22f,10.96f,0.23f,-0.00f,-0.00f,
                -0.00f,-35.40f,9.29f,0.29f,-0.00f,-0.00f,
                -0.00f,-35.01f,8.31f,0.32f,-0.00f,-0.00f,
                -0.00f,-35.04f,8.56f,0.31f,-0.00f,-0.00f,
                -0.00f,-35.30f,9.45f,0.29f,-0.00f,-0.00f,
                -0.00f,-35.59f,10.40f,0.26f,-0.00f,-0.00f,
                -0.00f,-35.72f,10.84f,0.25f,-0.00f,-0.00f,
        };
        this.Nose.rotateAngleX   = Nose_rotpos[frame*6+3];
        this.Nose.rotateAngleY   = Nose_rotpos[frame*6+5];
        this.Nose.rotateAngleZ   = Nose_rotpos[frame*6+4];
        this.Nose.rotationPointX = Nose_rotpos[frame*6];
        this.Nose.rotationPointY = 24-Nose_rotpos[frame*6+2];
        this.Nose.rotationPointZ = Nose_rotpos[frame*6+1];
        final float[] Tail1_rotpos = new float[]{
                0.00f,8.33f,12.30f,-1.16f,0.00f,-0.00f,
                0.00f,8.88f,12.80f,-1.10f,0.00f,-0.00f,
                0.00f,9.63f,13.46f,-1.02f,0.00f,-0.00f,
                0.00f,10.42f,14.16f,-0.91f,0.00f,-0.00f,
                0.00f,11.09f,14.74f,-0.79f,0.00f,-0.00f,
                0.00f,11.45f,15.20f,-0.66f,0.00f,-0.00f,
                0.00f,11.51f,15.58f,-0.54f,0.00f,-0.00f,
                0.00f,11.37f,15.81f,-0.44f,0.00f,-0.00f,
                0.00f,11.21f,15.83f,-0.37f,0.00f,-0.00f,
                0.00f,11.19f,15.58f,-0.34f,0.00f,-0.00f,
                0.00f,11.23f,14.78f,-0.41f,0.00f,-0.00f,
                0.00f,11.06f,13.49f,-0.59f,0.00f,-0.00f,
                0.00f,10.59f,12.20f,-0.82f,0.00f,-0.00f,
                0.00f,9.98f,11.30f,-1.02f,0.00f,-0.00f,
                0.00f,9.57f,10.95f,-1.12f,0.00f,-0.00f,
                0.00f,9.29f,11.02f,-1.15f,0.00f,-0.00f,
                0.00f,8.91f,11.30f,-1.17f,0.00f,-0.00f,
                0.00f,8.53f,11.66f,-1.18f,0.00f,-0.00f,
                0.00f,8.23f,11.98f,-1.18f,0.00f,-0.00f,
                0.00f,8.12f,12.11f,-1.18f,0.00f,-0.00f,
        };
        this.Tail1.rotateAngleX   = Tail1_rotpos[frame*6+3];
        this.Tail1.rotateAngleY   = Tail1_rotpos[frame*6+5];
        this.Tail1.rotateAngleZ   = Tail1_rotpos[frame*6+4];
        this.Tail1.rotationPointX = Tail1_rotpos[frame*6];
        this.Tail1.rotationPointY = 24-Tail1_rotpos[frame*6+2];
        this.Tail1.rotationPointZ = Tail1_rotpos[frame*6+1];
        final float[] Tail2_rotpos = new float[]{
                -0.00f,14.26f,5.80f,-0.41f,-0.00f,0.00f,
                -0.00f,15.10f,6.53f,-0.39f,-0.00f,0.00f,
                -0.00f,16.24f,7.51f,-0.36f,-0.00f,0.00f,
                -0.00f,17.44f,8.55f,-0.35f,-0.00f,0.00f,
                -0.00f,18.45f,9.44f,-0.38f,-0.00f,0.00f,
                -0.00f,19.01f,10.09f,-0.46f,-0.00f,0.00f,
                -0.00f,19.05f,10.57f,-0.57f,-0.00f,0.00f,
                -0.00f,18.74f,10.87f,-0.70f,-0.00f,0.00f,
                -0.00f,18.36f,10.94f,-0.79f,-0.00f,0.00f,
                -0.00f,18.23f,10.71f,-0.83f,-0.00f,0.00f,
                -0.00f,18.39f,9.75f,-0.77f,-0.00f,0.00f,
                -0.00f,18.39f,8.16f,-0.61f,-0.00f,0.00f,
                -0.00f,17.82f,6.70f,-0.41f,-0.00f,0.00f,
                -0.00f,16.78f,5.73f,-0.26f,-0.00f,0.00f,
                -0.00f,15.99f,5.22f,-0.23f,-0.00f,0.00f,
                -0.00f,15.51f,5.04f,-0.28f,-0.00f,0.00f,
                -0.00f,14.97f,5.08f,-0.33f,-0.00f,0.00f,
                -0.00f,14.46f,5.25f,-0.38f,-0.00f,0.00f,
                -0.00f,14.08f,5.43f,-0.41f,-0.00f,0.00f,
                -0.00f,13.93f,5.51f,-0.43f,-0.00f,0.00f,
        };
        this.Tail2.rotateAngleX   = Tail2_rotpos[frame*6+3];
        this.Tail2.rotateAngleY   = Tail2_rotpos[frame*6+5];
        this.Tail2.rotateAngleZ   = Tail2_rotpos[frame*6+4];
        this.Tail2.rotationPointX = Tail2_rotpos[frame*6];
        this.Tail2.rotationPointY = 24-Tail2_rotpos[frame*6+2];
        this.Tail2.rotationPointZ = Tail2_rotpos[frame*6+1];
        final float[] Tail3_rotpos = new float[]{
                -0.00f,24.68f,2.63f,-0.12f,-0.00f,-0.00f,
                -0.00f,25.61f,3.54f,-0.11f,-0.00f,-0.00f,
                -0.00f,26.82f,4.67f,-0.11f,-0.00f,-0.00f,
                -0.00f,28.05f,5.68f,-0.13f,-0.00f,-0.00f,
                -0.00f,28.97f,6.22f,-0.17f,-0.00f,-0.00f,
                -0.00f,29.26f,6.24f,-0.20f,-0.00f,-0.00f,
                -0.00f,28.86f,6.03f,-0.22f,-0.00f,-0.00f,
                -0.00f,28.01f,5.72f,-0.23f,-0.00f,-0.00f,
                -0.00f,27.16f,5.38f,-0.23f,-0.00f,-0.00f,
                -0.00f,26.84f,5.01f,-0.23f,-0.00f,-0.00f,
                -0.00f,27.32f,4.33f,-0.22f,-0.00f,-0.00f,
                -0.00f,28.08f,3.65f,-0.19f,-0.00f,-0.00f,
                -0.00f,28.26f,3.47f,-0.14f,-0.00f,-0.00f,
                -0.00f,27.62f,3.43f,-0.12f,-0.00f,-0.00f,
                -0.00f,26.90f,2.96f,-0.14f,-0.00f,-0.00f,
                -0.00f,26.31f,2.38f,-0.17f,-0.00f,-0.00f,
                -0.00f,25.63f,2.15f,-0.16f,-0.00f,-0.00f,
                -0.00f,24.99f,2.13f,-0.15f,-0.00f,-0.00f,
                -0.00f,24.50f,2.20f,-0.13f,-0.00f,-0.00f,
                -0.00f,24.31f,2.25f,-0.12f,-0.00f,-0.00f,
        };
        this.Tail3.rotateAngleX   = Tail3_rotpos[frame*6+3];
        this.Tail3.rotateAngleY   = Tail3_rotpos[frame*6+5];
        this.Tail3.rotateAngleZ   = Tail3_rotpos[frame*6+4];
        this.Tail3.rotationPointX = Tail3_rotpos[frame*6];
        this.Tail3.rotationPointY = 24-Tail3_rotpos[frame*6+2];
        this.Tail3.rotationPointZ = Tail3_rotpos[frame*6+1];
        final float[] Tail4_rotpos = new float[]{
                -0.00f,34.01f,1.66f,-0.03f,-0.00f,-0.00f,
                -0.00f,34.94f,2.62f,-0.03f,-0.00f,-0.00f,
                -0.00f,36.16f,3.74f,-0.03f,-0.00f,-0.00f,
                -0.00f,37.36f,4.62f,-0.04f,-0.00f,-0.00f,
                -0.00f,38.24f,4.80f,-0.08f,-0.00f,-0.00f,
                -0.00f,38.47f,4.46f,-0.12f,-0.00f,-0.00f,
                -0.00f,38.03f,4.07f,-0.14f,-0.00f,-0.00f,
                -0.00f,37.16f,3.68f,-0.15f,-0.00f,-0.00f,
                -0.00f,36.32f,3.34f,-0.15f,-0.00f,-0.00f,
                -0.00f,35.99f,2.98f,-0.15f,-0.00f,-0.00f,
                -0.00f,36.49f,2.39f,-0.14f,-0.00f,-0.00f,
                -0.00f,37.32f,2.03f,-0.10f,-0.00f,-0.00f,
                -0.00f,37.56f,2.31f,-0.05f,-0.00f,-0.00f,
                -0.00f,36.95f,2.44f,-0.04f,-0.00f,-0.00f,
                -0.00f,36.19f,1.74f,-0.06f,-0.00f,-0.00f,
                -0.00f,35.57f,0.94f,-0.09f,-0.00f,-0.00f,
                -0.00f,34.90f,0.73f,-0.08f,-0.00f,-0.00f,
                -0.00f,34.28f,0.87f,-0.07f,-0.00f,-0.00f,
                -0.00f,33.82f,1.12f,-0.05f,-0.00f,-0.00f,
                -0.00f,33.63f,1.25f,-0.04f,-0.00f,-0.00f,
        };
        this.Tail4.rotateAngleX   = Tail4_rotpos[frame*6+3];
        this.Tail4.rotateAngleY   = Tail4_rotpos[frame*6+5];
        this.Tail4.rotateAngleZ   = Tail4_rotpos[frame*6+4];
        this.Tail4.rotationPointX = Tail4_rotpos[frame*6];
        this.Tail4.rotationPointY = 24-Tail4_rotpos[frame*6+2];
        this.Tail4.rotationPointZ = Tail4_rotpos[frame*6+1];
        final float[] Tail5_rotpos = new float[]{
                -0.00f,42.76f,1.35f,-0.03f,-0.00f,-0.00f,
                -0.00f,43.69f,2.36f,-0.03f,-0.00f,-0.00f,
                -0.00f,44.91f,3.48f,-0.03f,-0.00f,-0.00f,
                -0.00f,46.11f,4.23f,-0.04f,-0.00f,-0.00f,
                -0.00f,46.97f,4.07f,-0.08f,-0.00f,-0.00f,
                -0.00f,47.15f,3.38f,-0.12f,-0.00f,-0.00f,
                -0.00f,46.69f,2.83f,-0.14f,-0.00f,-0.00f,
                -0.00f,45.82f,2.37f,-0.15f,-0.00f,-0.00f,
                -0.00f,44.97f,2.03f,-0.15f,-0.00f,-0.00f,
                -0.00f,44.65f,1.68f,-0.15f,-0.00f,-0.00f,
                -0.00f,45.16f,1.17f,-0.14f,-0.00f,-0.00f,
                -0.00f,46.02f,1.12f,-0.10f,-0.00f,-0.00f,
                -0.00f,46.30f,1.83f,-0.05f,-0.00f,-0.00f,
                -0.00f,45.69f,2.11f,-0.04f,-0.00f,-0.00f,
                -0.00f,44.93f,1.19f,-0.06f,-0.00f,-0.00f,
                -0.00f,44.30f,0.19f,-0.09f,-0.00f,-0.00f,
                -0.00f,43.63f,0.00f,-0.08f,-0.00f,-0.00f,
                -0.00f,43.02f,0.29f,-0.07f,-0.00f,-0.00f,
                -0.00f,42.56f,0.70f,-0.05f,-0.00f,-0.00f,
                -0.00f,42.38f,0.91f,-0.04f,-0.00f,-0.00f,
        };
        this.Tail5.rotateAngleX   = Tail5_rotpos[frame*6+3];
        this.Tail5.rotateAngleY   = Tail5_rotpos[frame*6+5];
        this.Tail5.rotateAngleZ   = Tail5_rotpos[frame*6+4];
        this.Tail5.rotationPointX = Tail5_rotpos[frame*6];
        this.Tail5.rotationPointY = 24-Tail5_rotpos[frame*6+2];
        this.Tail5.rotationPointZ = Tail5_rotpos[frame*6+1];
        final float[] Torso_rotpos = new float[]{
                0.00f,2.12f,18.27f,2.91f,-0.00f,0.00f,
                0.00f,2.36f,18.42f,2.96f,-0.00f,0.00f,
                0.00f,2.66f,18.52f,3.05f,-0.00f,0.00f,
                0.00f,2.95f,18.44f,-3.13f,-0.00f,0.00f,
                0.00f,3.15f,18.09f,-3.01f,-0.00f,0.00f,
                0.00f,3.15f,17.50f,-2.88f,-0.00f,0.00f,
                0.00f,2.98f,16.83f,-2.75f,-0.00f,0.00f,
                0.00f,2.77f,16.18f,-2.65f,-0.00f,0.00f,
                0.00f,2.60f,15.59f,-2.58f,-0.00f,0.00f,
                0.00f,2.58f,15.12f,-2.55f,-0.00f,0.00f,
                0.00f,2.62f,14.95f,-2.63f,-0.00f,0.00f,
                0.00f,2.62f,15.20f,-2.81f,-0.00f,0.00f,
                0.00f,2.74f,15.75f,-3.03f,-0.00f,0.00f,
                0.00f,3.00f,16.34f,3.05f,-0.00f,0.00f,
                0.00f,3.16f,16.70f,2.94f,-0.00f,0.00f,
                0.00f,3.05f,16.95f,2.92f,-0.00f,0.00f,
                0.00f,2.77f,17.34f,2.90f,-0.00f,0.00f,
                0.00f,2.43f,17.74f,2.89f,-0.00f,0.00f,
                0.00f,2.14f,18.06f,2.89f,-0.00f,0.00f,
                0.00f,2.02f,18.20f,2.89f,-0.00f,0.00f,
        };
        this.Torso.rotateAngleX   = Torso_rotpos[frame*6+3];
        this.Torso.rotateAngleY   = Torso_rotpos[frame*6+5];
        this.Torso.rotateAngleZ   = Torso_rotpos[frame*6+4];
        this.Torso.rotationPointX = Torso_rotpos[frame*6];
        this.Torso.rotationPointY = 24-Torso_rotpos[frame*6+2];
        this.Torso.rotationPointZ = Torso_rotpos[frame*6+1];
        final float[] UpperLegBL_rotpos = new float[]{
                8.39f,4.13f,15.47f,-0.94f,0.09f,0.26f,
                8.39f,4.63f,15.83f,-0.90f,0.08f,0.26f,
                8.39f,5.29f,16.19f,-0.82f,0.06f,0.26f,
                8.39f,6.00f,16.36f,-0.70f,0.02f,0.27f,
                8.39f,6.67f,16.21f,-0.53f,-0.02f,0.27f,
                8.39f,7.36f,15.85f,-0.31f,-0.08f,0.26f,
                8.39f,8.09f,15.61f,-0.07f,-0.14f,0.23f,
                8.39f,8.67f,15.51f,0.14f,-0.19f,0.20f,
                8.39f,8.98f,15.45f,0.29f,-0.21f,0.17f,
                8.39f,8.93f,15.27f,0.35f,-0.22f,0.15f,
                8.39f,8.72f,14.71f,0.29f,-0.21f,0.17f,
                8.39f,8.46f,13.81f,0.12f,-0.18f,0.20f,
                8.39f,8.05f,12.99f,-0.11f,-0.13f,0.24f,
                8.39f,7.51f,12.57f,-0.37f,-0.07f,0.26f,
                8.39f,6.87f,12.72f,-0.59f,-0.00f,0.27f,
                8.39f,6.13f,13.29f,-0.76f,0.04f,0.27f,
                8.39f,5.37f,13.99f,-0.86f,0.07f,0.26f,
                8.39f,4.67f,14.64f,-0.92f,0.08f,0.26f,
                8.39f,4.14f,15.12f,-0.94f,0.09f,0.26f,
                8.39f,3.93f,15.31f,-0.95f,0.09f,0.25f,
        };
        this.UpperLegBL.rotateAngleX   = UpperLegBL_rotpos[frame*6+3];
        this.UpperLegBL.rotateAngleY   = UpperLegBL_rotpos[frame*6+5];
        this.UpperLegBL.rotateAngleZ   = UpperLegBL_rotpos[frame*6+4];
        this.UpperLegBL.rotationPointX = UpperLegBL_rotpos[frame*6];
        this.UpperLegBL.rotationPointY = 24-UpperLegBL_rotpos[frame*6+2];
        this.UpperLegBL.rotationPointZ = UpperLegBL_rotpos[frame*6+1];
        final float[] FootBR_rotpos = new float[]{
                -7.81f,-0.57f,1.00f,0.21f,0.07f,-0.26f,
                -7.81f,0.80f,1.92f,0.19f,0.06f,-0.26f,
                -7.81f,2.82f,2.98f,0.18f,0.06f,-0.26f,
                -7.81f,5.31f,3.82f,0.23f,0.08f,-0.26f,
                -7.81f,8.18f,4.11f,0.38f,0.11f,-0.25f,
                -7.81f,11.88f,3.27f,0.70f,0.19f,-0.20f,
                -7.81f,17.01f,2.20f,1.20f,0.26f,-0.08f,
                -7.81f,22.45f,2.56f,1.75f,0.26f,0.06f,
                -7.81f,26.08f,3.88f,2.14f,0.22f,0.16f,
                -7.81f,27.15f,4.41f,2.29f,0.20f,0.19f,
                -7.81f,26.18f,3.70f,2.22f,0.21f,0.17f,
                -7.81f,23.78f,2.43f,2.03f,0.24f,0.13f,
                -7.81f,20.13f,1.20f,1.73f,0.27f,0.06f,
                -7.81f,15.97f,0.52f,1.40f,0.27f,-0.03f,
                -7.81f,12.15f,0.15f,1.10f,0.25f,-0.11f,
                -7.81f,8.58f,-0.19f,0.84f,0.21f,-0.17f,
                -7.81f,5.09f,-0.20f,0.61f,0.17f,-0.21f,
                -7.81f,2.00f,0.07f,0.41f,0.12f,-0.24f,
                -7.81f,-0.23f,0.43f,0.28f,0.09f,-0.26f,
                -7.81f,-1.07f,0.60f,0.23f,0.08f,-0.26f,
        };
        this.FootBR.rotateAngleX   = FootBR_rotpos[frame*6+3];
        this.FootBR.rotateAngleY   = FootBR_rotpos[frame*6+5];
        this.FootBR.rotateAngleZ   = FootBR_rotpos[frame*6+4];
        this.FootBR.rotationPointX = FootBR_rotpos[frame*6];
        this.FootBR.rotationPointY = 24-FootBR_rotpos[frame*6+2];
        this.FootBR.rotationPointZ = FootBR_rotpos[frame*6+1];
        final float[] LowerLegBL_rotpos = new float[]{
                7.70f,2.13f,8.05f,-0.92f,0.08f,0.26f,
                7.70f,3.12f,8.48f,-0.84f,0.06f,0.26f,
                7.70f,4.57f,8.95f,-0.72f,0.03f,0.27f,
                7.70f,6.31f,9.29f,-0.57f,-0.01f,0.27f,
                7.70f,8.25f,9.39f,-0.38f,-0.06f,0.26f,
                7.70f,10.39f,9.25f,-0.21f,-0.11f,0.25f,
                7.70f,12.62f,9.18f,-0.09f,-0.14f,0.23f,
                7.70f,14.55f,9.29f,-0.02f,-0.15f,0.22f,
                7.70f,15.79f,9.42f,0.01f,-0.16f,0.22f,
                7.70f,16.09f,9.32f,0.02f,-0.16f,0.22f,
                7.70f,15.48f,8.81f,0.03f,-0.16f,0.22f,
                7.70f,14.17f,8.07f,0.05f,-0.17f,0.21f,
                7.70f,12.32f,7.50f,0.05f,-0.17f,0.21f,
                7.70f,10.32f,7.36f,-0.02f,-0.15f,0.22f,
                7.70f,8.47f,7.48f,-0.18f,-0.12f,0.25f,
                7.70f,6.74f,7.59f,-0.39f,-0.06f,0.26f,
                7.70f,5.03f,7.67f,-0.59f,-0.01f,0.27f,
                7.70f,3.44f,7.75f,-0.77f,0.04f,0.27f,
                7.70f,2.23f,7.82f,-0.90f,0.08f,0.26f,
                7.70f,1.75f,7.85f,-0.95f,0.09f,0.25f,
        };
        this.LowerLegBL.rotateAngleX   = LowerLegBL_rotpos[frame*6+3];
        this.LowerLegBL.rotateAngleY   = LowerLegBL_rotpos[frame*6+5];
        this.LowerLegBL.rotateAngleZ   = LowerLegBL_rotpos[frame*6+4];
        this.LowerLegBL.rotationPointX = LowerLegBL_rotpos[frame*6];
        this.LowerLegBL.rotationPointY = 24-LowerLegBL_rotpos[frame*6+2];
        this.LowerLegBL.rotationPointZ = LowerLegBL_rotpos[frame*6+1];
        final float[] UpperLegBR_rotpos = new float[]{
                -8.39f,4.13f,15.47f,-0.94f,-0.09f,-0.26f,
                -8.39f,4.63f,15.83f,-0.90f,-0.08f,-0.26f,
                -8.39f,5.29f,16.19f,-0.82f,-0.06f,-0.26f,
                -8.39f,6.00f,16.36f,-0.70f,-0.02f,-0.27f,
                -8.39f,6.67f,16.21f,-0.53f,0.02f,-0.27f,
                -8.39f,7.36f,15.85f,-0.31f,0.08f,-0.26f,
                -8.39f,8.09f,15.61f,-0.07f,0.14f,-0.23f,
                -8.39f,8.67f,15.51f,0.14f,0.19f,-0.20f,
                -8.39f,8.98f,15.45f,0.29f,0.21f,-0.17f,
                -8.39f,8.93f,15.27f,0.35f,0.22f,-0.15f,
                -8.39f,8.72f,14.71f,0.29f,0.21f,-0.17f,
                -8.39f,8.46f,13.81f,0.12f,0.18f,-0.20f,
                -8.39f,8.05f,12.99f,-0.11f,0.13f,-0.24f,
                -8.39f,7.51f,12.57f,-0.37f,0.07f,-0.26f,
                -8.39f,6.87f,12.72f,-0.59f,0.00f,-0.27f,
                -8.39f,6.13f,13.29f,-0.76f,-0.04f,-0.27f,
                -8.39f,5.37f,13.99f,-0.86f,-0.07f,-0.26f,
                -8.39f,4.67f,14.64f,-0.92f,-0.08f,-0.26f,
                -8.39f,4.14f,15.12f,-0.94f,-0.09f,-0.26f,
                -8.39f,3.93f,15.31f,-0.95f,-0.09f,-0.25f,
        };
        this.UpperLegBR.rotateAngleX   = UpperLegBR_rotpos[frame*6+3];
        this.UpperLegBR.rotateAngleY   = UpperLegBR_rotpos[frame*6+5];
        this.UpperLegBR.rotateAngleZ   = UpperLegBR_rotpos[frame*6+4];
        this.UpperLegBR.rotationPointX = UpperLegBR_rotpos[frame*6];
        this.UpperLegBR.rotationPointY = 24-UpperLegBR_rotpos[frame*6+2];
        this.UpperLegBR.rotationPointZ = UpperLegBR_rotpos[frame*6+1];
    }
    protected void poseIdle(int frame) {
        final float[] ArmL_rotpos = new float[]{
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
                5.61f,-11.32f,7.09f,0.20f,-0.00f,-0.00f,
        };
        this.ArmL.rotateAngleX   = ArmL_rotpos[frame*6+3];
        this.ArmL.rotateAngleY   = ArmL_rotpos[frame*6+5];
        this.ArmL.rotateAngleZ   = ArmL_rotpos[frame*6+4];
        this.ArmL.rotationPointX = ArmL_rotpos[frame*6];
        this.ArmL.rotationPointY = 24-ArmL_rotpos[frame*6+2];
        this.ArmL.rotationPointZ = ArmL_rotpos[frame*6+1];
        final float[] ArmR_rotpos = new float[]{
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
                -5.61f,-11.32f,7.09f,0.20f,0.00f,3.14f,
        };
        this.ArmR.rotateAngleX   = ArmR_rotpos[frame*6+3];
        this.ArmR.rotateAngleY   = ArmR_rotpos[frame*6+5];
        this.ArmR.rotateAngleZ   = ArmR_rotpos[frame*6+4];
        this.ArmR.rotationPointX = ArmR_rotpos[frame*6];
        this.ArmR.rotationPointY = 24-ArmR_rotpos[frame*6+2];
        this.ArmR.rotationPointZ = ArmR_rotpos[frame*6+1];
        final float[] Chest_rotpos = new float[]{
                -0.00f,-9.84f,11.99f,-3.13f,-0.00f,-0.00f,
                -0.00f,-9.84f,11.97f,-3.13f,-0.00f,-0.00f,
                -0.00f,-9.83f,11.94f,-3.13f,-0.00f,-0.00f,
                -0.00f,-9.83f,11.92f,-3.13f,-0.00f,-0.00f,
                -0.00f,-9.83f,11.89f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.82f,11.87f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.82f,11.84f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.81f,11.82f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.81f,11.79f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.81f,11.76f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.81f,11.79f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.81f,11.82f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.82f,11.84f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.82f,11.87f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.83f,11.89f,-3.12f,-0.00f,-0.00f,
                -0.00f,-9.83f,11.92f,-3.13f,-0.00f,-0.00f,
                -0.00f,-9.83f,11.94f,-3.13f,-0.00f,-0.00f,
                -0.00f,-9.84f,11.97f,-3.13f,-0.00f,-0.00f,
                -0.00f,-9.84f,11.99f,-3.13f,-0.00f,-0.00f,
                -0.00f,-9.85f,12.02f,-3.13f,-0.00f,-0.00f,
        };
        this.Chest.rotateAngleX   = Chest_rotpos[frame*6+3];
        this.Chest.rotateAngleY   = Chest_rotpos[frame*6+5];
        this.Chest.rotateAngleZ   = Chest_rotpos[frame*6+4];
        this.Chest.rotationPointX = Chest_rotpos[frame*6];
        this.Chest.rotationPointY = 24-Chest_rotpos[frame*6+2];
        this.Chest.rotationPointZ = Chest_rotpos[frame*6+1];
        final float[] EarL_rotpos = new float[]{
                4.78f,-26.50f,20.61f,-0.18f,0.68f,-0.12f,
                4.78f,-26.51f,20.57f,-0.18f,0.68f,-0.12f,
                4.78f,-26.51f,20.52f,-0.18f,0.68f,-0.12f,
                4.78f,-26.52f,20.47f,-0.18f,0.68f,-0.12f,
                4.78f,-26.52f,20.43f,-0.18f,0.68f,-0.12f,
                4.78f,-26.52f,20.38f,-0.18f,0.68f,-0.12f,
                4.78f,-26.53f,20.33f,-0.18f,0.68f,-0.11f,
                4.78f,-26.53f,20.29f,-0.18f,0.68f,-0.11f,
                4.78f,-26.54f,20.24f,-0.18f,0.68f,-0.11f,
                4.78f,-26.54f,20.19f,-0.18f,0.68f,-0.11f,
                4.78f,-26.54f,20.24f,-0.18f,0.68f,-0.11f,
                4.78f,-26.53f,20.29f,-0.18f,0.68f,-0.12f,
                4.78f,-26.52f,20.33f,-0.18f,0.68f,-0.12f,
                4.78f,-26.51f,20.38f,-0.19f,0.68f,-0.12f,
                4.78f,-26.51f,20.43f,-0.19f,0.68f,-0.12f,
                4.78f,-26.50f,20.48f,-0.19f,0.68f,-0.12f,
                4.78f,-26.49f,20.53f,-0.19f,0.68f,-0.12f,
                4.78f,-26.49f,20.57f,-0.19f,0.68f,-0.12f,
                4.78f,-26.49f,20.62f,-0.19f,0.68f,-0.12f,
                4.78f,-26.49f,20.66f,-0.19f,0.68f,-0.12f,
        };
        this.EarL.rotateAngleX   = EarL_rotpos[frame*6+3];
        this.EarL.rotateAngleY   = EarL_rotpos[frame*6+5];
        this.EarL.rotateAngleZ   = EarL_rotpos[frame*6+4];
        this.EarL.rotationPointX = EarL_rotpos[frame*6];
        this.EarL.rotationPointY = 24-EarL_rotpos[frame*6+2];
        this.EarL.rotationPointZ = EarL_rotpos[frame*6+1];
        final float[] EarR_rotpos = new float[]{
                -4.78f,-26.50f,20.61f,-0.18f,-0.68f,0.12f,
                -4.78f,-26.51f,20.57f,-0.18f,-0.68f,0.12f,
                -4.78f,-26.51f,20.52f,-0.18f,-0.68f,0.12f,
                -4.78f,-26.52f,20.47f,-0.18f,-0.68f,0.12f,
                -4.78f,-26.52f,20.43f,-0.18f,-0.68f,0.12f,
                -4.78f,-26.52f,20.38f,-0.18f,-0.68f,0.12f,
                -4.78f,-26.53f,20.33f,-0.18f,-0.68f,0.11f,
                -4.78f,-26.53f,20.29f,-0.18f,-0.68f,0.11f,
                -4.78f,-26.54f,20.24f,-0.18f,-0.68f,0.11f,
                -4.78f,-26.54f,20.19f,-0.18f,-0.68f,0.11f,
                -4.78f,-26.54f,20.24f,-0.18f,-0.68f,0.11f,
                -4.78f,-26.53f,20.29f,-0.18f,-0.68f,0.12f,
                -4.78f,-26.52f,20.33f,-0.18f,-0.68f,0.12f,
                -4.78f,-26.51f,20.38f,-0.19f,-0.68f,0.12f,
                -4.78f,-26.51f,20.43f,-0.19f,-0.68f,0.12f,
                -4.78f,-26.50f,20.48f,-0.19f,-0.68f,0.12f,
                -4.78f,-26.49f,20.53f,-0.19f,-0.68f,0.12f,
                -4.78f,-26.49f,20.57f,-0.19f,-0.68f,0.12f,
                -4.78f,-26.49f,20.62f,-0.19f,-0.68f,0.12f,
                -4.78f,-26.49f,20.66f,-0.19f,-0.68f,0.12f,
        };
        this.EarR.rotateAngleX   = EarR_rotpos[frame*6+3];
        this.EarR.rotateAngleY   = EarR_rotpos[frame*6+5];
        this.EarR.rotateAngleZ   = EarR_rotpos[frame*6+4];
        this.EarR.rotationPointX = EarR_rotpos[frame*6];
        this.EarR.rotationPointY = 24-EarR_rotpos[frame*6+2];
        this.EarR.rotationPointZ = EarR_rotpos[frame*6+1];
        final float[] FootBL_rotpos = new float[]{
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
                7.81f,6.89f,0.83f,-0.00f,-0.01f,0.27f,
        };
        this.FootBL.rotateAngleX   = FootBL_rotpos[frame*6+3];
        this.FootBL.rotateAngleY   = FootBL_rotpos[frame*6+5];
        this.FootBL.rotateAngleZ   = FootBL_rotpos[frame*6+4];
        this.FootBL.rotationPointX = FootBL_rotpos[frame*6];
        this.FootBL.rotationPointY = 24-FootBL_rotpos[frame*6+2];
        this.FootBL.rotationPointZ = FootBL_rotpos[frame*6+1];
        final float[] FootL_rotpos = new float[]{
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
                5.61f,-16.17f,2.38f,1.66f,-0.00f,-0.00f,
        };
        this.FootL.rotateAngleX   = FootL_rotpos[frame*6+3];
        this.FootL.rotateAngleY   = FootL_rotpos[frame*6+5];
        this.FootL.rotateAngleZ   = FootL_rotpos[frame*6+4];
        this.FootL.rotationPointX = FootL_rotpos[frame*6];
        this.FootL.rotationPointY = 24-FootL_rotpos[frame*6+2];
        this.FootL.rotationPointZ = FootL_rotpos[frame*6+1];
        final float[] FootR_rotpos = new float[]{
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
                -5.61f,-16.17f,2.38f,-1.48f,3.14f,0.00f,
        };
        this.FootR.rotateAngleX   = FootR_rotpos[frame*6+3];
        this.FootR.rotateAngleY   = FootR_rotpos[frame*6+5];
        this.FootR.rotateAngleZ   = FootR_rotpos[frame*6+4];
        this.FootR.rotationPointX = FootR_rotpos[frame*6];
        this.FootR.rotationPointY = 24-FootR_rotpos[frame*6+2];
        this.FootR.rotationPointZ = FootR_rotpos[frame*6+1];
        final float[] Head_rotpos = new float[]{
                0.00f,-29.22f,15.96f,0.09f,-0.00f,-0.00f,
                0.00f,-29.23f,15.91f,0.09f,-0.00f,-0.00f,
                0.00f,-29.23f,15.86f,0.09f,-0.00f,-0.00f,
                0.00f,-29.23f,15.81f,0.09f,-0.00f,-0.00f,
                0.00f,-29.23f,15.77f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.72f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.67f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.62f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.57f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.52f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.58f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.63f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.68f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.73f,0.09f,-0.00f,-0.00f,
                0.00f,-29.24f,15.78f,0.09f,-0.00f,-0.00f,
                0.00f,-29.23f,15.83f,0.09f,-0.00f,-0.00f,
                0.00f,-29.23f,15.88f,0.08f,-0.00f,-0.00f,
                0.00f,-29.23f,15.93f,0.08f,-0.00f,-0.00f,
                0.00f,-29.23f,15.97f,0.09f,-0.00f,-0.00f,
                0.00f,-29.22f,16.01f,0.09f,-0.00f,-0.00f,
        };
        this.Head.rotateAngleX   = Head_rotpos[frame*6+3];
        this.Head.rotateAngleY   = Head_rotpos[frame*6+5];
        this.Head.rotateAngleZ   = Head_rotpos[frame*6+4];
        this.Head.rotationPointX = Head_rotpos[frame*6];
        this.Head.rotationPointY = 24-Head_rotpos[frame*6+2];
        this.Head.rotationPointZ = Head_rotpos[frame*6+1];
        final float[] LowerLegBR_rotpos = new float[]{
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
                -7.70f,8.13f,4.66f,-0.56f,0.01f,-0.27f,
        };
        this.LowerLegBR.rotateAngleX   = LowerLegBR_rotpos[frame*6+3];
        this.LowerLegBR.rotateAngleY   = LowerLegBR_rotpos[frame*6+5];
        this.LowerLegBR.rotateAngleZ   = LowerLegBR_rotpos[frame*6+4];
        this.LowerLegBR.rotationPointX = LowerLegBR_rotpos[frame*6];
        this.LowerLegBR.rotationPointY = 24-LowerLegBR_rotpos[frame*6+2];
        this.LowerLegBR.rotationPointZ = LowerLegBR_rotpos[frame*6+1];
        final float[] Neck_rotpos = new float[]{
                0.00f,-20.44f,13.83f,-0.36f,0.00f,-0.00f,
                0.00f,-20.43f,13.79f,-0.36f,0.00f,-0.00f,
                0.00f,-20.43f,13.75f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.71f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.67f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.63f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.59f,-0.35f,0.00f,-0.00f,
                0.00f,-20.42f,13.55f,-0.35f,0.00f,-0.00f,
                0.00f,-20.42f,13.51f,-0.35f,0.00f,-0.00f,
                0.00f,-20.42f,13.47f,-0.34f,0.00f,-0.00f,
                0.00f,-20.42f,13.51f,-0.35f,0.00f,-0.00f,
                0.00f,-20.42f,13.55f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.59f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.63f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.67f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.71f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.75f,-0.35f,0.00f,-0.00f,
                0.00f,-20.43f,13.79f,-0.36f,0.00f,-0.00f,
                0.00f,-20.44f,13.83f,-0.36f,0.00f,-0.00f,
                0.00f,-20.44f,13.87f,-0.36f,0.00f,-0.00f,
        };
        this.Neck.rotateAngleX   = Neck_rotpos[frame*6+3];
        this.Neck.rotateAngleY   = Neck_rotpos[frame*6+5];
        this.Neck.rotateAngleZ   = Neck_rotpos[frame*6+4];
        this.Neck.rotationPointX = Neck_rotpos[frame*6];
        this.Neck.rotationPointY = 24-Neck_rotpos[frame*6+2];
        this.Neck.rotationPointZ = Neck_rotpos[frame*6+1];
        final float[] Nose_rotpos = new float[]{
                0.00f,-35.07f,13.94f,0.09f,-0.00f,-0.00f,
                0.00f,-35.07f,13.88f,0.09f,-0.00f,-0.00f,
                0.00f,-35.07f,13.83f,0.09f,-0.00f,-0.00f,
                0.00f,-35.07f,13.78f,0.09f,-0.00f,-0.00f,
                0.00f,-35.07f,13.73f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.69f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.64f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.59f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.53f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.47f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.53f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.59f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.65f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.71f,0.09f,-0.00f,-0.00f,
                0.00f,-35.09f,13.77f,0.09f,-0.00f,-0.00f,
                0.00f,-35.09f,13.83f,0.09f,-0.00f,-0.00f,
                0.00f,-35.08f,13.88f,0.08f,-0.00f,-0.00f,
                0.00f,-35.08f,13.93f,0.08f,-0.00f,-0.00f,
                0.00f,-35.08f,13.97f,0.09f,-0.00f,-0.00f,
                0.00f,-35.07f,13.99f,0.09f,-0.00f,-0.00f,
        };
        this.Nose.rotateAngleX   = Nose_rotpos[frame*6+3];
        this.Nose.rotateAngleY   = Nose_rotpos[frame*6+5];
        this.Nose.rotateAngleZ   = Nose_rotpos[frame*6+4];
        this.Nose.rotationPointX = Nose_rotpos[frame*6];
        this.Nose.rotationPointY = 24-Nose_rotpos[frame*6+2];
        this.Nose.rotationPointZ = Nose_rotpos[frame*6+1];
        final float[] Tail1_rotpos = new float[]{
                0.00f,12.61f,10.77f,-0.71f,0.00f,-0.00f,
                0.00f,12.61f,10.78f,-0.71f,0.00f,-0.00f,
                0.00f,12.62f,10.78f,-0.71f,0.00f,-0.00f,
                0.00f,12.62f,10.79f,-0.70f,0.00f,-0.00f,
                0.00f,12.63f,10.79f,-0.70f,0.00f,-0.00f,
                0.00f,12.63f,10.80f,-0.70f,0.00f,-0.00f,
                0.00f,12.64f,10.80f,-0.70f,0.00f,-0.00f,
                0.00f,12.64f,10.81f,-0.70f,0.00f,-0.00f,
                0.00f,12.65f,10.82f,-0.70f,0.00f,-0.00f,
                0.00f,12.66f,10.82f,-0.70f,0.00f,-0.00f,
                0.00f,12.65f,10.82f,-0.70f,0.00f,-0.00f,
                0.00f,12.64f,10.81f,-0.70f,0.00f,-0.00f,
                0.00f,12.64f,10.80f,-0.70f,0.00f,-0.00f,
                0.00f,12.63f,10.80f,-0.70f,0.00f,-0.00f,
                0.00f,12.63f,10.79f,-0.70f,0.00f,-0.00f,
                0.00f,12.62f,10.79f,-0.70f,0.00f,-0.00f,
                0.00f,12.62f,10.78f,-0.71f,0.00f,-0.00f,
                0.00f,12.61f,10.78f,-0.71f,0.00f,-0.00f,
                0.00f,12.61f,10.77f,-0.71f,0.00f,-0.00f,
                0.00f,12.60f,10.76f,-0.71f,0.00f,-0.00f,
        };
        this.Tail1.rotateAngleX   = Tail1_rotpos[frame*6+3];
        this.Tail1.rotateAngleY   = Tail1_rotpos[frame*6+5];
        this.Tail1.rotateAngleZ   = Tail1_rotpos[frame*6+4];
        this.Tail1.rotationPointX = Tail1_rotpos[frame*6];
        this.Tail1.rotationPointY = 24-Tail1_rotpos[frame*6+2];
        this.Tail1.rotationPointZ = Tail1_rotpos[frame*6+1];
        final float[] Tail2_rotpos = new float[]{
                0.00f,20.31f,5.94f,-0.34f,-0.00f,-0.00f,
                0.00f,20.32f,5.95f,-0.34f,-0.00f,-0.00f,
                0.00f,20.33f,5.95f,-0.34f,-0.00f,-0.00f,
                0.00f,20.33f,5.96f,-0.34f,-0.00f,-0.00f,
                0.00f,20.34f,5.97f,-0.34f,-0.00f,-0.00f,
                0.00f,20.35f,5.98f,-0.34f,-0.00f,-0.00f,
                0.00f,20.36f,5.98f,-0.34f,-0.00f,-0.00f,
                0.00f,20.36f,5.99f,-0.34f,-0.00f,-0.00f,
                0.00f,20.37f,6.00f,-0.35f,-0.00f,-0.00f,
                0.00f,20.38f,6.01f,-0.35f,-0.00f,-0.00f,
                0.00f,20.37f,6.00f,-0.35f,-0.00f,-0.00f,
                0.00f,20.36f,5.99f,-0.34f,-0.00f,-0.00f,
                0.00f,20.36f,5.98f,-0.34f,-0.00f,-0.00f,
                0.00f,20.35f,5.98f,-0.34f,-0.00f,-0.00f,
                0.00f,20.34f,5.97f,-0.34f,-0.00f,-0.00f,
                0.00f,20.33f,5.96f,-0.34f,-0.00f,-0.00f,
                0.00f,20.33f,5.95f,-0.34f,-0.00f,-0.00f,
                0.00f,20.32f,5.95f,-0.34f,-0.00f,-0.00f,
                0.00f,20.31f,5.94f,-0.34f,-0.00f,-0.00f,
                0.00f,20.30f,5.93f,-0.34f,-0.00f,-0.00f,
        };
        this.Tail2.rotateAngleX   = Tail2_rotpos[frame*6+3];
        this.Tail2.rotateAngleY   = Tail2_rotpos[frame*6+5];
        this.Tail2.rotateAngleZ   = Tail2_rotpos[frame*6+4];
        this.Tail2.rotationPointX = Tail2_rotpos[frame*6];
        this.Tail2.rotationPointY = 24-Tail2_rotpos[frame*6+2];
        this.Tail2.rotationPointZ = Tail2_rotpos[frame*6+1];
        final float[] Tail3_rotpos = new float[]{
                0.00f,30.96f,3.17f,-0.12f,-0.00f,-0.00f,
                0.00f,30.96f,3.16f,-0.12f,-0.00f,-0.00f,
                0.00f,30.97f,3.16f,-0.12f,-0.00f,-0.00f,
                0.00f,30.97f,3.16f,-0.13f,-0.00f,-0.00f,
                0.00f,30.98f,3.15f,-0.13f,-0.00f,-0.00f,
                0.00f,30.98f,3.15f,-0.13f,-0.00f,-0.00f,
                0.00f,30.99f,3.15f,-0.13f,-0.00f,-0.00f,
                0.00f,30.99f,3.15f,-0.13f,-0.00f,-0.00f,
                0.00f,30.99f,3.14f,-0.13f,-0.00f,-0.00f,
                0.00f,31.00f,3.14f,-0.13f,-0.00f,-0.00f,
                0.00f,30.99f,3.14f,-0.13f,-0.00f,-0.00f,
                0.00f,30.99f,3.15f,-0.13f,-0.00f,-0.00f,
                0.00f,30.99f,3.15f,-0.13f,-0.00f,-0.00f,
                0.00f,30.98f,3.15f,-0.13f,-0.00f,-0.00f,
                0.00f,30.98f,3.15f,-0.13f,-0.00f,-0.00f,
                0.00f,30.97f,3.16f,-0.13f,-0.00f,-0.00f,
                0.00f,30.97f,3.16f,-0.12f,-0.00f,-0.00f,
                0.00f,30.96f,3.16f,-0.12f,-0.00f,-0.00f,
                0.00f,30.96f,3.17f,-0.12f,-0.00f,-0.00f,
                0.00f,30.95f,3.17f,-0.12f,-0.00f,-0.00f,
        };
        this.Tail3.rotateAngleX   = Tail3_rotpos[frame*6+3];
        this.Tail3.rotateAngleY   = Tail3_rotpos[frame*6+5];
        this.Tail3.rotateAngleZ   = Tail3_rotpos[frame*6+4];
        this.Tail3.rotationPointX = Tail3_rotpos[frame*6];
        this.Tail3.rotationPointY = 24-Tail3_rotpos[frame*6+2];
        this.Tail3.rotationPointZ = Tail3_rotpos[frame*6+1];
        final float[] Tail4_rotpos = new float[]{
                0.00f,40.28f,2.14f,-0.04f,-0.00f,-0.00f,
                0.00f,40.28f,2.13f,-0.04f,-0.00f,-0.00f,
                0.00f,40.29f,2.11f,-0.04f,-0.00f,-0.00f,
                0.00f,40.29f,2.10f,-0.04f,-0.00f,-0.00f,
                0.00f,40.29f,2.09f,-0.04f,-0.00f,-0.00f,
                0.00f,40.30f,2.08f,-0.05f,-0.00f,-0.00f,
                0.00f,40.30f,2.07f,-0.05f,-0.00f,-0.00f,
                0.00f,40.30f,2.05f,-0.05f,-0.00f,-0.00f,
                0.00f,40.31f,2.04f,-0.05f,-0.00f,-0.00f,
                0.00f,40.31f,2.03f,-0.05f,-0.00f,-0.00f,
                0.00f,40.31f,2.04f,-0.05f,-0.00f,-0.00f,
                0.00f,40.30f,2.05f,-0.05f,-0.00f,-0.00f,
                0.00f,40.30f,2.07f,-0.05f,-0.00f,-0.00f,
                0.00f,40.30f,2.08f,-0.05f,-0.00f,-0.00f,
                0.00f,40.29f,2.09f,-0.04f,-0.00f,-0.00f,
                0.00f,40.29f,2.10f,-0.04f,-0.00f,-0.00f,
                0.00f,40.29f,2.11f,-0.04f,-0.00f,-0.00f,
                0.00f,40.28f,2.13f,-0.04f,-0.00f,-0.00f,
                0.00f,40.28f,2.14f,-0.04f,-0.00f,-0.00f,
                0.00f,40.27f,2.15f,-0.04f,-0.00f,-0.00f,
        };
        this.Tail4.rotateAngleX   = Tail4_rotpos[frame*6+3];
        this.Tail4.rotateAngleY   = Tail4_rotpos[frame*6+5];
        this.Tail4.rotateAngleZ   = Tail4_rotpos[frame*6+4];
        this.Tail4.rotationPointX = Tail4_rotpos[frame*6];
        this.Tail4.rotationPointY = 24-Tail4_rotpos[frame*6+2];
        this.Tail4.rotationPointZ = Tail4_rotpos[frame*6+1];
        final float[] Tail5_rotpos = new float[]{
                0.00f,49.03f,1.78f,-0.04f,-0.00f,-0.00f,
                0.00f,49.03f,1.76f,-0.04f,-0.00f,-0.00f,
                0.00f,49.03f,1.74f,-0.04f,-0.00f,-0.00f,
                0.00f,49.04f,1.72f,-0.04f,-0.00f,-0.00f,
                0.00f,49.04f,1.69f,-0.04f,-0.00f,-0.00f,
                0.00f,49.04f,1.67f,-0.05f,-0.00f,-0.00f,
                0.00f,49.05f,1.65f,-0.05f,-0.00f,-0.00f,
                0.00f,49.05f,1.63f,-0.05f,-0.00f,-0.00f,
                0.00f,49.05f,1.61f,-0.05f,-0.00f,-0.00f,
                0.00f,49.05f,1.59f,-0.05f,-0.00f,-0.00f,
                0.00f,49.05f,1.61f,-0.05f,-0.00f,-0.00f,
                0.00f,49.05f,1.63f,-0.05f,-0.00f,-0.00f,
                0.00f,49.05f,1.65f,-0.05f,-0.00f,-0.00f,
                0.00f,49.04f,1.67f,-0.05f,-0.00f,-0.00f,
                0.00f,49.04f,1.69f,-0.04f,-0.00f,-0.00f,
                0.00f,49.04f,1.72f,-0.04f,-0.00f,-0.00f,
                0.00f,49.03f,1.74f,-0.04f,-0.00f,-0.00f,
                0.00f,49.03f,1.76f,-0.04f,-0.00f,-0.00f,
                0.00f,49.03f,1.78f,-0.04f,-0.00f,-0.00f,
                0.00f,49.02f,1.80f,-0.04f,-0.00f,-0.00f,
        };
        this.Tail5.rotateAngleX   = Tail5_rotpos[frame*6+3];
        this.Tail5.rotateAngleY   = Tail5_rotpos[frame*6+5];
        this.Tail5.rotateAngleZ   = Tail5_rotpos[frame*6+4];
        this.Tail5.rotationPointX = Tail5_rotpos[frame*6];
        this.Tail5.rotationPointY = 24-Tail5_rotpos[frame*6+2];
        this.Tail5.rotationPointZ = Tail5_rotpos[frame*6+1];
        final float[] Torso_rotpos = new float[]{
                0.00f,4.42f,13.44f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.43f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.42f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.42f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.41f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.41f,-2.92f,-0.00f,0.00f,
                0.00f,4.43f,13.40f,-2.91f,-0.00f,0.00f,
                0.00f,4.43f,13.40f,-2.91f,-0.00f,0.00f,
                0.00f,4.43f,13.39f,-2.91f,-0.00f,0.00f,
                0.00f,4.43f,13.38f,-2.91f,-0.00f,0.00f,
                0.00f,4.43f,13.39f,-2.91f,-0.00f,0.00f,
                0.00f,4.43f,13.40f,-2.91f,-0.00f,0.00f,
                0.00f,4.43f,13.40f,-2.91f,-0.00f,0.00f,
                0.00f,4.42f,13.41f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.41f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.42f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.42f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.43f,-2.92f,-0.00f,0.00f,
                0.00f,4.42f,13.44f,-2.92f,-0.00f,0.00f,
                0.00f,4.41f,13.44f,-2.92f,-0.00f,0.00f,
        };
        this.Torso.rotateAngleX   = Torso_rotpos[frame*6+3];
        this.Torso.rotateAngleY   = Torso_rotpos[frame*6+5];
        this.Torso.rotateAngleZ   = Torso_rotpos[frame*6+4];
        this.Torso.rotationPointX = Torso_rotpos[frame*6];
        this.Torso.rotationPointY = 24-Torso_rotpos[frame*6+2];
        this.Torso.rotationPointZ = Torso_rotpos[frame*6+1];
        final float[] UpperLegBL_rotpos = new float[]{
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
                8.39f,7.22f,12.37f,-0.56f,-0.01f,0.27f,
        };
        this.UpperLegBL.rotateAngleX   = UpperLegBL_rotpos[frame*6+3];
        this.UpperLegBL.rotateAngleY   = UpperLegBL_rotpos[frame*6+5];
        this.UpperLegBL.rotateAngleZ   = UpperLegBL_rotpos[frame*6+4];
        this.UpperLegBL.rotationPointX = UpperLegBL_rotpos[frame*6];
        this.UpperLegBL.rotationPointY = 24-UpperLegBL_rotpos[frame*6+2];
        this.UpperLegBL.rotationPointZ = UpperLegBL_rotpos[frame*6+1];
        final float[] FootBR_rotpos = new float[]{
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
                -7.81f,6.89f,0.83f,-0.00f,0.01f,-0.27f,
        };
        this.FootBR.rotateAngleX   = FootBR_rotpos[frame*6+3];
        this.FootBR.rotateAngleY   = FootBR_rotpos[frame*6+5];
        this.FootBR.rotateAngleZ   = FootBR_rotpos[frame*6+4];
        this.FootBR.rotationPointX = FootBR_rotpos[frame*6];
        this.FootBR.rotationPointY = 24-FootBR_rotpos[frame*6+2];
        this.FootBR.rotationPointZ = FootBR_rotpos[frame*6+1];
        final float[] LowerLegBL_rotpos = new float[]{
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
                7.70f,8.13f,4.66f,-0.56f,-0.01f,0.27f,
        };
        this.LowerLegBL.rotateAngleX   = LowerLegBL_rotpos[frame*6+3];
        this.LowerLegBL.rotateAngleY   = LowerLegBL_rotpos[frame*6+5];
        this.LowerLegBL.rotateAngleZ   = LowerLegBL_rotpos[frame*6+4];
        this.LowerLegBL.rotationPointX = LowerLegBL_rotpos[frame*6];
        this.LowerLegBL.rotationPointY = 24-LowerLegBL_rotpos[frame*6+2];
        this.LowerLegBL.rotationPointZ = LowerLegBL_rotpos[frame*6+1];
        final float[] UpperLegBR_rotpos = new float[]{
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
                -8.39f,7.22f,12.37f,-0.56f,0.01f,-0.27f,
        };
        this.UpperLegBR.rotateAngleX   = UpperLegBR_rotpos[frame*6+3];
        this.UpperLegBR.rotateAngleY   = UpperLegBR_rotpos[frame*6+5];
        this.UpperLegBR.rotateAngleZ   = UpperLegBR_rotpos[frame*6+4];
        this.UpperLegBR.rotationPointX = UpperLegBR_rotpos[frame*6];
        this.UpperLegBR.rotationPointY = 24-UpperLegBR_rotpos[frame*6+2];
        this.UpperLegBR.rotationPointZ = UpperLegBR_rotpos[frame*6+1];
    }

    public OtterModel() {
        this.init();
    }
    @Override public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadrotateAngleY, float headrotateAngleX) {
        if (entityIn.animation == OtterEntity.Animation.RUNNING)
            this.poseRun(entityIn.frame);
        else
            this.poseIdle(entityIn.frame);
    }
}
