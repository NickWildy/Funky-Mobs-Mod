package net.nickwildy.fmm.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.nickwildy.fmm.FunkyMobsMod;

public class CustomSounds {
    private CustomSounds() {
        // private empty constructor to avoid accidental instantiation
    }

    public static final SoundEvent BALDI_DEATH = registerSound("baldi_death");
    public static final SoundEvent BALDI_HURT = registerSound("baldi_hurt");
    public static final SoundEvent BALDI_OH_HI = registerSound("baldi_oh_hi");
    public static final SoundEvent BALDI_UH_OH = registerSound("baldi_uh_oh");

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(FunkyMobsMod.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void initialize() {
        FunkyMobsMod.LOGGER.info("Registering " + FunkyMobsMod.MOD_ID + " Sounds");
    }
}
