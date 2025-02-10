package net.digitalpear.aqua_creepers.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class UnderwaterCampfireBlock extends CampfireBlock {
    public UnderwaterCampfireBlock(boolean emitsParticles, int fireDamage, Settings settings) {
        super(emitsParticles, fireDamage, settings);
    }
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        return super.tryFillWithFluid(world, pos, state, fluidState);
    }

    @Override
    public ItemStack tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        ItemStack itemStack = super.tryDrainFluid(world, pos, state);
        BlockState newState = world.getBlockState(pos);
        if (!newState.get(WATERLOGGED)){
            if (!world.isClient()) {
                world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            world.setBlockState(pos, newState.with(LIT, false), 3);

            extinguish(null, world, pos, newState);
        }
        return itemStack;
    }

    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        BlockPos blockPos = hit.getBlockPos();
        if (!world.isClient && projectile.isOnFire() && projectile.canModifyAt(world, blockPos) && !(Boolean)state.get(LIT) && state.get(WATERLOGGED)) {
            world.setBlockState(blockPos, state.with(Properties.LIT, true), 11);
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        boolean bl = worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER;
        return super.getPlacementState(ctx).with(LIT, bl);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        if (newState.isOf(this)){
            world.setBlockState(pos, newState.with(LIT, newState.get(WATERLOGGED)));
        }
    }
}
