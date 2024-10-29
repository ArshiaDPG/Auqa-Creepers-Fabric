package net.digitalpear.aqua_creepers.mixin;


import net.digitalpear.aqua_creepers.init.AquaCreeperEntityTypes;
import net.digitalpear.aqua_creepers.init.AquaTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.explosion.EntityExplosionBehavior;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(EntityExplosionBehavior.class)
public class CustomExplosion {
    @Inject(method = "getBlastResistance", cancellable = true, at = @At("RETURN"))
    private void change(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState, CallbackInfoReturnable<Optional<Float>> cir){
        if (explosion.getCausingEntity().getType() == AquaCreeperEntityTypes.AQUA_CREEPER){
            cir.setReturnValue(Optional.of(Math.max(Fluids.EMPTY.getDefaultState().getBlastResistance(), blockState.getBlock().getBlastResistance())));
        }
    }

    @Inject(method = "canDestroyBlock", cancellable = true, at = @At("RETURN"))
    private void changeCanDestroy(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
