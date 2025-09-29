package net.devrune.messenger_crow.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.devrune.messenger_crow.MessengerCrowMod;

@SuppressWarnings("unused")
public class MessengerCrowModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MessengerCrowMod.MODID);
	public static final RegistryObject<SoundEvent> CROW_AMBIENT = REGISTRY.register("crow_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("messenger_crow", "crow_ambient")));
	public static final RegistryObject<SoundEvent> CROW_HURT = REGISTRY.register("crow_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("messenger_crow", "crow_hurt")));
	public static final RegistryObject<SoundEvent> CROW_POOF = REGISTRY.register("crow_poof", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("messenger_crow", "crow_poof")));
}
