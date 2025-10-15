package crit.magec.mixin;

import com.ibm.icu.impl.number.AffixPatternProvider;
import crit.magec.components.ModComponents;
import crit.magec.item.ModItems;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.swing.text.Position;
import java.util.Set;

@Mixin(PlayerEntity.class)
public abstract class SendInsideContract {


	@Shadow @NotNull public abstract ItemStack getWeaponStack();

	@Inject(at = @At("HEAD"), method = "damage", cancellable = true)
	private void insideContract(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		PlayerEntity target = (PlayerEntity) (Object) this;
		if (target.getOffHandStack().getItem() != ModItems.CONTRACT) return;
		if (!(source.getAttacker() instanceof PlayerEntity attacker)) return;
		String targetuuid = target.getUuidAsString();
		String attackercontractuuid = attacker.getOffHandStack().get(ModComponents.BOUND_TO_UUID);
		if (targetuuid.equals(attackercontractuuid)) {
			float damageAfterHiton = target.getHealth() - amount;
			if (damageAfterHiton <= 0) {
				RegistryKey<World> contractedKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of("magec", "insidethecontract"));
				ServerWorld contracted = target.getServer().getWorld(contractedKey);
				target.teleport(contracted, target.getX(), target.getY(),target.getZ(), Set.of(PositionFlag.X), target.getYaw(), target.getPitch(), true);
				cir.setReturnValue(false);
			}
		}

    }
}