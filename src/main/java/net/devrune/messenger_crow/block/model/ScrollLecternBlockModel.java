package net.devrune.messenger_crow.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.devrune.messenger_crow.block.entity.ScrollLecternTileEntity;

public class ScrollLecternBlockModel extends GeoModel<ScrollLecternTileEntity> {
	@Override
	public ResourceLocation getAnimationResource(ScrollLecternTileEntity animatable) {
		final int blockstate = animatable.blockstateNew;
		if (blockstate == 1)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 2)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 3)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 4)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 5)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 6)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 7)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 8)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 9)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 10)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 11)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 12)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 13)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 14)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 15)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		if (blockstate == 16)
			return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
		return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ScrollLecternTileEntity animatable) {
		final int blockstate = animatable.blockstateNew;
		if (blockstate == 1)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 2)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 3)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 4)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 5)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 6)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 7)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 8)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 9)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 10)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 11)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 12)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 13)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 14)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 15)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		if (blockstate == 16)
			return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
		return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ScrollLecternTileEntity animatable) {
		final int blockstate = animatable.blockstateNew;
		if (blockstate == 1)
			return new ResourceLocation("messenger_crow", "textures/block/black_lectern_scroll.png");
		if (blockstate == 2)
			return new ResourceLocation("messenger_crow", "textures/block/blue_lectern_scroll.png");
		if (blockstate == 3)
			return new ResourceLocation("messenger_crow", "textures/block/brown_lectern_scroll.png");
		if (blockstate == 4)
			return new ResourceLocation("messenger_crow", "textures/block/cyan_lectern_scroll.png");
		if (blockstate == 5)
			return new ResourceLocation("messenger_crow", "textures/block/gray_lectern_scroll.png");
		if (blockstate == 6)
			return new ResourceLocation("messenger_crow", "textures/block/green_lectern_scroll.png");
		if (blockstate == 7)
			return new ResourceLocation("messenger_crow", "textures/block/light_blue_lectern_scroll.png");
		if (blockstate == 8)
			return new ResourceLocation("messenger_crow", "textures/block/light_gray_lectern_scroll.png");
		if (blockstate == 9)
			return new ResourceLocation("messenger_crow", "textures/block/lime_lectern_scroll.png");
		if (blockstate == 10)
			return new ResourceLocation("messenger_crow", "textures/block/magenta_lectern_scroll.png");
		if (blockstate == 11)
			return new ResourceLocation("messenger_crow", "textures/block/orange_lectern_scroll.png");
		if (blockstate == 12)
			return new ResourceLocation("messenger_crow", "textures/block/pink_lectern_scroll.png");
		if (blockstate == 13)
			return new ResourceLocation("messenger_crow", "textures/block/purple_lectern_scroll.png");
		if (blockstate == 14)
			return new ResourceLocation("messenger_crow", "textures/block/red_lectern_scroll.png");
		if (blockstate == 15)
			return new ResourceLocation("messenger_crow", "textures/block/white_lectern_scroll.png");
		if (blockstate == 16)
			return new ResourceLocation("messenger_crow", "textures/block/yellow_lectern_scroll.png");
		return new ResourceLocation("messenger_crow", "textures/block/lectern_scroll.png");
	}
}
