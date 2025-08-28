package net.mcreator.messenger_crow.block.listener;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.messenger_crow.init.MessengerCrowModBlockEntities;
import net.mcreator.messenger_crow.block.renderer.ScrollLecternTileRenderer;
import net.mcreator.messenger_crow.block.renderer.ScarecrowTileRenderer;
import net.mcreator.messenger_crow.MessengerCrowMod;

@Mod.EventBusSubscriber(modid = MessengerCrowMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(MessengerCrowModBlockEntities.SCARECROW.get(), context -> new ScarecrowTileRenderer());
		event.registerBlockEntityRenderer(MessengerCrowModBlockEntities.SCROLL_LECTERN.get(), context -> new ScrollLecternTileRenderer());
	}
}
