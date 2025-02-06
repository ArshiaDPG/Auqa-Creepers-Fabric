package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class AquaRecipeProvider extends FabricRecipeProvider {
    public AquaRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, AquaItems.UNDERWATER_TNT_MINECART)
                .input(AquaBlocks.UNDERWATER_TNT).input(Items.MINECART).criterion("has_minecart", conditionsFromItem(Items.MINECART)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, AquaBlocks.UNDERWATER_TNT, 2)
                .input('#', Items.TNT)
                .input('X', AquaItems.OCEAN_SODIUM)
                .pattern("XXX")
                .pattern("X#X")
                .pattern("XXX")
                .criterion(hasItem(AquaItems.OCEAN_SODIUM), conditionsFromItem(AquaItems.OCEAN_SODIUM)).offerTo(exporter);

    }
}
