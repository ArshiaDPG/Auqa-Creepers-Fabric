package net.digitalpear.aqua_creepers.init.data;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.World;
import net.superkat.explosiveenhancement.api.ExplosiveApi;

public class ModCompat {
    public static boolean isEELoaded(){
        return FabricLoader.getInstance().isModLoaded("explosiveenhancement");
    }
    public static boolean isFDLoaded(){
        return FabricLoader.getInstance().isModLoaded("farmersdelight");
    }
    public static void spawnParticles(World world, double x, double y, double z, float power, boolean isUnderwater, boolean didDestroyBlocks) {
        ExplosiveApi.spawnParticles(world, x, y, z, power, isUnderwater, didDestroyBlocks);
    }
}