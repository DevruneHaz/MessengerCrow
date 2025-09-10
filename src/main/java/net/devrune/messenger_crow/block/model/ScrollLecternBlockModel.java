package net.devrune.messenger_crow.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.devrune.messenger_crow.block.entity.ScrollLecternTileEntity;

public class ScrollLecternBlockModel extends GeoModel<ScrollLecternTileEntity> {
	@Override
	public ResourceLocation getAnimationResource(ScrollLecternTileEntity animatable) {
		return new ResourceLocation("messenger_crow", "animations/scroll_lectern.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ScrollLecternTileEntity animatable) {
		return new ResourceLocation("messenger_crow", "geo/scroll_lectern.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ScrollLecternTileEntity animatable) {
		final int blockstate = animatable.blockstateNew;
		String textureLocation = switch (blockstate) {
			case 1 -> "textures/block/black_lectern_scroll.png";
			case 2 -> "textures/block/blue_lectern_scroll.png";
			case 3 -> "textures/block/brown_lectern_scroll.png";
			case 4 -> "textures/block/cyan_lectern_scroll.png";
			case 5 -> "textures/block/gray_lectern_scroll.png";
			case 6 -> "textures/block/green_lectern_scroll.png";
			case 7 -> "textures/block/light_blue_lectern_scroll.png";
			case 8 -> "textures/block/light_gray_lectern_scroll.png";
			case 9 -> "textures/block/lime_lectern_scroll.png";
			case 10 -> "textures/block/magenta_lectern_scroll.png";
			case 11 -> "textures/block/orange_lectern_scroll.png";
			case 12 -> "textures/block/pink_lectern_scroll.png";
			case 13 -> "textures/block/purple_lectern_scroll.png";
			case 14 -> "textures/block/red_lectern_scroll.png";
			case 15 -> "textures/block/white_lectern_scroll.png";
			case 16 -> "textures/block/yellow_lectern_scroll.png";
			default -> "textures/block/lectern_scroll.png";
		};
		return new ResourceLocation("messenger_crow", textureLocation);
	}
}
