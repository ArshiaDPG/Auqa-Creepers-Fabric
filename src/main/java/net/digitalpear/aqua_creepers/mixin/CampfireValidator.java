package net.digitalpear.aqua_creepers.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(BlockEntityType.class)
public class CampfireValidator {

    @Shadow @Final private Set<Block> blocks;

    @Inject(method = "supports", at = @At("RETURN"), cancellable = true)
    private void changeSupport(BlockState state, CallbackInfoReturnable<Boolean> cir){
        if (state.isOf(AquaBlocks.OCEAN_CAMPFIRE) && blocks.contains(Blocks.CAMPFIRE)){
            cir.setReturnValue(true);
        }
    }

}
