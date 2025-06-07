package net.nickwildy.fmm.entity.client;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.Identifier;
import net.nickwildy.fmm.FunkyMobsMod;
import net.nickwildy.fmm.entity.custom.BaldiEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.processing.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.constant.dataticket.DataTicket;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
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

    //@Override
    //public void setCustomAnimations(BaldiEntity animatable, long instanceId, AnimationState<BaldiEntity> animationState){
        //GeoBone head = getAnimationProcessor().getBone("head");

        //if(head != null){
            //FunkyMobsMod.LOGGER.error("OH SHUCKS!!!!!");
        //}
    //}
}
