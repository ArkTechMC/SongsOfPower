package com.iafenvoy.sop.render;

import com.iafenvoy.sop.entity.AggroDetonateEntity;
import com.iafenvoy.sop.render.model.DetonateModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class AggroDetonateRenderer extends EntityRenderer<AggroDetonateEntity> {
    private final DetonateModel<AggroDetonateEntity> detonateModel;

    public AggroDetonateRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.detonateModel = new DetonateModel<>(DetonateModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(AggroDetonateEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.scale(2, 2, 2);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch())));
        matrices.translate(0, -1.375, 0);
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(RenderConstants.WHITE_TEXTURE));
        this.detonateModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, 1, 0, 0, 0.7f);
        matrices.pop();
    }

    @Override
    public Identifier getTexture(AggroDetonateEntity entity) {
        return RenderConstants.WHITE_TEXTURE;
    }
}
