
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.messenger_crow.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.messenger_crow.client.particle.CrowTeleportParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MessengerCrowModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(MessengerCrowModParticleTypes.CROW_TELEPORT.get(), CrowTeleportParticle::provider);
	}
}
