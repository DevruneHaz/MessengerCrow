package net.devrune.messenger_crow.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.devrune.messenger_crow.client.model.Modelcrow_skull_mask;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
@SuppressWarnings("unused")
public class MessengerCrowModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelcrow_skull_mask.LAYER_LOCATION, Modelcrow_skull_mask::createBodyLayer);
	}
}
