package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

import static net.minecraft.data.client.TextureMap.getSubId;

public class AquaModelProvider extends FabricModelProvider {
    public static final Model TURTLE_TOTE = block("base_turtle_tote", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(AquaCreepers.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }
    public AquaModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerParentedItemModel(AquaItems.AQUA_CREEPER_SPAWN_EGG, new Identifier("item/template_spawn_egg"));
        blockStateModelGenerator.registerSingleton(AquaBlocks.UNDERWATER_TNT, TexturedModel.CUBE_BOTTOM_TOP);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(AquaItems.AQUA_CREEPER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(AquaItems.UNDERWATER_TNT_MINECART, Models.GENERATED);
        itemModelGenerator.register(AquaItems.OCEAN_SODIUM, Models.GENERATED);
        itemModelGenerator.register(AquaItems.AQUA_CREEPER, Models.GENERATED);
    }

    public static BlockStateVariantMap createDownDefaultRotationStates() {
        return BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.DOWN, BlockStateVariant.create())
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R270))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270));
    }
}
