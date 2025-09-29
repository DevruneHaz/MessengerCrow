package net.devrune.messenger_crow.init;

import net.devrune.messenger_crow.world.inventory.ScrollLecternGUIMenu;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.devrune.messenger_crow.client.gui.ScrollLecternGUIScreen;
import net.devrune.messenger_crow.client.gui.ScrollGUIScreen;
import net.devrune.messenger_crow.client.gui.CrowInventoryScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@SuppressWarnings("unused")
public class MessengerCrowModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MessengerCrowModMenus.SCROLL_GUI.get(), ScrollGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.CROW_INVENTORY.get(), CrowInventoryScreen::new);
			MenuScreens.register(MessengerCrowModMenus.SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory(""));
			MenuScreens.register(MessengerCrowModMenus.BLACK_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("black"));
			MenuScreens.register(MessengerCrowModMenus.BLUE_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("blue"));
			MenuScreens.register(MessengerCrowModMenus.BROWN_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("brown"));
			MenuScreens.register(MessengerCrowModMenus.CYAN_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("cyan"));
			MenuScreens.register(MessengerCrowModMenus.GRAY_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("gray"));
			MenuScreens.register(MessengerCrowModMenus.GREEN_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("green"));
			MenuScreens.register(MessengerCrowModMenus.LIGHT_BLUE_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("light_blue"));
			MenuScreens.register(MessengerCrowModMenus.LIGHT_GRAY_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("light_gray"));
			MenuScreens.register(MessengerCrowModMenus.LIME_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("lime"));
			MenuScreens.register(MessengerCrowModMenus.MAGENTA_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("magenta"));
			MenuScreens.register(MessengerCrowModMenus.ORANGE_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("orange"));
			MenuScreens.register(MessengerCrowModMenus.PINK_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("pink"));
			MenuScreens.register(MessengerCrowModMenus.PURPLE_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("purple"));
			MenuScreens.register(MessengerCrowModMenus.RED_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("red"));
			MenuScreens.register(MessengerCrowModMenus.WHITE_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("white"));
			MenuScreens.register(MessengerCrowModMenus.YELLOW_SCROLL_LECTERN_GUI.get(), scrollLecturnGuiScreenFactory("yellow"));
		});
	}

	static MenuScreens.ScreenConstructor<ScrollLecternGUIMenu, ScrollLecternGUIScreen> scrollLecturnGuiScreenFactory(String color) {
		return (container, inventory, text) -> new ScrollLecternGUIScreen(container, inventory, text, color);
	}
}
