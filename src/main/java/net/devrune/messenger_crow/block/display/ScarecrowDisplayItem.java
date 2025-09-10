package net.devrune.messenger_crow.block.display;

import net.devrune.messenger_crow.block.display.util.DisplayItemClientExtensions;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoItem;

import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;

import net.devrune.messenger_crow.block.renderer.ScarecrowDisplayItemRenderer;

import java.util.function.Consumer;


//TODO very similar to ScrollLecternDisplayItem. Consider introducing a common parent class.
public class ScarecrowDisplayItem extends BlockItem implements GeoItem {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public ScarecrowDisplayItem(Block block, Properties settings) {
		super(block, settings);
	}


	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new DisplayItemClientExtensions(new ScarecrowDisplayItemRenderer()));
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "controller", 0, (event) -> PlayState.CONTINUE));
	}


	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
