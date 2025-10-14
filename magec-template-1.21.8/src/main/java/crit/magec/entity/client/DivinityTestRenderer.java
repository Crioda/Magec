package crit.magec.entity.client;

import crit.magec.Magec;
import crit.magec.entity.DivinityTest;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DivinityTestRenderer extends MobEntityRenderer<DivinityTest, DivinityTestRenderState, DivinityTestModel> {

    public DivinityTestRenderer(EntityRendererFactory.Context context) {
        super(context, new DivinityTestModel(context.getPart(DivinityTestModel.DIVINITYTEST)), 0.75f);
    }

    @Override
    public Identifier getTexture(DivinityTestRenderState state) {
        return Identifier.of(Magec.MOD_ID, "textures/entity/divinitytest.png");
    }

    @Override
    public void render(DivinityTestRenderState livingEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntityRenderState, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public DivinityTestRenderState createRenderState() {
        return new DivinityTestRenderState();
    }

    @Override
    public void updateRenderState(DivinityTest livingEntity, DivinityTestRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
    }

}
