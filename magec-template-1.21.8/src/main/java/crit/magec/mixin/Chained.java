package crit.magec.mixin;

import crit.magec.components.ModComponents;
import crit.magec.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.PlayerInput;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInput.class)
public abstract class Chained {




	@Inject(at = @At("HEAD"), method = "forward", cancellable = true)
	private void noMovingForward(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
	@Inject(at = @At("HEAD"), method = "backward", cancellable = true)
	private void noMovingBackward(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
	@Inject(at = @At("HEAD"), method = "left", cancellable = true)
	private void noMovingLeft(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
	@Inject(at = @At("HEAD"), method = "right", cancellable = true)
	private void noMovingRight(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
	@Inject(at = @At("HEAD"), method = "jump", cancellable = true)
	private void noMovingJump(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
	}
}