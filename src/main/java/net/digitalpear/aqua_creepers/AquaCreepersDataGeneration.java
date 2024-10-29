package net.digitalpear.aqua_creepers;

import net.digitalpear.aqua_creepers.common.datagens.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AquaCreepersDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(AquaBlockTagProvider::new);
        pack.addProvider(AquaItemTagProvider::new);
        pack.addProvider(AquaBiomeTagProvider::new);

        pack.addProvider(AquaLanguageGen::new);
        pack.addProvider(AquaModelGen::new);
        pack.addProvider(AquaMobLootTableGen::new);
    }
}
