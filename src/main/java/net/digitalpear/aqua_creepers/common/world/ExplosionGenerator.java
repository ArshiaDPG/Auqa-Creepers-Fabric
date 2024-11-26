package net.digitalpear.aqua_creepers.common.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

public class ExplosionGenerator {
    public static CustomExplosion createExplosion(World world, @Nullable Entity entity, double x, double y, double z, float power, World.ExplosionSourceType explosionSourceType) {
        return ExplosionGenerator.createExplosion(world, entity, null, null, x, y, z, power, false, explosionSourceType);
    }

    public static CustomExplosion createExplosion(World world, @Nullable Entity entity, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType) {
        return ExplosionGenerator.createExplosion(world, entity, null, null, x, y, z, power, createFire, explosionSourceType);
    }

    public static CustomExplosion createExplosion(World world, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, Vec3d pos, float power, boolean createFire, World.ExplosionSourceType explosionSourceType) {
        return ExplosionGenerator.createExplosion(world, entity, damageSource, behavior, pos.getX(), pos.getY(), pos.getZ(), power, createFire, explosionSourceType);
    }

    public static CustomExplosion createExplosion(World world, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType) {
        return ExplosionGenerator.createExplosion(world, entity, damageSource, behavior, x, y, z, power, createFire, explosionSourceType, true);
    }

    public static CustomExplosion createExplosion(World world, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior behavior, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType, boolean particles) {
        CustomExplosion.DestructionType destructionType = switch (explosionSourceType) {
            case NONE -> CustomExplosion.DestructionType.KEEP;
            case BLOCK -> ExplosionGenerator.getDestructionType(world, GameRules.BLOCK_EXPLOSION_DROP_DECAY);
            case MOB ->
                    world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) ? ExplosionGenerator.getDestructionType(world, GameRules.MOB_EXPLOSION_DROP_DECAY) : Explosion.DestructionType.KEEP;
            case TNT -> ExplosionGenerator.getDestructionType(world, GameRules.TNT_EXPLOSION_DROP_DECAY);
        };

        CustomExplosion explosion = new CustomExplosion(world, entity, damageSource, behavior, x, y, z, power, createFire, destructionType);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(particles);
        return explosion;
    }


    private static Explosion.DestructionType getDestructionType(World world, GameRules.Key<GameRules.BooleanRule> gameRuleKey) {
        return world.getGameRules().getBoolean(gameRuleKey) ? Explosion.DestructionType.DESTROY_WITH_DECAY : Explosion.DestructionType.DESTROY;
    }
}
