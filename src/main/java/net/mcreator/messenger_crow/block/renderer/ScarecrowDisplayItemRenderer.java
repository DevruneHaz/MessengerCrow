package net.mcreator.messenger_crow.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.messenger_crow.block.model.ScarecrowDisplayModel;
import net.mcreator.messenger_crow.block.display.ScarecrowDisplayItem;

public class ScarecrowDisplayItemRenderer extends GeoItemRenderer<ScarecrowDisplayItem> {
	public ScarecrowDisplayItemRenderer() {
		super(new ScarecrowDisplayModel());
	}

	@Override
	public RenderType getRenderType(ScarecrowDisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
