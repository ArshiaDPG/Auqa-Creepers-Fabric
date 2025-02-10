package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.init.AquaBlocks;
import net.digitalpear.aqua_creepers.init.AquaCreeperEntityTypes;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.advancement.Advancement;

public class AquaLanguageProvider extends FabricLanguageProvider {
    public AquaLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(AquaCreeperEntityTypes.AQUA_CREEPER, "Aqua Creeper");

        translationBuilder.add(AquaItems.AQUA_CREEPER, "Aqua Creeper");
        translationBuilder.add(AquaItems.AQUA_CREEPER_BUCKET, "Bucket of Aqua Creeper");
        translationBuilder.add(AquaItems.AQUA_CREEPER_SPAWN_EGG, "Aqua Creeper Spawn Egg");

        translationBuilder.add(AquaBlocks.UNDERWATER_TNT, "Underwater TNT");
        translationBuilder.add(AquaCreeperEntityTypes.UNDERWATER_TNT_MINECART, "Underwater TNT Minecart");
        translationBuilder.add(AquaItems.UNDERWATER_TNT_MINECART, "Underwater TNT Minecart");

        translationBuilder.add(AquaItems.OCEAN_SODIUM, "Ocean Sodium");

        translationBuilder.add("subtitles.aqua_creepers.aqua_creeper_hurt", "Aqua Creeper Hurts");
        translationBuilder.add("subtitles.aqua_creepers.aqua_creeper_swim", "Aqua Creeper Swims");
        translationBuilder.add("subtitles.aqua_creepers.underwater_explosion", "Underwater Explosion");
        translationBuilder.add("subtitles.aqua_creepers.underwater_fuse", "Aqua Creeper Hisses");

        translationBuilder.add(AquaBlocks.OCEAN_TORCH, "Ocean Torch");
        translationBuilder.add(AquaBlocks.OCEAN_CAMPFIRE, "Ocean Campfire");
        translationBuilder.add(AquaBlocks.OCEAN_LANTERN, "Ocean Lantern");

        advancementTranslation(translationBuilder, AquaAdvancementProvider.SOUS_VIDE, "Sous Vide", "Cook fish using a lit Sodium Campfire.");
//        advancementTranslation(translationBuilder, AquaAdvancementProvider.GRIEF_PROTECTION, "Grief Protection", "Kill every type of creeper.");
    }

    public void advancementTranslation(TranslationBuilder builder, Advancement advancement, String title, String desc){
        builder.add(advancement.getDisplay().getTitle().getString(), title);
        builder.add(advancement.getDisplay().getDescription().getString(), desc);
    }
}
