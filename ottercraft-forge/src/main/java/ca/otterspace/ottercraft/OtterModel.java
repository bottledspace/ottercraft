package ca.otterspace.ottercraft;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) class OtterModel<T extends OtterEntity> extends EntityModel<T> {
    protected boolean smol = false;
    protected ModelRenderer armL;
    protected ModelRenderer armR;
    protected ModelRenderer chest;
    protected ModelRenderer earL;
    protected ModelRenderer earR;
    protected ModelRenderer footBL;
    protected ModelRenderer footL;
    protected ModelRenderer footR;
    protected ModelRenderer head;
    protected ModelRenderer lowerLegBR;
    protected ModelRenderer neck;
    protected ModelRenderer nose;
    protected ModelRenderer tail1;
    protected ModelRenderer tail2;
    protected ModelRenderer tail3;
    protected ModelRenderer tail4;
    protected ModelRenderer tail5;
    protected ModelRenderer torso;
    protected ModelRenderer upperLegBL;
    protected ModelRenderer footBR;
    protected ModelRenderer lowerLegBL;
    protected ModelRenderer upperLegBR;

    private void init() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.armL = new ModelRenderer(this, 166, 82);
        this.armR = new ModelRenderer(this, 137, 82);
        this.chest = new ModelRenderer(this, 198, 77);
        this.earL = new ModelRenderer(this, 199, 189);
        this.earR = new ModelRenderer(this, 237, 190);
        this.footBL = new ModelRenderer(this, 91, 193);
        this.footL = new ModelRenderer(this, 140, 112);
        this.footR = new ModelRenderer(this, 169, 112);
        this.head = new ModelRenderer(this, 206, 149);
        this.neck = new ModelRenderer(this, 203, 115);
        this.nose = new ModelRenderer(this, 217, 177);
        this.tail1 = new ModelRenderer(this, 0, 0);
        this.tail2 = new ModelRenderer(this, 63, 30);
        this.tail3 = new ModelRenderer(this, 43, 69);
        this.tail4 = new ModelRenderer(this, 106, 10);
        this.tail5 = new ModelRenderer(this, 16, 33);
        this.torso = new ModelRenderer(this, 8, 217);
        this.upperLegBL = new ModelRenderer(this, 99, 81);
        this.footBR = new ModelRenderer(this, 92, 222);
        this.lowerLegBL = new ModelRenderer(this, 92, 164);
        this.upperLegBR = new ModelRenderer(this, 98, 106);
        this.lowerLegBR = new ModelRenderer(this, 91, 137);

        this.head.addBox(-5.00f,-4.50f,-4.50f,10.00f,9.00f,9.00f);
        this.nose.addBox(-3.00f,-3.00f ,-1.50f,6.00f,6.00f,3.00f);
        this.nose.setRotationPoint(0, +2f,-5.5f);
        this.head.addChild(nose);
        this.earL.addBox(-2.00f,-2.00f,-1.50f,4.00f,4.00f,3.00f);
        this.earL.setRotationPoint(-5f,-3.5f,3f);
        this.earL.rotateAngleX = -16.9f* ((float)Math.PI / 180F);
        this.earL.rotateAngleZ = 38.3f* ((float)Math.PI / 180F);
        this.earL.rotateAngleY = -10.7f* ((float)Math.PI / 180F);
        this.head.addChild(earL);
        this.earR.addBox(-2.00f,-2.00f,-1.50f,4.00f,4.00f,3.00f);
        this.earR.setRotationPoint(5f,-3.5f,3f);
        this.earR.rotateAngleX = -16.9f* ((float)Math.PI / 180F);
        this.earR.rotateAngleZ = -38.3f* ((float)Math.PI / 180F);
        this.earR.rotateAngleY = 10.7f* ((float)Math.PI / 180F);
        this.head.addChild(earR);

        this.armL.addBox(-2.50f,-5.50f,-2.50f,5.00f,11.00f,5.00f);
        this.armR.addBox(-2.50f,-5.50f,-2.50f,5.00f,11.00f,5.00f);
        this.chest.addBox(-5.50f,-5.50f,-9.00f,11.00f,11.00f,18.00f);
        this.footBL.addBox(-2.50f,-2.00f,-7.00f,5.00f,4.00f,14.00f);
        this.footL.addBox(-2.00f,-7.00f,-2.00f,4.00f,14.00f,4.00f);
        this.footR.addBox(-2.00f,-7.00f,-2.00f,4.00f,14.00f,4.00f);
        this.lowerLegBR.addBox(-2.50f,-2.50f,-7.00f,5.00f,5.00f,14.00f);
        this.neck.addBox(-4.50f,-4.50f,-7.00f,9.00f,9.00f,14.00f);
        this.tail1.addBox(-5.00f,-5.00f,-6.00f,10.00f,10.00f,12.00f);
        this.tail2.addBox(-4.00f,-4.00f,-7.00f,8.00f,8.00f,14.00f);
        this.tail3.addBox(-3.00f,-3.00f,-6.00f,6.00f,6.00f,12.00f);
        this.tail4.addBox(-2.00f,-2.00f,-5.00f,4.00f,4.00f,10.00f);
        this.tail5.addBox(-1.00f,-1.00f,-5.00f,2.00f,2.00f,10.00f);
        this.torso.addBox(-7.00f,-7.00f,-9.00f,14.00f,14.00f,18.00f);
        this.upperLegBL.addBox(-3.00f,-6.00f,-3.00f,6.00f,12.00f,6.00f);
        this.footBR.addBox(-2.50f,-2.00f,-7.00f,5.00f,4.00f,14.00f);
        this.lowerLegBL.addBox(-2.50f,-2.50f,-7.00f,5.00f,5.00f,14.00f);
        this.upperLegBR.addBox(-3.00f,-6.00f,-3.00f,6.00f,12.00f,6.00f);
    }
    public void render(MatrixStack ms, IVertexBuilder b, int f, int g, float red, float green, float blue, float alpha) {
        ms.push();
        if (this.smol) {
            ms.scale(0.5f, 0.5f, 0.5f);
            ms.translate(0.0f, 1.5f, 0.0f);
        }
        this.armL.render(ms, b, f, g, red, green, blue, alpha);
        this.armR.render(ms, b, f, g, red, green, blue, alpha);
        this.chest.render(ms, b, f, g, red, green, blue, alpha);
        this.footBL.render(ms, b, f, g, red, green, blue, alpha);
        this.footL.render(ms, b, f, g, red, green, blue, alpha);
        this.footR.render(ms, b, f, g, red, green, blue, alpha);
        this.head.render(ms, b, f, g, red, green, blue, alpha);
        this.lowerLegBR.render(ms, b, f, g, red, green, blue, alpha);
        this.neck.render(ms, b, f, g, red, green, blue, alpha);
        this.tail1.render(ms, b, f, g, red, green, blue, alpha);
        this.tail2.render(ms, b, f, g, red, green, blue, alpha);
        this.tail3.render(ms, b, f, g, red, green, blue, alpha);
        this.tail4.render(ms, b, f, g, red, green, blue, alpha);
        this.tail5.render(ms, b, f, g, red, green, blue, alpha);
        this.torso.render(ms, b, f, g, red, green, blue, alpha);
        this.upperLegBL.render(ms, b, f, g, red, green, blue, alpha);
        this.footBR.render(ms, b, f, g, red, green, blue, alpha);
        this.lowerLegBL.render(ms, b, f, g, red, green, blue, alpha);
        this.upperLegBR.render(ms, b, f, g, red, green, blue, alpha);
        ms.pop();
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
        this.armL.rotateAngleX   = ArmL_rotpos[frame*6+3];
        this.armL.rotateAngleY   = ArmL_rotpos[frame*6+5];
        this.armL.rotateAngleZ   = ArmL_rotpos[frame*6+4];
        this.armL.rotationPointX = ArmL_rotpos[frame*6];
        this.armL.rotationPointY = 24-ArmL_rotpos[frame*6+2];
        this.armL.rotationPointZ = ArmL_rotpos[frame*6+1];
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
        this.armR.rotateAngleX   = ArmR_rotpos[frame*6+3];
        this.armR.rotateAngleY   = ArmR_rotpos[frame*6+5];
        this.armR.rotateAngleZ   = ArmR_rotpos[frame*6+4];
        this.armR.rotationPointX = ArmR_rotpos[frame*6];
        this.armR.rotationPointY = 24-ArmR_rotpos[frame*6+2];
        this.armR.rotationPointZ = ArmR_rotpos[frame*6+1];
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
        this.chest.rotateAngleX   = Chest_rotpos[frame*6+3];
        this.chest.rotateAngleY   = Chest_rotpos[frame*6+5];
        this.chest.rotateAngleZ   = Chest_rotpos[frame*6+4];
        this.chest.rotationPointX = Chest_rotpos[frame*6];
        this.chest.rotationPointY = 24-Chest_rotpos[frame*6+2];
        this.chest.rotationPointZ = Chest_rotpos[frame*6+1];
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
        this.footBL.rotateAngleX   = FootBL_rotpos[frame*6+3];
        this.footBL.rotateAngleY   = FootBL_rotpos[frame*6+5];
        this.footBL.rotateAngleZ   = FootBL_rotpos[frame*6+4];
        this.footBL.rotationPointX = FootBL_rotpos[frame*6];
        this.footBL.rotationPointY = 24-FootBL_rotpos[frame*6+2];
        this.footBL.rotationPointZ = FootBL_rotpos[frame*6+1];
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
        this.footL.rotateAngleX   = FootL_rotpos[frame*6+3];
        this.footL.rotateAngleY   = FootL_rotpos[frame*6+5];
        this.footL.rotateAngleZ   = FootL_rotpos[frame*6+4];
        this.footL.rotationPointX = FootL_rotpos[frame*6];
        this.footL.rotationPointY = 24-FootL_rotpos[frame*6+2];
        this.footL.rotationPointZ = FootL_rotpos[frame*6+1];
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
        this.footR.rotateAngleX   = FootR_rotpos[frame*6+3];
        this.footR.rotateAngleY   = FootR_rotpos[frame*6+5];
        this.footR.rotateAngleZ   = FootR_rotpos[frame*6+4];
        this.footR.rotationPointX = FootR_rotpos[frame*6];
        this.footR.rotationPointY = 24-FootR_rotpos[frame*6+2];
        this.footR.rotationPointZ = FootR_rotpos[frame*6+1];
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
        this.head.rotateAngleX   = Head_rotpos[frame*6+3];
        this.head.rotateAngleY   = Head_rotpos[frame*6+5];
        this.head.rotateAngleZ   = Head_rotpos[frame*6+4];
        this.head.rotationPointX = Head_rotpos[frame*6];
        this.head.rotationPointY = 24-Head_rotpos[frame*6+2];
        this.head.rotationPointZ = Head_rotpos[frame*6+1];
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
        this.lowerLegBR.rotateAngleX   = LowerLegBR_rotpos[frame*6+3];
        this.lowerLegBR.rotateAngleY   = LowerLegBR_rotpos[frame*6+5];
        this.lowerLegBR.rotateAngleZ   = LowerLegBR_rotpos[frame*6+4];
        this.lowerLegBR.rotationPointX = LowerLegBR_rotpos[frame*6];
        this.lowerLegBR.rotationPointY = 24-LowerLegBR_rotpos[frame*6+2];
        this.lowerLegBR.rotationPointZ = LowerLegBR_rotpos[frame*6+1];
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
        this.neck.rotateAngleX   = Neck_rotpos[frame*6+3];
        this.neck.rotateAngleY   = Neck_rotpos[frame*6+5];
        this.neck.rotateAngleZ   = Neck_rotpos[frame*6+4];
        this.neck.rotationPointX = Neck_rotpos[frame*6];
        this.neck.rotationPointY = 24-Neck_rotpos[frame*6+2];
        this.neck.rotationPointZ = Neck_rotpos[frame*6+1];
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
        this.tail1.rotateAngleX   = Tail1_rotpos[frame*6+3];
        this.tail1.rotateAngleY   = Tail1_rotpos[frame*6+5];
        this.tail1.rotateAngleZ   = Tail1_rotpos[frame*6+4];
        this.tail1.rotationPointX = Tail1_rotpos[frame*6];
        this.tail1.rotationPointY = 24-Tail1_rotpos[frame*6+2];
        this.tail1.rotationPointZ = Tail1_rotpos[frame*6+1];
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
        this.tail2.rotateAngleX   = Tail2_rotpos[frame*6+3];
        this.tail2.rotateAngleY   = Tail2_rotpos[frame*6+5];
        this.tail2.rotateAngleZ   = Tail2_rotpos[frame*6+4];
        this.tail2.rotationPointX = Tail2_rotpos[frame*6];
        this.tail2.rotationPointY = 24-Tail2_rotpos[frame*6+2];
        this.tail2.rotationPointZ = Tail2_rotpos[frame*6+1];
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
        this.tail3.rotateAngleX   = Tail3_rotpos[frame*6+3];
        this.tail3.rotateAngleY   = Tail3_rotpos[frame*6+5];
        this.tail3.rotateAngleZ   = Tail3_rotpos[frame*6+4];
        this.tail3.rotationPointX = Tail3_rotpos[frame*6];
        this.tail3.rotationPointY = 24-Tail3_rotpos[frame*6+2];
        this.tail3.rotationPointZ = Tail3_rotpos[frame*6+1];
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
        this.tail4.rotateAngleX   = Tail4_rotpos[frame*6+3];
        this.tail4.rotateAngleY   = Tail4_rotpos[frame*6+5];
        this.tail4.rotateAngleZ   = Tail4_rotpos[frame*6+4];
        this.tail4.rotationPointX = Tail4_rotpos[frame*6];
        this.tail4.rotationPointY = 24-Tail4_rotpos[frame*6+2];
        this.tail4.rotationPointZ = Tail4_rotpos[frame*6+1];
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
        this.tail5.rotateAngleX   = Tail5_rotpos[frame*6+3];
        this.tail5.rotateAngleY   = Tail5_rotpos[frame*6+5];
        this.tail5.rotateAngleZ   = Tail5_rotpos[frame*6+4];
        this.tail5.rotationPointX = Tail5_rotpos[frame*6];
        this.tail5.rotationPointY = 24-Tail5_rotpos[frame*6+2];
        this.tail5.rotationPointZ = Tail5_rotpos[frame*6+1];
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
        this.torso.rotateAngleX   = Torso_rotpos[frame*6+3];
        this.torso.rotateAngleY   = Torso_rotpos[frame*6+5];
        this.torso.rotateAngleZ   = Torso_rotpos[frame*6+4];
        this.torso.rotationPointX = Torso_rotpos[frame*6];
        this.torso.rotationPointY = 24-Torso_rotpos[frame*6+2];
        this.torso.rotationPointZ = Torso_rotpos[frame*6+1];
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
        this.upperLegBL.rotateAngleX   = UpperLegBL_rotpos[frame*6+3];
        this.upperLegBL.rotateAngleY   = UpperLegBL_rotpos[frame*6+5];
        this.upperLegBL.rotateAngleZ   = UpperLegBL_rotpos[frame*6+4];
        this.upperLegBL.rotationPointX = UpperLegBL_rotpos[frame*6];
        this.upperLegBL.rotationPointY = 24-UpperLegBL_rotpos[frame*6+2];
        this.upperLegBL.rotationPointZ = UpperLegBL_rotpos[frame*6+1];
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
        this.footBR.rotateAngleX   = FootBR_rotpos[frame*6+3];
        this.footBR.rotateAngleY   = FootBR_rotpos[frame*6+5];
        this.footBR.rotateAngleZ   = FootBR_rotpos[frame*6+4];
        this.footBR.rotationPointX = FootBR_rotpos[frame*6];
        this.footBR.rotationPointY = 24-FootBR_rotpos[frame*6+2];
        this.footBR.rotationPointZ = FootBR_rotpos[frame*6+1];
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
        this.lowerLegBL.rotateAngleX   = LowerLegBL_rotpos[frame*6+3];
        this.lowerLegBL.rotateAngleY   = LowerLegBL_rotpos[frame*6+5];
        this.lowerLegBL.rotateAngleZ   = LowerLegBL_rotpos[frame*6+4];
        this.lowerLegBL.rotationPointX = LowerLegBL_rotpos[frame*6];
        this.lowerLegBL.rotationPointY = 24-LowerLegBL_rotpos[frame*6+2];
        this.lowerLegBL.rotationPointZ = LowerLegBL_rotpos[frame*6+1];
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
        this.upperLegBR.rotateAngleX   = UpperLegBR_rotpos[frame*6+3];
        this.upperLegBR.rotateAngleY   = UpperLegBR_rotpos[frame*6+5];
        this.upperLegBR.rotateAngleZ   = UpperLegBR_rotpos[frame*6+4];
        this.upperLegBR.rotationPointX = UpperLegBR_rotpos[frame*6];
        this.upperLegBR.rotationPointY = 24-UpperLegBR_rotpos[frame*6+2];
        this.upperLegBR.rotationPointZ = UpperLegBR_rotpos[frame*6+1];
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
        this.armL.rotateAngleX   = ArmL_rotpos[frame*6+3];
        this.armL.rotateAngleY   = ArmL_rotpos[frame*6+5];
        this.armL.rotateAngleZ   = ArmL_rotpos[frame*6+4];
        this.armL.rotationPointX = ArmL_rotpos[frame*6];
        this.armL.rotationPointY = 24-ArmL_rotpos[frame*6+2];
        this.armL.rotationPointZ = ArmL_rotpos[frame*6+1];
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
        this.armR.rotateAngleX   = ArmR_rotpos[frame*6+3];
        this.armR.rotateAngleY   = ArmR_rotpos[frame*6+5];
        this.armR.rotateAngleZ   = ArmR_rotpos[frame*6+4];
        this.armR.rotationPointX = ArmR_rotpos[frame*6];
        this.armR.rotationPointY = 24-ArmR_rotpos[frame*6+2];
        this.armR.rotationPointZ = ArmR_rotpos[frame*6+1];
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
        this.chest.rotateAngleX   = Chest_rotpos[frame*6+3];
        this.chest.rotateAngleY   = Chest_rotpos[frame*6+5];
        this.chest.rotateAngleZ   = Chest_rotpos[frame*6+4];
        this.chest.rotationPointX = Chest_rotpos[frame*6];
        this.chest.rotationPointY = 24-Chest_rotpos[frame*6+2];
        this.chest.rotationPointZ = Chest_rotpos[frame*6+1];
        /*final float[] EarL_rotpos = new float[]{
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
        this.earL.rotateAngleX   = EarL_rotpos[frame*6+3];
        this.earL.rotateAngleY   = EarL_rotpos[frame*6+5];
        this.earL.rotateAngleZ   = EarL_rotpos[frame*6+4];
        this.earL.rotationPointX = EarL_rotpos[frame*6];
        this.earL.rotationPointY = 24-EarL_rotpos[frame*6+2];
        this.earL.rotationPointZ = EarL_rotpos[frame*6+1];
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
        this.earR.rotateAngleX   = EarR_rotpos[frame*6+3];
        this.earR.rotateAngleY   = EarR_rotpos[frame*6+5];
        this.earR.rotateAngleZ   = EarR_rotpos[frame*6+4];
        this.earR.rotationPointX = EarR_rotpos[frame*6];
        this.earR.rotationPointY = 24-EarR_rotpos[frame*6+2];
        this.earR.rotationPointZ = EarR_rotpos[frame*6+1];*/
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
        this.footBL.rotateAngleX   = FootBL_rotpos[frame*6+3];
        this.footBL.rotateAngleY   = FootBL_rotpos[frame*6+5];
        this.footBL.rotateAngleZ   = FootBL_rotpos[frame*6+4];
        this.footBL.rotationPointX = FootBL_rotpos[frame*6];
        this.footBL.rotationPointY = 24-FootBL_rotpos[frame*6+2];
        this.footBL.rotationPointZ = FootBL_rotpos[frame*6+1];
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
        this.footL.rotateAngleX   = FootL_rotpos[frame*6+3];
        this.footL.rotateAngleY   = FootL_rotpos[frame*6+5];
        this.footL.rotateAngleZ   = FootL_rotpos[frame*6+4];
        this.footL.rotationPointX = FootL_rotpos[frame*6];
        this.footL.rotationPointY = 24-FootL_rotpos[frame*6+2];
        this.footL.rotationPointZ = FootL_rotpos[frame*6+1];
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
        this.footR.rotateAngleX   = FootR_rotpos[frame*6+3];
        this.footR.rotateAngleY   = FootR_rotpos[frame*6+5];
        this.footR.rotateAngleZ   = FootR_rotpos[frame*6+4];
        this.footR.rotationPointX = FootR_rotpos[frame*6];
        this.footR.rotationPointY = 24-FootR_rotpos[frame*6+2];
        this.footR.rotationPointZ = FootR_rotpos[frame*6+1];
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
        this.head.rotateAngleX   = Head_rotpos[frame*6+3];
        this.head.rotateAngleY   = Head_rotpos[frame*6+5];
        this.head.rotateAngleZ   = Head_rotpos[frame*6+4];
        this.head.rotationPointX = Head_rotpos[frame*6];
        this.head.rotationPointY = 24-Head_rotpos[frame*6+2];
        this.head.rotationPointZ = Head_rotpos[frame*6+1];
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
        this.lowerLegBR.rotateAngleX   = LowerLegBR_rotpos[frame*6+3];
        this.lowerLegBR.rotateAngleY   = LowerLegBR_rotpos[frame*6+5];
        this.lowerLegBR.rotateAngleZ   = LowerLegBR_rotpos[frame*6+4];
        this.lowerLegBR.rotationPointX = LowerLegBR_rotpos[frame*6];
        this.lowerLegBR.rotationPointY = 24-LowerLegBR_rotpos[frame*6+2];
        this.lowerLegBR.rotationPointZ = LowerLegBR_rotpos[frame*6+1];
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
        this.neck.rotateAngleX   = Neck_rotpos[frame*6+3];
        this.neck.rotateAngleY   = Neck_rotpos[frame*6+5];
        this.neck.rotateAngleZ   = Neck_rotpos[frame*6+4];
        this.neck.rotationPointX = Neck_rotpos[frame*6];
        this.neck.rotationPointY = 24-Neck_rotpos[frame*6+2];
        this.neck.rotationPointZ = Neck_rotpos[frame*6+1];
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
        this.tail1.rotateAngleX   = Tail1_rotpos[frame*6+3];
        this.tail1.rotateAngleY   = Tail1_rotpos[frame*6+5];
        this.tail1.rotateAngleZ   = Tail1_rotpos[frame*6+4];
        this.tail1.rotationPointX = Tail1_rotpos[frame*6];
        this.tail1.rotationPointY = 24-Tail1_rotpos[frame*6+2];
        this.tail1.rotationPointZ = Tail1_rotpos[frame*6+1];
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
        this.tail2.rotateAngleX   = Tail2_rotpos[frame*6+3];
        this.tail2.rotateAngleY   = Tail2_rotpos[frame*6+5];
        this.tail2.rotateAngleZ   = Tail2_rotpos[frame*6+4];
        this.tail2.rotationPointX = Tail2_rotpos[frame*6];
        this.tail2.rotationPointY = 24-Tail2_rotpos[frame*6+2];
        this.tail2.rotationPointZ = Tail2_rotpos[frame*6+1];
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
        this.tail3.rotateAngleX   = Tail3_rotpos[frame*6+3];
        this.tail3.rotateAngleY   = Tail3_rotpos[frame*6+5];
        this.tail3.rotateAngleZ   = Tail3_rotpos[frame*6+4];
        this.tail3.rotationPointX = Tail3_rotpos[frame*6];
        this.tail3.rotationPointY = 24-Tail3_rotpos[frame*6+2];
        this.tail3.rotationPointZ = Tail3_rotpos[frame*6+1];
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
        this.tail4.rotateAngleX   = Tail4_rotpos[frame*6+3];
        this.tail4.rotateAngleY   = Tail4_rotpos[frame*6+5];
        this.tail4.rotateAngleZ   = Tail4_rotpos[frame*6+4];
        this.tail4.rotationPointX = Tail4_rotpos[frame*6];
        this.tail4.rotationPointY = 24-Tail4_rotpos[frame*6+2];
        this.tail4.rotationPointZ = Tail4_rotpos[frame*6+1];
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
        this.tail5.rotateAngleX   = Tail5_rotpos[frame*6+3];
        this.tail5.rotateAngleY   = Tail5_rotpos[frame*6+5];
        this.tail5.rotateAngleZ   = Tail5_rotpos[frame*6+4];
        this.tail5.rotationPointX = Tail5_rotpos[frame*6];
        this.tail5.rotationPointY = 24-Tail5_rotpos[frame*6+2];
        this.tail5.rotationPointZ = Tail5_rotpos[frame*6+1];
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
        this.torso.rotateAngleX   = Torso_rotpos[frame*6+3];
        this.torso.rotateAngleY   = Torso_rotpos[frame*6+5];
        this.torso.rotateAngleZ   = Torso_rotpos[frame*6+4];
        this.torso.rotationPointX = Torso_rotpos[frame*6];
        this.torso.rotationPointY = 24-Torso_rotpos[frame*6+2];
        this.torso.rotationPointZ = Torso_rotpos[frame*6+1];
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
        this.upperLegBL.rotateAngleX   = UpperLegBL_rotpos[frame*6+3];
        this.upperLegBL.rotateAngleY   = UpperLegBL_rotpos[frame*6+5];
        this.upperLegBL.rotateAngleZ   = UpperLegBL_rotpos[frame*6+4];
        this.upperLegBL.rotationPointX = UpperLegBL_rotpos[frame*6];
        this.upperLegBL.rotationPointY = 24-UpperLegBL_rotpos[frame*6+2];
        this.upperLegBL.rotationPointZ = UpperLegBL_rotpos[frame*6+1];
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
        this.footBR.rotateAngleX   = FootBR_rotpos[frame*6+3];
        this.footBR.rotateAngleY   = FootBR_rotpos[frame*6+5];
        this.footBR.rotateAngleZ   = FootBR_rotpos[frame*6+4];
        this.footBR.rotationPointX = FootBR_rotpos[frame*6];
        this.footBR.rotationPointY = 24-FootBR_rotpos[frame*6+2];
        this.footBR.rotationPointZ = FootBR_rotpos[frame*6+1];
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
        this.lowerLegBL.rotateAngleX   = LowerLegBL_rotpos[frame*6+3];
        this.lowerLegBL.rotateAngleY   = LowerLegBL_rotpos[frame*6+5];
        this.lowerLegBL.rotateAngleZ   = LowerLegBL_rotpos[frame*6+4];
        this.lowerLegBL.rotationPointX = LowerLegBL_rotpos[frame*6];
        this.lowerLegBL.rotationPointY = 24-LowerLegBL_rotpos[frame*6+2];
        this.lowerLegBL.rotationPointZ = LowerLegBL_rotpos[frame*6+1];
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
        this.upperLegBR.rotateAngleX   = UpperLegBR_rotpos[frame*6+3];
        this.upperLegBR.rotateAngleY   = UpperLegBR_rotpos[frame*6+5];
        this.upperLegBR.rotateAngleZ   = UpperLegBR_rotpos[frame*6+4];
        this.upperLegBR.rotationPointX = UpperLegBR_rotpos[frame*6];
        this.upperLegBR.rotationPointY = 24-UpperLegBR_rotpos[frame*6+2];
        this.upperLegBR.rotationPointZ = UpperLegBR_rotpos[frame*6+1];
    }

    public OtterModel() {
        this.init();
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadrotateAngleY, float headrotateAngleX) {
        this.smol = (entityIn.getOtterSubspecies() == 0);
        if (entityIn.animation == OtterEntity.Animation.RUNNING)
            this.poseRun(entityIn.frame);
        else {
            this.poseIdle(entityIn.frame);
            this.head.rotateAngleX += headrotateAngleX * ((float)Math.PI / 180F);
            this.head.rotateAngleY = netHeadrotateAngleY * ((float)Math.PI / 180F);
        }
    }
}
