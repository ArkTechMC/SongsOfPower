package com.iafenvoy.sop.render.model;

import com.iafenvoy.sop.entity.AggroDetonateEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class DetonateModel<T extends AggroDetonateEntity> extends EntityModel<T> {
    private final ModelPart detonate;

    public DetonateModel(ModelPart root) {
        this.detonate = root.getChild("detonate");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("detonate", ModelPartBuilder.create().uv(0, 0).cuboid(-4F, -0.5F, -0.5F, 8F, 1F, 1F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.detonate.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
