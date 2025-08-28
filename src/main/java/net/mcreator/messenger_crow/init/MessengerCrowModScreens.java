
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.messenger_crow.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.Minecraft;

import net.mcreator.messenger_crow.init.MessengerCrowModMenus.GuiSyncMessage;
import net.mcreator.messenger_crow.client.gui.YellowScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.WhiteScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.ScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.ScrollGUIScreen;
import net.mcreator.messenger_crow.client.gui.RedScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.PurpleScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.PinkScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.OrangeScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.MagentaScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.LimeScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.LightGrayScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.LightBlueScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.GreenScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.GrayScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.CyanScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.CrowInventoryScreen;
import net.mcreator.messenger_crow.client.gui.BrownScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.BlueScrollLecternGUIScreen;
import net.mcreator.messenger_crow.client.gui.BlackScrollLecternGUIScreen;

import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MessengerCrowModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MessengerCrowModMenus.SCROLL_GUI.get(), ScrollGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.CROW_INVENTORY.get(), CrowInventoryScreen::new);
			MenuScreens.register(MessengerCrowModMenus.SCROLL_LECTERN_GUI.get(), ScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.BLACK_SCROLL_LECTERN_GUI.get(), BlackScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.BLUE_SCROLL_LECTERN_GUI.get(), BlueScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.BROWN_SCROLL_LECTERN_GUI.get(), BrownScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.CYAN_SCROLL_LECTERN_GUI.get(), CyanScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.GRAY_SCROLL_LECTERN_GUI.get(), GrayScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.GREEN_SCROLL_LECTERN_GUI.get(), GreenScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.LIGHT_BLUE_SCROLL_LECTERN_GUI.get(), LightBlueScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.LIGHT_GRAY_SCROLL_LECTERN_GUI.get(), LightGrayScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.LIME_SCROLL_LECTERN_GUI.get(), LimeScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.MAGENTA_SCROLL_LECTERN_GUI.get(), MagentaScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.ORANGE_SCROLL_LECTERN_GUI.get(), OrangeScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.PINK_SCROLL_LECTERN_GUI.get(), PinkScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.PURPLE_SCROLL_LECTERN_GUI.get(), PurpleScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.RED_SCROLL_LECTERN_GUI.get(), RedScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.WHITE_SCROLL_LECTERN_GUI.get(), WhiteScrollLecternGUIScreen::new);
			MenuScreens.register(MessengerCrowModMenus.YELLOW_SCROLL_LECTERN_GUI.get(), YellowScrollLecternGUIScreen::new);
		});
	}

	static void handleTextBoxMessage(GuiSyncMessage message) {
		String editbox = message.editbox();
		String value = message.value();
		Screen currentScreen = Minecraft.getInstance().screen;
		if (currentScreen instanceof WidgetScreen sc) {
			HashMap<String, Object> widgets = sc.getWidgets();
			Object obj = widgets.get("text:" + editbox);
			if (obj instanceof EditBox box) {
				box.setValue(value);
			}
		}
	}

	public interface WidgetScreen {
		HashMap<String, Object> getWidgets();
	}
}
