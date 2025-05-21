package net.digitalpear.aqua_creepers.common.entities.custom;

import net.digitalpear.aqua_creepers.common.entities.UnderwaterTntMinecartEntity;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class CustomMinecartEntity extends AbstractMinecartEntity {
    protected CustomMinecartEntity(EntityType<?> entityType, World world) {
        super(entityType, world);
    }
    public static AbstractMinecartEntity create(World world, double x, double y, double z) {
        return UnderwaterTntMinecartEntity.createUnderwaterTntMinecartEntity(world, x, y, z);
    }

    @Override
    public ItemStack getPickBlockStack() {
        return AquaItems.UNDERWATER_TNT_MINECART.getDefaultStack();
    }
}
