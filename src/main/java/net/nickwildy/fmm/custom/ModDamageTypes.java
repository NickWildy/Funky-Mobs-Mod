package net.nickwildy.fmm.custom;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.nickwildy.fmm.FunkyMobsMod;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> SLAP_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(FunkyMobsMod.MOD_ID, "slap"));

}
