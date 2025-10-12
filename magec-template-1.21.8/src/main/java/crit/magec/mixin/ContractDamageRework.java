package crit.magec.mixin;

import crit.magec.components.ModComponents;
import crit.magec.item.ModItems;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class ContractDamageRework {

	@Shadow @NotNull public abstract ItemStack getWeaponStack();

	@Shadow public abstract boolean damage(ServerWorld world, DamageSource source, float amount);

	@Inject(at = @At("HEAD"), method = "damage", cancellable = true)
	private void noDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		PlayerEntity target = (PlayerEntity) (Object) this;
		if (target.getOffHandStack().getItem() != ModItems.CONTRACT) return;
		if (!(source.getAttacker() instanceof PlayerEntity attacker)) return;
		String attackeruuid = attacker.getUuidAsString();
		String targetuuid = target.getUuidAsString();
		String targetcontractuuid = target.getOffHandStack().get(ModComponents.BOUND_TO_UUID);
		String attackercontractuuid = attacker.getOffHandStack().get(ModComponents.BOUND_TO_UUID);
		if (attackeruuid.equals(targetcontractuuid)) {
				attacker.damage(world, source, amount);
				cir.setReturnValue(false);
		}
		if (targetuuid.equals(attackercontractuuid)) {
			damage(world, source, amount);
		}
	}
}