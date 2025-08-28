package net.devrune.messenger_crow.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.devrune.messenger_crow.block.model.ScrollLecternDisplayModel;
import net.devrune.messenger_crow.block.display.ScrollLecternDisplayItem;

public class ScrollLecternDisplayItemRenderer extends GeoItemRenderer<ScrollLecternDisplayItem> {
	public ScrollLecternDisplayItemRenderer() {
		super(new ScrollLecternDisplayModel());
	}

	@Override
	public RenderType getRenderType(ScrollLecternDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
