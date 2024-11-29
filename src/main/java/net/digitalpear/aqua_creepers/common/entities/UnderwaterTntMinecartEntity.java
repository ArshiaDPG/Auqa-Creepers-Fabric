package net.digitalpear.aqua_creepers.common.entities;

import net.digitalpear.aqua_creepers.common.world.ExplosionGenerator;
import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaCreeperEntityTypes;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.digitalpear.aqua_creepers.init.data.ExplosiveCompat;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class UnderwaterTntMinecartEntity extends TntMinecartEntity{
    public UnderwaterTntMinecartEntity(EntityType<? extends TntMinecartEntity> entityType, World world) {
        super(entityType, world);
        this.setCustomBlock(AquaBlocks.UNDERWATER_TNT.getDefaultState());
    }
    private UnderwaterTntMinecartEntity(World world, double x, double y, double z) {
        this(AquaCreeperEntityTypes.UNDERWATER_TNT_MINECART, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    public ItemStack getPickBlockStack() {
        return AquaItems.UNDERWATER_TNT_MINECART.getDefaultStack();
    }
    public static UnderwaterTntMinecartEntity createUnderwaterTntMinecartEntity(World world, double x, double y, double z) {
        return new UnderwaterTntMinecartEntity(world, x, y, z);
    }

    @Override
    protected Item getItem() {
        return super.getItem();
    }

    @Override
    protected void explode(@Nullable DamageSource damageSource, double power) {
        double d = Math.sqrt(power);
        if (d > 5.0) {
            d = 5.0;
        }
        float explosionPower = (float)(4.0 + this.random.nextDouble() * 1.5 * d);
        ExplosionGenerator.createExplosion(getWorld(), this, this.getX(), this.getY(), this.getZ(), explosionPower, World.ExplosionSourceType.TNT);
        if (!this.getWorld().isClient) {
            this.discard();
        }
    }
}
