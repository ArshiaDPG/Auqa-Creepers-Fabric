package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.DynamicEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyNameLootFunction;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.function.SetContentsLootFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class AquaBlockLootTableProvider extends FabricBlockLootTableProvider {
    public AquaBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(AquaBlocks.UNDERWATER_TNT);
    }
}
