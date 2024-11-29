package net.digitalpear.aqua_creepers;

import net.digitalpear.aqua_creepers.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;


public class AquaCreepers implements ModInitializer {
    public static String MOD_ID = "aqua_creepers";

    public static Identifier id(String name){
        return Identifier.of(MOD_ID, name);
    }

    /*
        TODO:
        -Music discs that only aqua creeper drops (?)
     */

    /*
        KNOWN ISSUES:
        -Explosion particles do not appear sometimes.
        -Vanilla particles still appear when Explosion Enhancement is installed.
     */

    @Override
    public void onInitialize() {
        AquaCreeperEntityTypes.init();
        AquaCreeperSounds.init();
        AquaBlocks.init();
        AquaItems.init();

        /*
            Add to biomes in the "aqua_creeper_spawns" tag.
         */
        BiomeModifications.addSpawn(BiomeSelectors.tag(AquaTags.Biomes.AQUA_CREEPER_SPAWNS), SpawnGroup.MONSTER, AquaCreeperEntityTypes.AQUA_CREEPER, 5, 1, 1);
    }
}
