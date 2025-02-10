package net.digitalpear.aqua_creepers;

import net.digitalpear.aqua_creepers.common.datagens.*;
import net.digitalpear.aqua_creepers.common.datagens.tags.AquaBiomeTagProvider;
import net.digitalpear.aqua_creepers.common.datagens.tags.AquaBlockTagProvider;
import net.digitalpear.aqua_creepers.common.datagens.tags.AquaEntityTypeTagProvider;
import net.digitalpear.aqua_creepers.common.datagens.tags.AquaItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AquaCreepersDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(AquaEntityTypeTagProvider::new);
        pack.addProvider(AquaItemTagProvider::new);
        pack.addProvider(AquaBiomeTagProvider::new);
        pack.addProvider(AquaBlockTagProvider::new);

        pack.addProvider(AquaLanguageProvider::new);
        pack.addProvider(AquaModelProvider::new);
        pack.addProvider(AquaRecipeProvider::new);

        pack.addProvider(AquaEntityLootTableProvider::new);
        pack.addProvider(AquaBlockLootTableProvider::new);

        pack.addProvider(AquaAdvancementProvider::new);
    }
}
