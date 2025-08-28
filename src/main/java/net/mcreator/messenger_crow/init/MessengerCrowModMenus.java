
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.messenger_crow.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;

import net.mcreator.messenger_crow.world.inventory.YellowScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.WhiteScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.ScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.ScrollGUIMenu;
import net.mcreator.messenger_crow.world.inventory.RedScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.PurpleScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.PinkScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.OrangeScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.MagentaScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.LimeScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.LightGrayScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.LightBlueScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.GreenScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.GrayScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.CyanScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.CrowInventoryMenu;
import net.mcreator.messenger_crow.world.inventory.BrownScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.BlueScrollLecternGUIMenu;
import net.mcreator.messenger_crow.world.inventory.BlackScrollLecternGUIMenu;
import net.mcreator.messenger_crow.MessengerCrowMod;

import javax.annotation.Nullable;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MessengerCrowModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MessengerCrowMod.MODID);
	public static final RegistryObject<MenuType<ScrollGUIMenu>> SCROLL_GUI = REGISTRY.register("scroll_gui", () -> IForgeMenuType.create(ScrollGUIMenu::new));
	public static final RegistryObject<MenuType<CrowInventoryMenu>> CROW_INVENTORY = REGISTRY.register("crow_inventory", () -> IForgeMenuType.create(CrowInventoryMenu::new));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> SCROLL_LECTERN_GUI = REGISTRY.register("scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<BlackScrollLecternGUIMenu>> BLACK_SCROLL_LECTERN_GUI = REGISTRY.register("black_scroll_lectern_gui", () -> IForgeMenuType.create(BlackScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<BlueScrollLecternGUIMenu>> BLUE_SCROLL_LECTERN_GUI = REGISTRY.register("blue_scroll_lectern_gui", () -> IForgeMenuType.create(BlueScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<BrownScrollLecternGUIMenu>> BROWN_SCROLL_LECTERN_GUI = REGISTRY.register("brown_scroll_lectern_gui", () -> IForgeMenuType.create(BrownScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<CyanScrollLecternGUIMenu>> CYAN_SCROLL_LECTERN_GUI = REGISTRY.register("cyan_scroll_lectern_gui", () -> IForgeMenuType.create(CyanScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<GrayScrollLecternGUIMenu>> GRAY_SCROLL_LECTERN_GUI = REGISTRY.register("gray_scroll_lectern_gui", () -> IForgeMenuType.create(GrayScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<GreenScrollLecternGUIMenu>> GREEN_SCROLL_LECTERN_GUI = REGISTRY.register("green_scroll_lectern_gui", () -> IForgeMenuType.create(GreenScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<LightBlueScrollLecternGUIMenu>> LIGHT_BLUE_SCROLL_LECTERN_GUI = REGISTRY.register("light_blue_scroll_lectern_gui", () -> IForgeMenuType.create(LightBlueScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<LightGrayScrollLecternGUIMenu>> LIGHT_GRAY_SCROLL_LECTERN_GUI = REGISTRY.register("light_gray_scroll_lectern_gui", () -> IForgeMenuType.create(LightGrayScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<LimeScrollLecternGUIMenu>> LIME_SCROLL_LECTERN_GUI = REGISTRY.register("lime_scroll_lectern_gui", () -> IForgeMenuType.create(LimeScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<MagentaScrollLecternGUIMenu>> MAGENTA_SCROLL_LECTERN_GUI = REGISTRY.register("magenta_scroll_lectern_gui", () -> IForgeMenuType.create(MagentaScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<OrangeScrollLecternGUIMenu>> ORANGE_SCROLL_LECTERN_GUI = REGISTRY.register("orange_scroll_lectern_gui", () -> IForgeMenuType.create(OrangeScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<PinkScrollLecternGUIMenu>> PINK_SCROLL_LECTERN_GUI = REGISTRY.register("pink_scroll_lectern_gui", () -> IForgeMenuType.create(PinkScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<PurpleScrollLecternGUIMenu>> PURPLE_SCROLL_LECTERN_GUI = REGISTRY.register("purple_scroll_lectern_gui", () -> IForgeMenuType.create(PurpleScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<RedScrollLecternGUIMenu>> RED_SCROLL_LECTERN_GUI = REGISTRY.register("red_scroll_lectern_gui", () -> IForgeMenuType.create(RedScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<WhiteScrollLecternGUIMenu>> WHITE_SCROLL_LECTERN_GUI = REGISTRY.register("white_scroll_lectern_gui", () -> IForgeMenuType.create(WhiteScrollLecternGUIMenu::new));
	public static final RegistryObject<MenuType<YellowScrollLecternGUIMenu>> YELLOW_SCROLL_LECTERN_GUI = REGISTRY.register("yellow_scroll_lectern_gui", () -> IForgeMenuType.create(YellowScrollLecternGUIMenu::new));

	public static void setText(String boxname, String value, @Nullable ServerPlayer player) {
		if (player != null) {
			MessengerCrowMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> player), new GuiSyncMessage(boxname, value));
		} else {
			MessengerCrowMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new GuiSyncMessage(boxname, value));
		}
	}

	public static class GuiSyncMessage {
		private final String textboxid;
		private final String data;

		public GuiSyncMessage(FriendlyByteBuf buffer) {
			this.textboxid = buffer.readComponent().getString();
			this.data = buffer.readComponent().getString();
		}

		public GuiSyncMessage(String textboxid, String data) {
			this.textboxid = textboxid;
			this.data = data;
		}

		public static void buffer(GuiSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeComponent(Component.literal(message.textboxid));
			buffer.writeComponent(Component.literal(message.data));
		}

		public static void handleData(GuiSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					MessengerCrowModScreens.handleTextBoxMessage(message);
				}
			});
			context.setPacketHandled(true);
		}

		String editbox() {
			return this.textboxid;
		}

		String value() {
			return this.data;
		}
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		MessengerCrowMod.addNetworkMessage(GuiSyncMessage.class, GuiSyncMessage::buffer, GuiSyncMessage::new, GuiSyncMessage::handleData);
	}
}
