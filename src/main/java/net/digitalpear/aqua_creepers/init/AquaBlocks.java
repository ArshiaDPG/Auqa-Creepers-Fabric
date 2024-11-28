package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.common.blocks.UnderwaterTntBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AquaBlocks {

    public static Block createBlock(String name, Block block){
        Registry.register(Registries.ITEM, AquaCreepers.id(name), new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, AquaCreepers.id(name), block);
    }

    public static final Block UNDERWATER_TNT = createBlock("underwater_tnt", new UnderwaterTntBlock(AbstractBlock.Settings.copy(Blocks.TNT).mapColor(MapColor.LIGHT_BLUE)));

    public static void init() {
    }
}
