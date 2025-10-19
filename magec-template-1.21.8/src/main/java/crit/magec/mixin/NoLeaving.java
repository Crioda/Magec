package crit.magec.mixin;

import crit.magec.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Optional;

@Mixin(PlayerEntity.class)
public abstract class NoLeaving {
// could i just have one mixin that injects into playerentity, yes, am i going to do that, NO!!!!!!

	@Inject(at = @At("HEAD"), method = "damage", cancellable = true)
	private void twoHands(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		PlayerEntity target = (PlayerEntity) (Object) this;
		float damageAfterHiton = target.getHealth() - amount;
		if (world.getDimension().respawnAnchorWorks() && world.getDimension().bedWorks() && damageAfterHiton <= 0) {
			BlockPos pos = world.getLevelProperties().getSpawnPos();
			target.getInventory().dropAll();
			target.setHealth(20);
			target.incrementStat(Stats.DEATHS);
			target.resetStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_DEATH));
			target.resetStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST));
			target.setLastDeathPos(Optional.of(GlobalPos.create(target.getWorld().getRegistryKey(), target.getBlockPos())));
			target.extinguish();
			target.setOnFire(false);
			target.teleport(pos.getX(), pos.getY(), pos.getZ(), false);
			cir.setReturnValue(false);
		}
	}
}