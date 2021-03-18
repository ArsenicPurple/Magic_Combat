package net.I_love_arsenic.magcom.client.render.entity;

import net.I_love_arsenic.magcom.MagCom;
import net.I_love_arsenic.magcom.client.render.model.BooritModel;
import net.I_love_arsenic.magcom.common.entities.BooritEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BooritRenderer extends MobRenderer<BooritEntity, BooritModel<BooritEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(MagCom.MOD_ID, "textures/entities/boorit.png");

    public BooritRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BooritModel<>(), 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(BooritEntity entity) {
        return TEXTURE;
    }
}
