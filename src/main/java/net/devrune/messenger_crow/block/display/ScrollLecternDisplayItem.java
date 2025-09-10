package net.devrune.messenger_crow.block.display;

import net.devrune.messenger_crow.block.display.util.DisplayItemClientExtensions;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoItem;

import net.minecraftforge.common.property.Properties;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;

import net.devrune.messenger_crow.block.renderer.ScrollLecternDisplayItemRenderer;

import java.util.function.Consumer;


//TODO very similar to ScarecrowDisplayItem. Consider introducing a common parent class.
public class ScrollLecternDisplayItem extends BlockItem implements GeoItem {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

	public ScrollLecternDisplayItem(Block block, Properties settings) {
		super(block, settings);
	}


	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new DisplayItemClientExtensions(new ScrollLecternDisplayItemRenderer()));
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
