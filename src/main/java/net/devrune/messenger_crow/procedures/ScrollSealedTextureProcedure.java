package net.devrune.messenger_crow.procedures;

import net.minecraft.world.item.ItemStack;

public class ScrollSealedTextureProcedure {
	public static double execute(ItemStack itemstack) {
		if (itemstack.getOrCreateTag().getBoolean("sealed") == true) {
			return 1;
		}
		return 0;
	}
}
