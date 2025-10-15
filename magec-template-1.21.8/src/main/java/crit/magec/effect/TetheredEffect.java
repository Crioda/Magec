package crit.magec.effect;

import crit.magec.Magec;
import crit.magec.mixin.Chained;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.Vec3d;

import java.util.Set;

public class TetheredEffect extends StatusEffect {
    public TetheredEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }
    private double x = 0;
    private double y = 0;
    private double z = 0;


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        x = entity.getX();
        y = entity.getY();
        z = entity.getZ();
        super.onApplied(entity, amplifier);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        //entity.teleport(x, y, z, false);
        //entity.setVelocity(new Vec3d(0,0,0));
        //entity.velocityModified = true;
        entity.setMovementSpeed(0);
        entity.addStatusEffect(new StatusEffectInstance(Magec.TETHERED_EFFECT));
        return super.applyUpdateEffect(world, entity, amplifier);
    }

}
