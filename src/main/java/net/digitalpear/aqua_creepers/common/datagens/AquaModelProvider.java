package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

import static net.minecraft.data.client.TextureMap.getSubId;

public class AquaModelProvider extends FabricModelProvider {
    public AquaModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerParentedItemModel(AquaItems.AQUA_CREEPER_SPAWN_EGG, new Identifier("item/template_spawn_egg"));
        blockStateModelGenerator.registerSingleton(AquaBlocks.UNDERWATER_TNT, TexturedModel.CUBE_BOTTOM_TOP);

        registerToggleableTorch(blockStateModelGenerator, AquaBlocks.OCEAN_TORCH, AquaBlocks.OCEAN_WALL_TORCH, Properties.LIT);

        blockStateModelGenerator.registerCampfire(AquaBlocks.OCEAN_CAMPFIRE);
        blockStateModelGenerator.registerLantern(AquaBlocks.OCEAN_LANTERN);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(AquaItems.AQUA_CREEPER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(AquaItems.UNDERWATER_TNT_MINECART, Models.GENERATED);
        itemModelGenerator.register(AquaItems.OCEAN_SODIUM, Models.GENERATED);
        itemModelGenerator.register(AquaItems.AQUA_CREEPER, Models.GENERATED);
    }

    private void registerToggleableTorch(BlockStateModelGenerator blockStateModelGenerator, Block torch, Block wallTorch, BooleanProperty property) {
        TextureMap textureMap = TextureMap.torch(torch);
        TextureMap textureMap2 = TextureMap.torch(TextureMap.getSubId(torch, "_off"));
        Identifier identifier = Models.TEMPLATE_TORCH.upload(torch, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_TORCH.upload(torch, "_off", textureMap2, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(torch).coordinate(BlockStateModelGenerator.createBooleanModelMap(property, identifier, identifier2)));
        Identifier identifier3 = Models.TEMPLATE_TORCH_WALL.upload(wallTorch, textureMap, blockStateModelGenerator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_TORCH_WALL.upload(wallTorch, "_off", textureMap2, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(wallTorch).coordinate(BlockStateModelGenerator.createBooleanModelMap(property, identifier3, identifier4)).coordinate(BlockStateModelGenerator.createEastDefaultHorizontalRotationStates()));
        blockStateModelGenerator.registerItemModel(torch);
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallTorch);
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
