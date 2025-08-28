package net.devrune.messenger_crow.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.devrune.messenger_crow.block.display.ScrollLecternDisplayItem;

public class ScrollLecternDisplayModel extends GeoModel<ScrollLecternDisplayItem> {
	@Override
	public ResourceLocation getAnimationResource(ScrollLecternDisplayItem animatable) {
		return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ScrollLecternDisplayItem animatable) {
		return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ScrollLecternDisplayItem entity) {
		return new ResourceLocation("messenger_crow", "textures/block/lectern_scroll.png");
	}
}
