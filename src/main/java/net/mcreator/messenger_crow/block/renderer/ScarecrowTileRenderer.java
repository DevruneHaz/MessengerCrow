package net.mcreator.messenger_crow.block.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.messenger_crow.block.model.ScarecrowBlockModel;
import net.mcreator.messenger_crow.block.entity.ScarecrowTileEntity;

public class ScarecrowTileRenderer extends GeoBlockRenderer<ScarecrowTileEntity> {
	public ScarecrowTileRenderer() {
		super(new ScarecrowBlockModel());
	}

	@Override
	public RenderType getRenderType(ScarecrowTileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
