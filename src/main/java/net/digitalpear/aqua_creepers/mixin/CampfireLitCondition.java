package net.digitalpear.aqua_creepers.mixin;

import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class CampfireLitCondition {

    @Inject(method = "canBeLit", cancellable = true, at = @At("RETURN"))
    private static void changeConditions(BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (state.isOf(AquaBlocks.OCEAN_CAMPFIRE)){
            cir.setReturnValue(state.isIn(BlockTags.CAMPFIRES, (statex) ->
                    statex.contains(CampfireBlock.WATERLOGGED) && statex.contains(CampfireBlock.LIT))
                    && state.get(CampfireBlock.WATERLOGGED)
                    && !(Boolean)state.get(CampfireBlock.LIT));
        }
    }
    @Inject(method = "isSignalFireBaseBlock", at = @At("RETURN"), cancellable = true)
    private void changeBaseBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (this.equals(AquaBlocks.OCEAN_CAMPFIRE)){
            cir.setReturnValue(state.isIn(AquaTags.Blocks.WATER_CAMPFIRE_SIGNAL_BOOSTERS));
        }
    }
}
