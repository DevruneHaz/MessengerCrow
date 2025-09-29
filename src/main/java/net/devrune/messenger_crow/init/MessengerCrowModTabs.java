package net.devrune.messenger_crow.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.devrune.messenger_crow.MessengerCrowMod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@SuppressWarnings("unused")
public class MessengerCrowModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MessengerCrowMod.MODID);
	public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = REGISTRY.register(MessengerCrowMod.MODID,
			() -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.messenger_crow"))
					.icon(() -> new ItemStack(MessengerCrowModItems.CROW_FEATHER.get()))
					.displayItems((parameters, output) -> MessengerCrowModItems.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
					.build());
}
