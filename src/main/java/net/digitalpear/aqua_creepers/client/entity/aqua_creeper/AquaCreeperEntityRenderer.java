package net.digitalpear.aqua_creepers.client.entity.aqua_creeper;

import net.digitalpear.aqua_creepers.AquaCreepers;
import net.digitalpear.aqua_creepers.client.AquaCreepersClient;
import net.digitalpear.aqua_creepers.common.entities.AquaCreeperEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;


@Environment(EnvType.CLIENT)
public class AquaCreeperEntityRenderer extends MobEntityRenderer<AquaCreeperEntity, AquaCreeperEntityModel<AquaCreeperEntity>> {

    private static final Identifier TEXTURE = AquaCreepers.id("textures/entity/aqua_creeper.png");
    private static final Identifier CHARGED_TEXTURE = AquaCreepers.id("textures/entity/aqua_creeper_charged.png");

    public AquaCreeperEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AquaCreeperEntityModel<>(context.getPart(AquaCreepersClient.AQUA_CREEPER)), 0.5f);
        this.addFeature(new AquaCreeperChargeFeatureRenderer(this, context.getModelLoader()));

    }
    protected void setupTransforms(AquaCreeperEntity aquaCreeperEntity, MatrixStack matrixStack, float f, float g, float h) {
        super.setupTransforms(aquaCreeperEntity, matrixStack, f, g, h);

        /*
            Centers the model.
         */
        matrixStack.translate(0, 0, 0.4);

        /*
            Makes the fush flop around if outside of water.
         */
        float i = 1.0F;
        float j = 1.0F;
        if (!aquaCreeperEntity.isTouchingWater()) {
            i = 1.3F;
            j = 1.7F;
        }

        float k = i * 4.3F * MathHelper.sin(j * 0.6F * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(k));
        matrixStack.translate(0.0F, 0.0F, -0.4F);
        if (!aquaCreeperEntity.isTouchingWater()) {
            matrixStack.translate(0.2F, 0.1F, 0.0F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90.0F));
        }

        /*
            Aqua Creepers jitter when charged.
         */
        Random random = aquaCreeperEntity.getRandom();
        if (aquaCreeperEntity.isCharged() && !aquaCreeperEntity.isIgnited() && aquaCreeperEntity.age % random.nextBetween(40, 60) == 0 && random.nextBoolean()){
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextBetween(-90, 90)));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextBetween(-90, 90)));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextBetween(-90, 90)));
        }
    }
    /*
        Play animation for explosion.
     */
    protected void scale(AquaCreeperEntity aquaCreeperEntity, MatrixStack matrixStack, float f) {
        float g = aquaCreeperEntity.getClientFuseTime(f);
        float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
        g = MathHelper.clamp(g, 0.0F, 1.0F);
        g *= g;
        g *= g;
        float i = (1.0F + g * 0.4F) * h;
        float j = (1.0F + g * 0.1F) / h;
        matrixStack.scale(i, j, i);
    }
    protected float getAnimationCounter(AquaCreeperEntity aquaCreeperEntity, float f) {
        float g = aquaCreeperEntity.getClientFuseTime(f);
        return (int)(g * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(g, 0.5F, 1.0F);
    }

    @Override
    public Identifier getTexture(AquaCreeperEntity entity) {
        return entity.isCharged() ? CHARGED_TEXTURE : TEXTURE;
    }
}
