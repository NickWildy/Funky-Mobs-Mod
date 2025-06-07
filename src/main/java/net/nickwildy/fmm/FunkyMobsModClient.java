package net.nickwildy.fmm;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.nickwildy.fmm.entity.ModEntities;
import net.nickwildy.fmm.entity.client.BaldiEntityRenderer;

public class FunkyMobsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BALDI, (context) -> {
            return new BaldiEntityRenderer<>(context);
        });
    }
}
