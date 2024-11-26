package net.digitalpear.aqua_creepers.common.datagens;

import net.digitalpear.aqua_creepers.init.AquaCreeperEntityTypes;
import net.digitalpear.aqua_creepers.init.AquaItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class AquaLanguageProvider extends FabricLanguageProvider {
    public AquaLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(AquaCreeperEntityTypes.AQUA_CREEPER, "Aqua Creeper");

        translationBuilder.add(AquaItems.AQUA_CREEPER_BUCKET, "Bucket of Aqua Creeper");
        translationBuilder.add(AquaItems.AQUA_CREEPER_SPAWN_EGG, "Aqua Creeper Spawn Egg");

        translationBuilder.add("subtitles.aqua_creepers.aqua_creeper_hurt", "Aqua Creeper Hurts");
        translationBuilder.add("subtitles.aqua_creepers.aqua_creeper_swim", "Aqua Creeper Swims");
        translationBuilder.add("subtitles.aqua_creepers.underwater_explosion", "Underwater CustomExplosion");
        translationBuilder.add("subtitles.aqua_creepers.underwater_fuse", "Aqua Creeper Hisses");
    }
}
