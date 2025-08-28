
package net.devrune.messenger_crow.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class CrowFeatherItem extends Item {
	public CrowFeatherItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
