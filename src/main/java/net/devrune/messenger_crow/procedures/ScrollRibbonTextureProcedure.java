package net.devrune.messenger_crow.procedures;

import net.minecraft.world.item.ItemStack;

public class ScrollRibbonTextureProcedure {
	public static double execute(ItemStack itemstack) {
		if ((itemstack.getOrCreateTag().getString("ribbon")).equals("black")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A70");
			return 1;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("blue")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A71");
			return 2;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("brown")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A76");
			return 3;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("cyan")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A73");
			return 4;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("gray")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A78");
			return 5;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("green")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A72");
			return 6;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("light_blue")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A79");
			return 7;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("light_gray")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A77");
			return 8;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("lime")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7a");
			return 9;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("magenta")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7d");
			return 10;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("orange")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A76");
			return 11;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("pink")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7d");
			return 12;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("purple")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A75");
			return 13;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("red")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A74");
			return 14;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("white")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7f");
			return 15;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("yellow")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7e");
			return 16;
		}
		return 0;
	}
}
