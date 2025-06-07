package net.nickwildy.fmm.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.nickwildy.fmm.FunkyMobsMod;
import net.nickwildy.fmm.entity.custom.BaldiEntity;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BaldiEntityModel extends DefaultedEntityGeoModel<BaldiEntity> {
    public BaldiEntityModel() {
        super(ResourceLocation.fromNamespaceAndPath(FunkyMobsMod.MOD_ID, "Baldi"));
    }
}
