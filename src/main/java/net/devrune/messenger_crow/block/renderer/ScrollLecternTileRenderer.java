package net.devrune.messenger_crow.block.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.devrune.messenger_crow.block.model.ScrollLecternBlockModel;
import net.devrune.messenger_crow.block.entity.ScrollLecternBlockEntity;

public class ScrollLecternTileRenderer extends GeoBlockRenderer<ScrollLecternBlockEntity> {
	public ScrollLecternTileRenderer() {
		super(new ScrollLecternBlockModel());
	}

	@Override
	public RenderType getRenderType(ScrollLecternBlockEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
