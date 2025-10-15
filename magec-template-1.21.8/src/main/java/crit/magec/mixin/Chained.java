package crit.magec.mixin;

import net.minecraft.util.PlayerInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInput.class)
public abstract class Chained {
	public boolean enabled = false;

	@Inject(at = @At("HEAD"), method = "forward", cancellable = true)
	private void noMovingForward(CallbackInfoReturnable<Boolean> cir) {
		if (enabled){
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "backward", cancellable = true)
	private void noMovingBackward(CallbackInfoReturnable<Boolean> cir) {
		if (enabled){
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "left", cancellable = true)
	private void noMovingLeft(CallbackInfoReturnable<Boolean> cir) {
		if (enabled){
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "right", cancellable = true)
	private void noMovingRight(CallbackInfoReturnable<Boolean> cir) {
		if (enabled){
			cir.setReturnValue(false);
		}
	}
	@Inject(at = @At("HEAD"), method = "jump", cancellable = true)
	private void noMovingJump(CallbackInfoReturnable<Boolean> cir) {
		if (enabled){
			cir.setReturnValue(false);
		}
	}
}