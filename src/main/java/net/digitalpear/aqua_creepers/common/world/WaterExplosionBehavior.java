package net.digitalpear.aqua_creepers.common.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Optional;

public class WaterExplosionBehavior extends ExplosionBehavior {
    @Override
    public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
        if (fluidState.isIn(FluidTags.WATER) || blockState.isOf(Blocks.WATER)){
            return blockState.isAir() ? Optional.empty() : Optional.of(Math.max(Fluids.EMPTY.getDefaultState().getBlastResistance(), blockState.getBlock().getBlastResistance()));
        }
        return super.getBlastResistance(explosion, world, pos, blockState, fluidState);
    }
}
