package net.digitalpear.aqua_creepers.common.datagens.tags;

import net.digitalpear.aqua_creepers.init.AquaTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class AquaEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public AquaEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(AquaTags.EntityTypes.DISC_KILLERS).forceAddTag(EntityTypeTags.SKELETONS).add(EntityType.DROWNED);
    }
}
