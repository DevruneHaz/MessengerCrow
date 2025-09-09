
package net.devrune.messenger_crow.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

public class FeatherFanItem extends Item {
	public FeatherFanItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.BLOCK;
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 1200;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		entity.startUsingItem(hand);
		handleFlap(entity, ar.getObject());
		return ar;
	}

	public static void handleFlap(Entity entity, ItemStack itemstack) {
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
