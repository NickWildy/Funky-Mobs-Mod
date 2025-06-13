package net.nickwildy.fmm;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.nickwildy.fmm.entity.ModEntities;
import net.nickwildy.fmm.entity.client.BaldiEntityRenderer;
import net.nickwildy.fmm.entity.custom.BaldiEntity;
import net.nickwildy.fmm.item.ModItems;
import net.nickwildy.fmm.sounds.CustomSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunkyMobsMod implements ModInitializer {
	public static final String MOD_ID = "funky-mobs-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();

		ModItems.registerModItems();

		CustomSounds.initialize();

		FabricDefaultAttributeRegistry.register(ModEntities.BALDI, BaldiEntity.setAttributes());
	}
}