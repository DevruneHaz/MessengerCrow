
package net.mcreator.messenger_crow.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class PhantomCrowFeatherItem extends Item {
	public PhantomCrowFeatherItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
