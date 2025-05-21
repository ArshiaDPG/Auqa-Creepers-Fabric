package net.digitalpear.aqua_creepers.common.items;

import net.digitalpear.aqua_creepers.init.data.ModCompat;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureSet;

public class AquaCreeperItem extends Item {
    public AquaCreeperItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures) && ModCompat.isFDLoaded();
    }
}
