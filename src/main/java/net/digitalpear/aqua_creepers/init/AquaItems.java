package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.common.items.AquaCreeperItem;
import net.digitalpear.aqua_creepers.common.items.CustomMinecartItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;

public class AquaItems {

    public static Item createItem(String name, Item item){
        return Registry.register(Registries.ITEM, AquaCreepers.id(name), item);
    }

    public static final Item AQUA_CREEPER_BUCKET = createItem("aqua_creeper_bucket", new EntityBucketItem(AquaCreeperEntityTypes.AQUA_CREEPER, Fluids.WATER,
            SoundEvents.ITEM_BUCKET_EMPTY_FISH,
            new Item.Settings().maxCount(1)));

    public static final Item AQUA_CREEPER_SPAWN_EGG = createItem("aqua_creeper_spawn_egg", new SpawnEggItem(AquaCreeperEntityTypes.AQUA_CREEPER,
            7329243, 13365225,
            new Item.Settings()));

    public static final Item UNDERWATER_TNT_MINECART = createItem("underwater_tnt_minecart", new CustomMinecartItem(new Item.Settings().maxCount(1)));

    public static final Item OCEAN_SODIUM = createItem("ocean_sodium", new Item(new Item.Settings()));

    public static final Item AQUA_CREEPER = createItem("aqua_creeper", new AquaCreeperItem(new Item.Settings().food(FoodComponents.COD)));

    public static final Item OCEAN_TORCH = createItem("ocean_torch", new VerticallyAttachableBlockItem(AquaBlocks.OCEAN_TORCH, AquaBlocks.OCEAN_WALL_TORCH, new Item.Settings(), Direction.DOWN));
    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.TORCH, OCEAN_TORCH);
            entries.addAfter(Items.LANTERN, AquaBlocks.OCEAN_LANTERN);
            entries.addAfter(Items.CAMPFIRE, AquaBlocks.OCEAN_CAMPFIRE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.GUNPOWDER, OCEAN_SODIUM);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.addAfter(Items.TNT, AquaBlocks.UNDERWATER_TNT);
            entries.addAfter(Items.TNT_MINECART, UNDERWATER_TNT_MINECART);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.TNT, AquaBlocks.UNDERWATER_TNT);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.PUFFERFISH, AQUA_CREEPER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> entries.addAfter(Items.ALLAY_SPAWN_EGG, AQUA_CREEPER_SPAWN_EGG));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.addAfter(Items.TADPOLE_BUCKET, AQUA_CREEPER_BUCKET));
    }
}
