
package net.mcreator.messenger_crow.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class RedRibbonItem extends Item {
	public RedRibbonItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
