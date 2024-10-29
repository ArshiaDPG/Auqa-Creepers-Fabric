package net.digitalpear.aqua_creepers.client;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.client.entity.AquaCreeperEntityModel;
import net.digitalpear.aqua_creepers.client.entity.AquaCreeperEntityRenderer;
import net.digitalpear.aqua_creepers.init.AquaCreeperEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class AquaCreepersClient implements ClientModInitializer {
    public static final EntityModelLayer AQUA_CREEPER = new EntityModelLayer(AquaCreepers.id("aqua_creeper"), "main");
    public static final EntityModelLayer AQUA_CREEPER_ARMOR = new EntityModelLayer(AquaCreepers.id("aqua_creeper"), "armor");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(AquaCreeperEntityTypes.AQUA_CREEPER, AquaCreeperEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(AQUA_CREEPER, AquaCreeperEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(AQUA_CREEPER_ARMOR, AquaCreeperEntityModel::getTexturedModelData);
    }
}
