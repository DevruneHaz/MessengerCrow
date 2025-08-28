package net.devrune.messenger_crow.procedures;

import net.minecraft.world.item.ItemStack;

public class ScrollTickUpdateProcedure {
	public static void execute(ItemStack itemstack) {
		if ((itemstack.getOrCreateTag().getString("target")).isEmpty()) {
			itemstack.getOrCreateTag().putString("target", "Target Unset");
		}
		if (itemstack.getOrCreateTag().getBoolean("sealed") == true) {
			if ((itemstack.getOrCreateTag().getString("lore")).isEmpty()) {
				itemstack.getOrCreateTag().putString("lore", "\u00A77Crouch + RMB to open");
			}
		} else {
			if ((itemstack.getOrCreateTag().getString("lore")).isEmpty()) {
				itemstack.getOrCreateTag().putString("lore", "\u00A77Place in a lectern");
			}
		}
	}
}
