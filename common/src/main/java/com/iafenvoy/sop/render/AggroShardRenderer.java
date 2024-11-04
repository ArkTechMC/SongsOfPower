package com.iafenvoy.sop.render;

import com.iafenvoy.sop.entity.AggroShardEntity;
import com.iafenvoy.sop.render.model.ShardModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

@Environment(EnvType.CLIENT)
public class AggroShardRenderer extends EntityRenderer<AggroShardEntity> {
    private final ShardModel<AggroShardEntity> shardModel;

    public AggroShardRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.shardModel = new ShardModel<>(ShardModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(AggroShardEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch())));
        matrices.translate(-0.1, 0, 0);
        VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(this.getTexture(entity)));
        this.shardModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, 1, 0, 0, 0.5f);
        matrices.pop();
    }

    @Override
    public Identifier getTexture(AggroShardEntity entity) {
        return ShardModel.SHARD_TEXTURE_WHITE;
    }
}
