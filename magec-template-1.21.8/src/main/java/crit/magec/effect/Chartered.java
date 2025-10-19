package crit.magec.effect;

import crit.magec.Magec;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;

public class Chartered extends StatusEffect {
    public Chartered() {
        super(StatusEffectCategory.HARMFUL, 0);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        for (int i = 0; i < 100; i++) {
            double angle = 2 * Math.PI * i / 100;

            double posX = x + 5 * Math.cos(angle);
            double posY = y;
            double posZ = z + 5 * Math.sin(angle);

            world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, posX ,posY ,posZ, 1, 0, 0, 0, 0);

        }
        for (int i = 0; i < 25; i++) {
            double x2 = entity.getX() - 2.5;
            double z2 = entity.getZ() - 2.5;
            double angle = (2 * Math.PI * i / 25);
            double posX = x + 2.5 * Math.cos(angle);
            double posZ = z + 2.5 * Math.sin(angle);
            double posX2 = x2 + 2.5 * Math.cos(angle);
            double posZ2 = z2 + 2.5 * Math.cos(angle);
            world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, posX ,y ,posZ, 1, 0, 0, 0, 0);
            world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, posX ,y ,posZ2, 1, 0, 0, 0, 0);
            world.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, -posX2 ,y ,-posZ2, 1, 0, 0, 0, 0);

        }

        entity.addStatusEffect(new StatusEffectInstance(Magec.CHARTERED));
        return super.applyUpdateEffect(world, entity, amplifier);
    }

}
