package net.digitalpear.aqua_creepers.common.datagens.tags;

import net.digitalpear.aqua_creepers.init.AquaTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AquaItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public AquaItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture, new AquaBlockTagProvider(output, completableFuture));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(AquaTags.Items.AQUA_CREEPER_DROP_MUSIC_DISCS).forceAddTag(ItemTags.CREEPER_DROP_MUSIC_DISCS);
        getOrCreateTagBuilder(AquaTags.Items.KNIVES);
        getOrCreateTagBuilder(AquaTags.Items.GRANTS_UNDERWATER_COOKING_ADVANCEMENT).add(Items.COD, Items.SALMON);
    }
}
