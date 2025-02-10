package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.VanillaRecipeProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

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

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, AquaItems.OCEAN_TORCH, 1).input('#', Items.STICK).input('X', AquaItems.OCEAN_SODIUM).pattern("X").pattern("#").criterion(hasItem(AquaItems.OCEAN_SODIUM), conditionsFromItem(AquaItems.OCEAN_SODIUM)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, AquaBlocks.OCEAN_LANTERN).input('#', AquaItems.OCEAN_TORCH).input('X', Items.IRON_NUGGET).pattern("XXX").pattern("X#X").pattern("XXX").criterion("has_iron_nugget", conditionsFromItem(Items.IRON_NUGGET)).criterion("has_iron_ingot", conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, AquaBlocks.OCEAN_CAMPFIRE).input('L', ItemTags.LOGS).input('S', Items.STICK).input('C', AquaItems.OCEAN_SODIUM).pattern(" S ").pattern("SCS").pattern("LLL").criterion("has_stick", conditionsFromItem(Items.STICK)).criterion(hasItem(AquaItems.OCEAN_SODIUM), conditionsFromItem(AquaItems.OCEAN_SODIUM)).offerTo(exporter);

    }
}
