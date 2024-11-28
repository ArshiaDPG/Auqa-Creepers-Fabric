package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.util.Identifier;

public class AquaModelProvider extends FabricModelProvider {
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
    }
}
