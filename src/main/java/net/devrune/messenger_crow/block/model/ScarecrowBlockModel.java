package net.devrune.messenger_crow.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.devrune.messenger_crow.block.entity.ScarecrowTileEntity;

public class ScarecrowBlockModel extends GeoModel<ScarecrowTileEntity> {
	@Override
	public ResourceLocation getAnimationResource(ScarecrowTileEntity animatable) {
		return new ResourceLocation("messenger_crow", "animations/scarecrow.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ScarecrowTileEntity animatable) {
		return new ResourceLocation("messenger_crow", "geo/scarecrow.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ScarecrowTileEntity animatable) {
		final int blockstate = animatable.blockstateNew;
		if (blockstate == 1)
			return new ResourceLocation("messenger_crow", "textures/block/scarecrow_lit.png");
		return new ResourceLocation("messenger_crow", "textures/block/scarecrow.png");
	}
}
