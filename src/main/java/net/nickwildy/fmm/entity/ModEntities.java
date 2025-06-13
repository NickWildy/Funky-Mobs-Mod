package net.nickwildy.fmm.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.nickwildy.fmm.FunkyMobsMod;
import net.nickwildy.fmm.entity.custom.BaldiEntity;

public class ModEntities {
    public static final EntityType<BaldiEntity> BALDI = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(FunkyMobsMod.MOD_ID, "baldi"),
            EntityType.Builder.create(BaldiEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.8f, 3.5f).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,Identifier.of(FunkyMobsMod.MOD_ID, "baldi"))));

    public static void registerModEntities(){
        FunkyMobsMod.LOGGER.info("Registering Mod Entities for " + FunkyMobsMod.MOD_ID);
    }

}
