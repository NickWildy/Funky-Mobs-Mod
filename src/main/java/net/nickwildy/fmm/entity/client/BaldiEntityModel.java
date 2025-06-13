package net.nickwildy.fmm.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.nickwildy.fmm.entity.custom.BaldiEntity;
import software.bernie.geckolib.animatable.processing.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class BaldiEntityModel extends GeoModel<BaldiEntity> {

    @Override
    public Identifier getModelResource(GeoRenderState renderState) {
        return Identifier.of("funky-mobs-mod", "geckolib/models/baldi.geo.json");
    }

    @Override
    public Identifier getTextureResource(GeoRenderState renderState) {
        return Identifier.of("funky-mobs-mod", "textures/baldi.png");
    }

    @Override
    public Identifier getAnimationResource(BaldiEntity animatable) {
        return Identifier.of("funky-mobs-mod", "geckolib/animations/baldi.animation.json");
    }

    @Override
    public void setCustomAnimations(AnimationState<BaldiEntity> animationState){
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            float headPitch = animationState.getData(DataTickets.ENTITY_PITCH);
            float headYaw = animationState.getData(DataTickets.ENTITY_YAW);

            head.setRotX(-headPitch * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(-headYaw * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
