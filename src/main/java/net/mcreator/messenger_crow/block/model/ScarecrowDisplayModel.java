package net.mcreator.messenger_crow.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.messenger_crow.block.display.ScarecrowDisplayItem;

public class ScarecrowDisplayModel extends GeoModel<ScarecrowDisplayItem> {
	@Override
	public ResourceLocation getAnimationResource(ScarecrowDisplayItem animatable) {
		return new ResourceLocation("messenger_crow", "animations/scarecrow.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ScarecrowDisplayItem animatable) {
		return new ResourceLocation("messenger_crow", "geo/scarecrow.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ScarecrowDisplayItem entity) {
		return new ResourceLocation("messenger_crow", "textures/block/scarecrow.png");
	}
}
