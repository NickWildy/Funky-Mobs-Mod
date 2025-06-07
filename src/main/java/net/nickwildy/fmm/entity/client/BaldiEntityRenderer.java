package net.nickwildy.fmm.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.nickwildy.fmm.entity.custom.BaldiEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BaldiEntityRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BaldiEntity, R> {
    public BaldiEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BaldiEntityModel());
    }
}
