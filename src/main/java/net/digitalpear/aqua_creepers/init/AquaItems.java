package net.digitalpear.aqua_creepers.init;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;

public class AquaItems {

    public static Item createItem(String name, Item item){
        return Registry.register(Registries.ITEM, AquaCreepers.id(name), item);
    }
    public static final Item AQUA_CREEPER_BUCKET = createItem("aqua_creeper_bucket", new EntityBucketItem(AquaCreeperEntityTypes.AQUA_CREEPER, Fluids.WATER,
            SoundEvents.ITEM_BUCKET_EMPTY_FISH,
            new Item.Settings().maxCount(1)));

    public static final Item AQUA_CREEPER_SPAWN_EGG = createItem("aqua_creeper_spawn_egg", new SpawnEggItem(AquaCreeperEntityTypes.AQUA_CREEPER,
            7329243, 0,
            new Item.Settings()));

    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> entries.addAfter(Items.ALLAY_SPAWN_EGG, AQUA_CREEPER_SPAWN_EGG));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.addAfter(Items.TADPOLE_BUCKET, AQUA_CREEPER_BUCKET));
    }
}
