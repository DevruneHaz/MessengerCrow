package net.devrune.messenger_crow.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.devrune.messenger_crow.world.inventory.ScrollLecternGUIMenu;
import net.devrune.messenger_crow.world.inventory.ScrollGUIMenu;
import net.devrune.messenger_crow.world.inventory.CrowInventoryMenu;
import net.devrune.messenger_crow.MessengerCrowMod;

public class MessengerCrowModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MessengerCrowMod.MODID);
	public static final RegistryObject<MenuType<ScrollGUIMenu>> SCROLL_GUI = REGISTRY.register("scroll_gui", () -> IForgeMenuType.create(ScrollGUIMenu::new));
	public static final RegistryObject<MenuType<CrowInventoryMenu>> CROW_INVENTORY = REGISTRY.register("crow_inventory", () -> IForgeMenuType.create(CrowInventoryMenu::new));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> SCROLL_LECTERN_GUI = REGISTRY.register("scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.uncolored));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> BLACK_SCROLL_LECTERN_GUI = REGISTRY.register("black_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.black));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> BLUE_SCROLL_LECTERN_GUI = REGISTRY.register("blue_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.blue));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> BROWN_SCROLL_LECTERN_GUI = REGISTRY.register("brown_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.brown));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> CYAN_SCROLL_LECTERN_GUI = REGISTRY.register("cyan_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.cyan));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> GRAY_SCROLL_LECTERN_GUI = REGISTRY.register("gray_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.gray));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> GREEN_SCROLL_LECTERN_GUI = REGISTRY.register("green_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.green));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> LIGHT_BLUE_SCROLL_LECTERN_GUI = REGISTRY.register("light_blue_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.light_blue));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> LIGHT_GRAY_SCROLL_LECTERN_GUI = REGISTRY.register("light_gray_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.light_gray));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> LIME_SCROLL_LECTERN_GUI = REGISTRY.register("lime_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.lime));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> MAGENTA_SCROLL_LECTERN_GUI = REGISTRY.register("magenta_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.magenta));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> ORANGE_SCROLL_LECTERN_GUI = REGISTRY.register("orange_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.orange));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> PINK_SCROLL_LECTERN_GUI = REGISTRY.register("pink_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.pink));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> PURPLE_SCROLL_LECTERN_GUI = REGISTRY.register("purple_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.purple));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> RED_SCROLL_LECTERN_GUI = REGISTRY.register("red_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.red));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> WHITE_SCROLL_LECTERN_GUI = REGISTRY.register("white_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.white));
	public static final RegistryObject<MenuType<ScrollLecternGUIMenu>> YELLOW_SCROLL_LECTERN_GUI = REGISTRY.register("yellow_scroll_lectern_gui", () -> IForgeMenuType.create(ScrollLecternGUIMenu.yellow));

}