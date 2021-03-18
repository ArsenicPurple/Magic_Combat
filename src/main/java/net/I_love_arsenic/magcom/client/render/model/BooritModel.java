package net.I_love_arsenic.magcom.client.render.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.I_love_arsenic.magcom.common.entities.BooritEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BooritModel<T extends BooritEntity> extends EntityModel<T> {
    private final ModelRenderer boorit;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer bipedLeftLeg;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer bipedBody;
    private final ModelRenderer bipedHead;

    public BooritModel() {
        textureWidth = 64;
        textureHeight = 64;

        boorit = new ModelRenderer(this);
        boorit.setRotationPoint(0.0F, 1.0F, 0.0F);
        setRotationAngle(boorit, 0.0F, 3.1416F, 0.0F);


        bipedRightLeg = new ModelRenderer(this);
        bipedRightLeg.setRotationPoint(3.5F, 8.0F, 0.0F);
        boorit.addChild(bipedRightLeg);
        bipedRightLeg.setTextureOffset(0, 36).addBox(-2.5F, 0.0F, -2.0F, 5.0F, 14.0F, 4.0F, 0.0F, false);
        bipedRightLeg.setTextureOffset(30, 35).addBox(-2.5F, 14.0F, -1.0F, 5.0F, 1.0F, 4.0F, 0.0F, false);
        bipedRightLeg.setTextureOffset(36, 4).addBox(-2.5F, 0.0F, 2.0F, 5.0F, 7.0F, 1.0F, 0.0F, false);
        bipedRightLeg.setTextureOffset(36, 50).addBox(-3.5F, 0.0F, -2.0F, 1.0F, 7.0F, 4.0F, 0.0F, false);
        bipedRightLeg.setTextureOffset(36, 50).addBox(2.5F, 0.0F, -2.0F, 1.0F, 7.0F, 4.0F, 0.0F, false);
        bipedRightLeg.setTextureOffset(34, 40).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 4.0F, 1.0F, 0.0F, false);
        bipedRightLeg.setTextureOffset(40, 0).addBox(-2.5F, 8.0F, -3.0F, 5.0F, 2.0F, 1.0F, 0.0F, false);

        bipedLeftLeg = new ModelRenderer(this);
        bipedLeftLeg.setRotationPoint(-3.5F, 8.0F, 0.0F);
        boorit.addChild(bipedLeftLeg);
        bipedLeftLeg.setTextureOffset(0, 36).addBox(-2.5F, 0.0F, -2.0F, 5.0F, 14.0F, 4.0F, 0.0F, true);
        bipedLeftLeg.setTextureOffset(30, 35).addBox(-2.5F, 14.0F, -1.0F, 5.0F, 1.0F, 4.0F, 0.0F, true);
        bipedLeftLeg.setTextureOffset(36, 4).addBox(-2.5F, 0.0F, 2.0F, 5.0F, 7.0F, 1.0F, 0.0F, true);
        bipedLeftLeg.setTextureOffset(36, 50).addBox(-3.5F, 0.0F, -2.0F, 1.0F, 7.0F, 4.0F, 0.0F, true);
        bipedLeftLeg.setTextureOffset(36, 50).addBox(2.5F, 0.0F, -2.0F, 1.0F, 7.0F, 4.0F, 0.0F, true);
        bipedLeftLeg.setTextureOffset(34, 40).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 4.0F, 1.0F, 0.0F, true);
        bipedLeftLeg.setTextureOffset(40, 0).addBox(-2.5F, 8.0F, -3.0F, 5.0F, 2.0F, 1.0F, 0.0F, true);

        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(6.0F, -5.0F, 0.0F);
        boorit.addChild(bipedRightArm);
        bipedRightArm.setTextureOffset(18, 36).addBox(0.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);
        bipedRightArm.setTextureOffset(10, 54).addBox(0.0F, -3.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        bipedRightArm.setTextureOffset(30, 30).addBox(0.0F, -2.0F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
        bipedRightArm.setTextureOffset(30, 30).addBox(0.0F, -2.0F, 2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
        bipedRightArm.setTextureOffset(26, 54).addBox(4.0F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(-6.0F, -5.0F, 0.0F);
        boorit.addChild(bipedLeftArm);
        bipedLeftArm.setTextureOffset(18, 36).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, true);
        bipedLeftArm.setTextureOffset(26, 54).addBox(-5.0F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, true);
        bipedLeftArm.setTextureOffset(30, 30).addBox(-4.0F, -2.0F, 2.0F, 4.0F, 4.0F, 1.0F, 0.0F, true);
        bipedLeftArm.setTextureOffset(30, 30).addBox(-4.0F, -2.0F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, true);
        bipedLeftArm.setTextureOffset(10, 54).addBox(-4.0F, -3.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, true);

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        boorit.addChild(bipedBody);
        bipedBody.setTextureOffset(36, 13).addBox(-3.0F, -1.0F, 3.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        bipedBody.setTextureOffset(36, 13).addBox(-3.0F, 1.0F, 3.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        bipedBody.setTextureOffset(36, 13).addBox(-3.0F, 3.0F, 3.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        bipedBody.setTextureOffset(36, 13).addBox(-3.0F, 5.0F, 3.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
        bipedBody.setTextureOffset(0, 0).addBox(-6.0F, -7.0F, -3.0F, 12.0F, 15.0F, 6.0F, 0.0F, false);
        bipedBody.setTextureOffset(34, 45).addBox(-5.0F, -6.0F, 3.0F, 4.0F, 4.0F, 1.0F, 0.0F, true);
        bipedBody.setTextureOffset(34, 45).addBox(1.0F, -6.0F, 3.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

        bipedHead = new ModelRenderer(this);
        bipedHead.setRotationPoint(0.0F, -7.0F, 0.0F);
        boorit.addChild(bipedHead);
        bipedHead.setTextureOffset(0, 21).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);
        bipedHead.setTextureOffset(30, 0).addBox(-7.0F, -7.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, true);
        bipedHead.setTextureOffset(36, 15).addBox(-7.0F, -11.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
        bipedHead.setTextureOffset(30, 0).addBox(4.0F, -7.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
        bipedHead.setTextureOffset(36, 15).addBox(5.0F, -11.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
        bipedHead.setTextureOffset(30, 21).addBox(-2.0F, -4.0F, 3.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        boorit.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
