package net.devrune.messenger_crow.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.devrune.messenger_crow.MessengerCrowMod;

public class MessengerCrowModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MessengerCrowMod.MODID);
	public static final RegistryObject<SimpleParticleType> CROW_TELEPORT = REGISTRY.register("crow_teleport", () -> new SimpleParticleType(false));
}
