package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaCreeperEntityTypes;
import net.digitalpear.aqua_creepers.init.AquaTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.block.CampfireBlock;
import net.minecraft.data.server.advancement.AdvancementTabGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

import java.util.List;
import java.util.function.Consumer;

public class AquaAdvancementProvider extends FabricAdvancementProvider {

    public static final Advancement SOUS_VIDE = Advancement.Builder.create()
            .display(new ItemStack(AquaBlocks.OCEAN_CAMPFIRE), Text.translatable("advancement.husbandry.sous_vide.title"), Text.translatable("advancement.husbandry.sous_vide.desc"), null, AdvancementFrame.TASK, true, true, false)
            .criterion("cook_fish_on_ocean_campfire", ItemCriterion.Conditions.createItemUsedOnBlock(LocationPredicate.Builder.create().fluid(FluidPredicate.Builder.create().fluid(Fluids.WATER).build()).block(BlockPredicate.Builder.create().blocks(AquaBlocks.OCEAN_CAMPFIRE).state(StatePredicate.Builder.create().exactMatch(CampfireBlock.LIT, true).exactMatch(CampfireBlock.WATERLOGGED, true).build()).build()), ItemPredicate.Builder.create().tag(AquaTags.Items.GRANTS_UNDERWATER_COOKING_ADVANCEMENT)))
            .parent(AdvancementTabGenerator.createEmptyAdvancement("husbandry/root"))
            .build(AquaCreepers.id("husbandry/sous_vide"));

//    public static final Advancement GRIEF_PROTECTION = requireListedMobsKilled()
//            .display(new ItemStack(Items.CREEPER_HEAD), Text.translatable("advancement.adventure.grief_protection.title"), Text.translatable("advancement.adventure.grief_protection.desc"), null, AdvancementFrame.TASK, true, true, false)
//            .parent(AdvancementTabGenerator.createEmptyAdvancement("adventure/kill_a_mob"))
//            .build(AquaCreepers.id("adventure/grief_protection"));

    private static Advancement.Builder requireListedMobsKilled() {
        Advancement.Builder builder = Advancement.Builder.create();
        for (EntityType<?> entityType : List.of(EntityType.CREEPER, AquaCreeperEntityTypes.AQUA_CREEPER)) {
            builder.criterion(Registries.ENTITY_TYPE.getId(entityType).toString(), net.minecraft.advancement.criterion.OnKilledCriterion.Conditions.createPlayerKilledEntity(EntityPredicate.Builder.create().type(entityType)));
        }

        return builder;
    }

    public AquaAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        consumer.accept(SOUS_VIDE);
//        consumer.accept(GRIEF_PROTECTION);
    }
}
