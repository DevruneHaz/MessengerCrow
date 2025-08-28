package net.devrune.messenger_crow.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class FeatherFanFlappingProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity.getRemainingFireTicks() > 0) {
			if (itemstack.getOrCreateTag().getDouble("flap") == 8) {
				itemstack.getOrCreateTag().putDouble("flap", 0);
				entity.clearFire();
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 100);
			} else {
				itemstack.getOrCreateTag().putDouble("flap", (itemstack.getOrCreateTag().getDouble("flap") + 1));
			}
		}
	}
}
