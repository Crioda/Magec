package crit.magec.mixin;

import crit.magec.item.ModItems;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.jar.Attributes;

@Mixin(LivingEntity.class)
public abstract class HammerTime {
// infinite mixin
	@Inject(at = @At("HEAD"), method = "applyArmorToDamage", cancellable = true)
	private void hammerTimeGaspers( DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {

		if (source.getAttacker() instanceof LivingEntity attacker && attacker.getMainHandStack().isOf(ModItems.HAMMER)) {
			cir.setReturnValue(amount);
		}
	}
}