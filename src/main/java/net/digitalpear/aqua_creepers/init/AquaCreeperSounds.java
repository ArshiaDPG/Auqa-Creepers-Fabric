package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class AquaCreeperSounds {

    public static SoundEvent register(String name){
        return Registry.register(Registries.SOUND_EVENT, AquaCreepers.id(name), SoundEvent.of(AquaCreepers.id(name)));
    }

    public static final SoundEvent ENTITY_AQUA_CREEPER_HURT = register("aqua_creeper_hurt");
    public static final SoundEvent ENTITY_AQUA_CREEPER_SWIM = register("aqua_creeper_swim");
    public static final SoundEvent UNDERWATER_EXPLOSION = register("underwater_explosion");
    public static final SoundEvent UNDERWATER_FUSE = register("underwater_fuse");

    public static void init(){
    }
}
