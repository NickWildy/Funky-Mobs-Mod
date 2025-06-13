package net.nickwildy.fmm.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.nickwildy.fmm.entity.custom.BaldiEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.layer.ItemInHandGeoLayer;

public class BaldiEntityRenderer<R extends LivingEntityRenderState & GeoRenderState> extends GeoEntityRenderer<BaldiEntity, R> {
    public BaldiEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new BaldiEntityModel());
        this.addRenderLayer(new ItemInHandGeoLayer<>(this, "Right_arm", "held_item_hook"));
    }

    private BaldiEntity entityb;

    @Override
    public void updateRenderState(BaldiEntity entity, R entityRenderState, float partialTick) {
        super.updateRenderState(entity, entityRenderState, partialTick);

        if (entityRenderState instanceof LivingEntityRenderState livingEntityRenderState)
            extractLivingEntityRenderState((LivingEntity)entity, livingEntityRenderState, partialTick, this.itemModelResolver);

        fillRenderState(entity, null, entityRenderState, partialTick);

        entityb = entity;
    }
}
