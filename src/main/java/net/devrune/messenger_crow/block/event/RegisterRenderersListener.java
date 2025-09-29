package net.devrune.messenger_crow.block.event;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.devrune.messenger_crow.init.MessengerCrowModBlockEntities;
import net.devrune.messenger_crow.block.renderer.ScrollLecternTileRenderer;
import net.devrune.messenger_crow.block.renderer.ScarecrowTileRenderer;
import net.devrune.messenger_crow.MessengerCrowMod;

//TODO I'd like to move this into the tile renderer classes themselves, with an event subscription in a dedicated client event class
@Mod.EventBusSubscriber(modid = MessengerCrowMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterRenderersListener {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(MessengerCrowModBlockEntities.SCARECROW.get(), context -> new ScarecrowTileRenderer());
		event.registerBlockEntityRenderer(MessengerCrowModBlockEntities.SCROLL_LECTERN.get(), context -> new ScrollLecternTileRenderer());
	}
}
