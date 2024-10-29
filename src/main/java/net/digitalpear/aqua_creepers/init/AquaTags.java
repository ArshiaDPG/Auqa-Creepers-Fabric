package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class AquaTags {
    public static <T> TagKey<T> tag(String name, RegistryKey<? extends Registry<T>> key){
        return TagKey.of(key, AquaCreepers.id(name));
    }
    public static class Blocks{
        private static TagKey<Block> tag(String name){
            return AquaTags.tag(name, RegistryKeys.BLOCK);
        }
//        public static final TagKey<Block> IGNORABLE_FLUIDS = tag("ignorable_fluids");
    }
    public static class Items{
        private static TagKey<Item> tag(String name){
            return AquaTags.tag(name, RegistryKeys.ITEM);
        }

        public static final TagKey<Item> AQUA_CREEPER_DROP_MUSIC_DISCS = tag("aqua_creeper_drop_music_discs");
    }
    public static class Biomes{
        private static TagKey<Biome> tag(String name){
            return AquaTags.tag(name, RegistryKeys.BIOME);
        }
        public static final TagKey<Biome> AQUA_CREEPER_SPAWNS = tag("aqua_creeper_spawns");

    }
}
