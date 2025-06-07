package net.nickwildy.fmm.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.nickwildy.fmm.entity.custom.BaldiEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BaldiEntityRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BaldiEntity, R> {
    public BaldiEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new BaldiEntityModel());
    }
}
