package net.digitalpear.aqua_creepers.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class UnderwaterLanternBlock extends LanternBlock {
    public static final BooleanProperty LIT = Properties.LIT;

    public UnderwaterLanternBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, true));
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIT);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
        if (newState.isOf(this)){
            world.setBlockState(pos, newState.with(LIT, newState.get(WATERLOGGED)));
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state == null){
            return null;
        }
        return state.with(LIT, ctx.getWorld().isWater(ctx.getBlockPos()));
    }
}
