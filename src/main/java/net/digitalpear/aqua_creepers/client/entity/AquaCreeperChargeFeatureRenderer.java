package net.digitalpear.aqua_creepers.client.entity;

import net.digitalpear.aqua_creepers.client.AquaCreepersClient;
import net.digitalpear.aqua_creepers.common.entities.AquaCreeperEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class AquaCreeperChargeFeatureRenderer extends EnergySwirlOverlayFeatureRenderer<AquaCreeperEntity, AquaCreeperEntityModel<AquaCreeperEntity>> {
    private static final Identifier SKIN = new Identifier("textures/entity/creeper/creeper_armor.png");
    private final AquaCreeperEntityModel<AquaCreeperEntity> model;

    public AquaCreeperChargeFeatureRenderer(FeatureRendererContext<AquaCreeperEntity, AquaCreeperEntityModel<AquaCreeperEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new AquaCreeperEntityModel<>(loader.getModelPart(AquaCreepersClient.AQUA_CREEPER_ARMOR));
    }

    protected float getEnergySwirlX(float partialAge) {
        return partialAge * 0.01F;
    }

    protected Identifier getEnergySwirlTexture() {
        return SKIN;
    }

    protected EntityModel<AquaCreeperEntity> getEnergySwirlModel() {
        return this.model;
    }
}
