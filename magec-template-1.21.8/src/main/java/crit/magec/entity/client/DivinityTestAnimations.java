package crit.magec.entity.client;

import net.minecraft.client.render.entity.animation.*;

public class DivinityTestAnimations {
    public static final AnimationDefinition ANIM_DIVINITYTEST_IDLE =  AnimationDefinition.Builder.create(2f).looping()
            .addBoneAnimation("bone2", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(-1.0F, 30.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5833F, AnimationHelper.createTranslationalVector(-1.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("bone2", new Transformation(Transformation.Targets.SCALE,
                    new Keyframe(0.0F, AnimationHelper.createScalingVector(1.0F, 1.0F, 1.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();
}
