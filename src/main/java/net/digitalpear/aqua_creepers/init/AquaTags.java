package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class AquaTags {
    private static <T> TagKey<T> tag(String name, RegistryKey<? extends Registry<T>> key){
        return tag(AquaCreepers.id(name), key);
    }
    private static <T> TagKey<T> tag(Identifier name, RegistryKey<? extends Registry<T>> key){
        return TagKey.of(key, name);
    }

    public static class Blocks{
        private static TagKey<Block> tag(String name){
            return AquaTags.tag(name, RegistryKeys.BLOCK);
        }

        public static final TagKey<Block> WATER_CAMPFIRE_SIGNAL_BOOSTERS = tag("water_campfire_signal_boosters");

    }

    public static class EntityTypes {
        private static TagKey<EntityType<?>> tag(String name){
            return AquaTags.tag(name, RegistryKeys.ENTITY_TYPE);
        }
        public static final TagKey<EntityType<?>> DISC_KILLERS = tag("disc_killers");
    }

    public static class Items{
        private static TagKey<Item> tag(Identifier name){
            return AquaTags.tag(name, RegistryKeys.ITEM);
        }
        private static TagKey<Item> tag(String name){
            return AquaTags.tag(name, RegistryKeys.ITEM);
        }

        public static final TagKey<Item> AQUA_CREEPER_DROP_MUSIC_DISCS = tag("aqua_creeper_drop_music_discs");
        public static final TagKey<Item> GRANTS_UNDERWATER_COOKING_ADVANCEMENT = tag("grants_underwater_cooking_advancement");
        public static final TagKey<Item> KNIVES = tag(Identifier.of("c", "tools/knives"));
    }

    public static class Biomes{
        private static TagKey<Biome> tag(Identifier name){
            return AquaTags.tag(name, RegistryKeys.BIOME);
        }
        private static TagKey<Biome> tag(String name){
            return AquaTags.tag(name, RegistryKeys.BIOME);
        }
        public static final TagKey<Biome> AQUA_CREEPER_SPAWNS = tag("aqua_creeper_spawns");
    }
}
