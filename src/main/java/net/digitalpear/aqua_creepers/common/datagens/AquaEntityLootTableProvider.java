package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.digitalpear.aqua_creepers.init.AquaTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.TagEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class AquaEntityLootTableProvider extends SimpleFabricLootTableProvider {
    public AquaEntityLootTableProvider(FabricDataOutput output) {
        super(output, LootContextTypes.ENTITY);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        biConsumer.accept(AquaCreepers.id("entities/aqua_creeper"),
                LootTable.builder()
                        /*
                            Defalt gunpowder drops
                        */
                        .pool(LootPool.builder()
                                .with(ItemEntry.builder(AquaItems.OCEAN_SODIUM))
                                .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_KILLER, EntityPredicate.Builder.create().equipment(EntityEquipmentPredicate.Builder.create().mainhand(ItemPredicate.Builder.create().tag(AquaTags.Items.KNIVES).build()).build())).invert())
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
                                .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))))

                        /*
                            FD Compat drops
                         */
                        .pool(LootPool.builder()
                                .with(ItemEntry.builder(AquaItems.AQUA_CREEPER))
                                .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.DIRECT_KILLER, EntityPredicate.Builder.create().equipment(EntityEquipmentPredicate.Builder.create().mainhand(ItemPredicate.Builder.create().tag(AquaTags.Items.KNIVES).build()).build()))))


                        /*
                            Music disc drops
                        */
                        .pool(LootPool.builder()
                                .with(TagEntry.expandBuilder(AquaTags.Items.AQUA_CREEPER_DROP_MUSIC_DISCS))
                                .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER,
                                EntityPredicate.Builder.create().type(AquaTags.EntityTypes.DISC_KILLERS))))
        );
    }
}
