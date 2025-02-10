package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.common.blocks.*;
import net.fabricmc.fabric.mixin.lookup.BlockEntityTypeAccessor;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class AquaBlocks {

    public static Block createBlock(String name, Block block){
        Registry.register(Registries.ITEM, AquaCreepers.id(name), new BlockItem(block, new Item.Settings()));
        return createBlockWithoutItem(name, block);
    }

    public static Block createBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, AquaCreepers.id(name), block);
    }

    public static final Block UNDERWATER_TNT = createBlock("underwater_tnt", new UnderwaterTntBlock(AbstractBlock.Settings.copy(Blocks.TNT).mapColor(MapColor.LIGHT_BLUE)));

    public static final Block OCEAN_TORCH = createBlockWithoutItem("ocean_torch", new UnderwaterTorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).luminance(state -> state.get(UnderwaterTorchBlock.LIT) ? 14 : 0), ParticleTypes.BUBBLE));
    public static final Block OCEAN_WALL_TORCH = createBlockWithoutItem("ocean_wall_torch", new UnderwaterWallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH).dropsLike(OCEAN_TORCH).luminance(state -> state.get(UnderwaterWallTorchBlock.LIT) ? 14 : 0), ParticleTypes.BUBBLE));
    public static final Block OCEAN_CAMPFIRE = createBlock("ocean_campfire", new UnderwaterCampfireBlock(true, 1, AbstractBlock.Settings.copy(Blocks.CAMPFIRE)));
    public static final Block OCEAN_LANTERN = createBlock("ocean_lantern", new UnderwaterLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).luminance(state -> state.get(UnderwaterTorchBlock.LIT) ? 15 : 0)));

    public static void init() {
    }
}
