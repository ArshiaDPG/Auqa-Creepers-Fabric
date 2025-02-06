package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.init.AquaTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class AquaItemTagProvider extends FabricTagProvider<Item> {

    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public AquaItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(AquaTags.Items.AQUA_CREEPER_DROP_MUSIC_DISCS).forceAddTag(ItemTags.CREEPER_DROP_MUSIC_DISCS);
        getOrCreateTagBuilder(AquaTags.Items.KNIVES);
    }
}
