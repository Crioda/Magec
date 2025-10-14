package crit.magec.entity.client;

import crit.magec.Magec;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class DivinityTestModel extends EntityModel<DivinityTestRenderState  > {
    private final ModelPart bone;
    private final ModelPart bone2;
    public static final EntityModelLayer DIVINITYTEST = new EntityModelLayer(Identifier.of(Magec.MOD_ID, "divinitytest"), "main");
    public DivinityTestModel(ModelPart root) {
        super(root);
        this.bone = root.getChild("bone");
        this.bone2 = this.bone.getChild("bone2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 24.0F, 0.0F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -48.0F, -8.0F, 16.0F, 48.0F, 16.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
