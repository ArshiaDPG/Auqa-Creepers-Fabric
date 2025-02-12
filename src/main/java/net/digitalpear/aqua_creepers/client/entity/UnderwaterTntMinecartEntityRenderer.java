package net.digitalpear.aqua_creepers.client.entity;

import net.digitalpear.aqua_creepers.client.AquaCreepersClient;
import net.digitalpear.aqua_creepers.common.entities.UnderwaterTntMinecartEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
@Environment(EnvType.CLIENT)
public class UnderwaterTntMinecartEntityRenderer extends MinecartEntityRenderer<UnderwaterTntMinecartEntity> {
    private final BlockRenderManager tntBlockRenderManager;

    public UnderwaterTntMinecartEntityRenderer(EntityRendererFactory.Context context) {
        super(context, AquaCreepersClient.UNDERWATER_TNT_MINECART);
        this.tntBlockRenderManager = context.getBlockRenderManager();
    }

    protected void renderBlock(UnderwaterTntMinecartEntity tntMinecartEntity, float f, BlockState blockState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        int j = tntMinecartEntity.getFuseTicks();
        if (j > -1 && (float)j - f + 1.0F < 10.0F) {
            float g = 1.0F - ((float)j - f + 1.0F) / 10.0F;
            g = MathHelper.clamp(g, 0.0F, 1.0F);
            g *= g;
            g *= g;
            float h = 1.0F + g * 0.3F;
            matrixStack.scale(h, h, h);
        }

        renderFlashingBlock(this.tntBlockRenderManager, blockState, matrixStack, vertexConsumerProvider, i, j > -1 && j / 5 % 2 == 0);
    }

    public static void renderFlashingBlock(BlockRenderManager blockRenderManager, BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean drawFlash) {
        int i;
        if (drawFlash) {
            i = OverlayTexture.packUv(OverlayTexture.getU(1.0F), 10);
        } else {
            i = OverlayTexture.DEFAULT_UV;
        }

        blockRenderManager.renderBlockAsEntity(state, matrices, vertexConsumers, light, i);
    }
}
