package net.digitalpear.aqua_creepers.common.blocks;

import net.digitalpear.aqua_creepers.common.world.ExplosionGenerator;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class UnderwaterCampfireBlock extends CampfireBlock {
    public UnderwaterCampfireBlock(boolean emitsParticles, int fireDamage, Settings settings) {
        super(emitsParticles, fireDamage, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, true).with(SIGNAL_FIRE, false).with(WATERLOGGED, true).with(FACING, Direction.NORTH));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(AquaItems.AQUA_CREEPER) && state.get(LIT) && blockEntity instanceof CampfireBlockEntity campfireBlockEntity){
            if (!campfireBlockEntity.getItemsBeingCooked().stream().filter(ItemStack::isEmpty).toList().isEmpty()){
                world.breakBlock(pos, false);
                if (!player.isCreative()){
                    stack.decrement(1);
                }
                player.swingHand(hand);
                ExplosionGenerator.createExplosion(world, player, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 4, World.ExplosionSourceType.BLOCK);
                return ActionResult.CONSUME;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
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
}
