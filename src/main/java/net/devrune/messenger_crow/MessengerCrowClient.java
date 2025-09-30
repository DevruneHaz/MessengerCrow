package net.devrune.messenger_crow;


import net.devrune.messenger_crow.block.renderer.ScarecrowTileRenderer;
import net.devrune.messenger_crow.block.renderer.ScrollLecternTileRenderer;
import net.devrune.messenger_crow.init.MessengerCrowModBlockEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MessengerCrowMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("unused")
public class MessengerCrowClient {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(MessengerCrowModBlockEntities.SCROLL_LECTERN.get(), context -> new ScrollLecternTileRenderer());
        event.registerBlockEntityRenderer(MessengerCrowModBlockEntities.SCARECROW.get(), context -> new ScarecrowTileRenderer());
    }
}
