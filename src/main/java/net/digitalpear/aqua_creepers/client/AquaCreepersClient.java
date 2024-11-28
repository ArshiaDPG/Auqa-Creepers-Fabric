package net.digitalpear.aqua_creepers.client;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.client.entity.UnderwaterTntMinecartEntityRenderer;
import net.digitalpear.aqua_creepers.client.entity.aqua_creeper.AquaCreeperEntityModel;
import net.digitalpear.aqua_creepers.client.entity.aqua_creeper.AquaCreeperEntityRenderer;
import net.digitalpear.aqua_creepers.client.entity.UnderwaterTntEntityRenderer;
import net.digitalpear.aqua_creepers.init.AquaCreeperEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.MinecartEntityModel;

@Environment(EnvType.CLIENT)
public class AquaCreepersClient implements ClientModInitializer {
    
    public static final EntityModelLayer AQUA_CREEPER = new EntityModelLayer(AquaCreepers.id("aqua_creeper"), "main");
    public static final EntityModelLayer AQUA_CREEPER_ARMOR = new EntityModelLayer(AquaCreepers.id("aqua_creeper"), "armor");
    public static final EntityModelLayer UNDERWATER_TNT_MINECART = new EntityModelLayer(AquaCreepers.id("underwater_tnt_minecart"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(AquaCreeperEntityTypes.AQUA_CREEPER, AquaCreeperEntityRenderer::new);
        EntityRendererRegistry.register(AquaCreeperEntityTypes.UNDERWATER_TNT, UnderwaterTntEntityRenderer::new);
        EntityRendererRegistry.register(AquaCreeperEntityTypes.UNDERWATER_TNT_MINECART, UnderwaterTntMinecartEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(AQUA_CREEPER, AquaCreeperEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(AQUA_CREEPER_ARMOR, AquaCreeperEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(UNDERWATER_TNT_MINECART, MinecartEntityModel::getTexturedModelData);
    }
}
