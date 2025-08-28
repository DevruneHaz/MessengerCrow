package net.devrune.messenger_crow.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.devrune.messenger_crow.entity.CrowEntity;

public class CrowModel extends GeoModel<CrowEntity> {
	@Override
	public ResourceLocation getAnimationResource(CrowEntity entity) {
		return new ResourceLocation("messenger_crow", "animations/crow.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(CrowEntity entity) {
		return new ResourceLocation("messenger_crow", "geo/crow.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(CrowEntity entity) {
		return new ResourceLocation("messenger_crow", "textures/entities/" + entity.getTexture() + ".png");
	}

}
