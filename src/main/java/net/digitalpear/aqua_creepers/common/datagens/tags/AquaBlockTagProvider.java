package net.digitalpear.aqua_creepers.common.datagens.tags;

import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class AquaBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public AquaBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.CAMPFIRES).add(AquaBlocks.OCEAN_CAMPFIRE);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(AquaBlocks.OCEAN_LANTERN);
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(AquaBlocks.OCEAN_CAMPFIRE);

        getOrCreateTagBuilder(AquaTags.Blocks.WATER_CAMPFIRE_SIGNAL_BOOSTERS).forceAddTag(BlockTags.CORAL_BLOCKS).add(Blocks.WET_SPONGE);
    }
}
