package net.digitalpear.aqua_creepers.common.entities;

import net.digitalpear.aqua_creepers.common.world.ExplosionGenerator;
import net.digitalpear.aqua_creepers.init.AquaCreeperEntityTypes;
import net.minecraft.entity.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class UnderwaterTntEntity extends TntEntity {

    @Nullable
    private LivingEntity causingEntity;

    public UnderwaterTntEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
    }

    private UnderwaterTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(AquaCreeperEntityTypes.UNDERWATER_TNT, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.20000000298023224, -Math.cos(d) * 0.02);
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    public static UnderwaterTntEntity createUnderwaterTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        return new UnderwaterTntEntity(world, x, y, z, igniter);
    }


    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
        }

        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return this.causingEntity;
    }

    public void explode() {
        float f = 4.0F;
        ExplosionGenerator.createExplosion(getWorld(), this, this.getX(), this.getBodyY(0.0625), this.getZ(), f, World.ExplosionSourceType.TNT);
    }
}
