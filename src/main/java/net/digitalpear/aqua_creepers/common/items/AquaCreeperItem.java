package net.digitalpear.aqua_creepers.common.items;

import net.digitalpear.aqua_creepers.common.world.ExplosionGenerator;
import net.digitalpear.aqua_creepers.init.data.ModCompat;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.world.World;

public class AquaCreeperItem extends Item {
    public AquaCreeperItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures) && ModCompat.isFDLoaded();
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.getRandom().nextBoolean()){
            ExplosionGenerator.createExplosion(world, user, user.getX(), user.getY() + 0.5, user.getZ(), 1.0F, World.ExplosionSourceType.MOB);
        }
        return super.finishUsing(stack, world, user);
    }
}
