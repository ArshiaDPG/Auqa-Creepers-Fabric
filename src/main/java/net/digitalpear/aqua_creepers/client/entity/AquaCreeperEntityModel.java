package net.digitalpear.aqua_creepers.client.entity;

import net.digitalpear.aqua_creepers.common.entities.AquaCreeperEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModelWithChildTransform;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;


@Environment(EnvType.CLIENT)
public class AquaCreeperEntityModel<T extends AquaCreeperEntity> extends SinglePartEntityModelWithChildTransform<T> {

    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart rightFin;
    private final ModelPart leftFin;

    public AquaCreeperEntityModel(ModelPart root) {
        super(0.5f, 0.5f);
        this.body = root.getChild("body");
        this.tail = body.getChild("tail");
        this.rightFin = body.getChild("right_fin");
        this.leftFin = body.getChild("left_fin");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 9.0F)
                .uv(14, 21).cuboid(0.0F, -13.0F, -2.0F, 0.0F, 5.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        ModelPartData body_r1 = body.addChild("body_r1", ModelPartBuilder.create().uv(24, 17).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));
        ModelPartData body_r2 = body.addChild("body_r2", ModelPartBuilder.create().uv(24, 20).cuboid(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));
        ModelPartData rightFin = body.addChild("right_fin", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -2.0F, -2.0F, 0.0F, -0.3054F, 0.0F));
        ModelPartData leftFin = body.addChild("left_fin", ModelPartBuilder.create().uv(20, 13).cuboid(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -2.0F, -2.0F, 0.0F, 0.3054F, 0.0F));
        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 21).cuboid(0.0F, -4.0F, 0.0F, 0.0F, 9.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 6.0F));
        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 17).cuboid(-4.0F, -7.0F, -7.0F, 8.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(meshdefinition, 64, 64);
    }

    /*
        Play swimming animation (more intense if outside water).
     */
    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float intensityMultiplier = 1.0F;
        float widthMultiplier = 1.0F;


        float tailRotationMultiplier = 0.25F;
        float finRotationMultiplier = 0.25F;
        float bodyRotationMultiplier = 0.1F;

        if (!entity.isTouchingWater()) {
            intensityMultiplier = 1.3F;
            widthMultiplier = 1.7F;
            bodyRotationMultiplier *= 2F;
            tailRotationMultiplier *= 3;
            //this.body.roll = 0.5F;
        }

        float finRotation = Math.abs(intensityMultiplier * finRotationMultiplier * MathHelper.sin(widthMultiplier * 0.6F * animationProgress));
        this.leftFin.yaw = finRotation;
        this.rightFin.yaw = -finRotation;

        this.tail.yaw = -intensityMultiplier * tailRotationMultiplier * MathHelper.sin(widthMultiplier * 0.6F * animationProgress);
        this.body.yaw = -intensityMultiplier * bodyRotationMultiplier * MathHelper.sin(widthMultiplier * 0.6F * animationProgress);

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay);
    }

    @Override
    public ModelPart getPart() {
        return this.body;
    }
}