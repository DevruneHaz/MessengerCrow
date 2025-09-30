package net.devrune.messenger_crow.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import net.devrune.messenger_crow.item.ScrollItem;
import net.devrune.messenger_crow.item.FeatherFanItem;
import net.devrune.messenger_crow.item.CrowSkullMaskItem;
import net.devrune.messenger_crow.block.display.ScarecrowDisplayItem;
import net.devrune.messenger_crow.MessengerCrowMod;

import java.util.LinkedHashSet;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@SuppressWarnings("unused")
public class MessengerCrowModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MessengerCrowMod.MODID);
	public static LinkedHashSet<Supplier<? extends ItemStack>> CREATIVE_TAB_ITEMS = new LinkedHashSet<>();

	public static <V extends ItemLike, T extends Supplier<V>> T registerWithTab(T i) {
		CREATIVE_TAB_ITEMS.add(() -> new ItemStack(i.get()));
		return i;
	}

	public static final RegistryObject<Item> SCROLL = registerWithTab(REGISTRY.register("scroll", ScrollItem::new));

	private static ItemStack createScroll(String colour) {
		ItemStack is = new ItemStack(SCROLL.get());
		is.getOrCreateTag().putString("ribbon", colour);
		return is;
	}
	static {
		CREATIVE_TAB_ITEMS.add(() -> createScroll("black"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("blue"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("brown"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("cyan"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("gray"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("green"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("light_blue"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("light_gray"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("lime"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("magenta"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("orange"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("pink"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("purple"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("red"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("white"));
		CREATIVE_TAB_ITEMS.add(() -> createScroll("yellow"));
	}


	public static final RegistryObject<Item> CROW_FEATHER = registerWithTab(REGISTRY.register("crow_feather", withProperties((p) -> p.stacksTo(64).rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> SCARECROW = registerWithTab(REGISTRY.register(MessengerCrowModBlocks.SCARECROW.getId().getPath(), () -> new ScarecrowDisplayItem(MessengerCrowModBlocks.SCARECROW.get(), new Item.Properties())));
	public static final RegistryObject<Item> FEATHER_FAN = registerWithTab(REGISTRY.register("feather_fan", FeatherFanItem::new));
	public static final RegistryObject<Item> BLACK_RIBBON = registerWithTab(REGISTRY.register("black_ribbon", withProperties()));
	public static final RegistryObject<Item> BLUE_RIBBON = registerWithTab(REGISTRY.register("blue_ribbon", withProperties()));
	public static final RegistryObject<Item> BROWN_RIBBON = registerWithTab(REGISTRY.register("brown_ribbon", withProperties()));
	public static final RegistryObject<Item> CYAN_RIBBON = registerWithTab(REGISTRY.register("cyan_ribbon", withProperties()));
	public static final RegistryObject<Item> GRAY_RIBBON = registerWithTab(REGISTRY.register("gray_ribbon", withProperties()));
	public static final RegistryObject<Item> GREEN_RIBBON = registerWithTab(REGISTRY.register("green_ribbon", withProperties()));
	public static final RegistryObject<Item> LIGHT_BLUE_RIBBON = registerWithTab(REGISTRY.register("light_blue_ribbon", withProperties()));
	public static final RegistryObject<Item> LIGHT_GRAY_RIBBON = registerWithTab(REGISTRY.register("light_gray_ribbon", withProperties()));
	public static final RegistryObject<Item> LIME_RIBBON = registerWithTab(REGISTRY.register("lime_ribbon", withProperties()));
	public static final RegistryObject<Item> MAGENTA_RIBBON = registerWithTab(REGISTRY.register("magenta_ribbon", withProperties()));
	public static final RegistryObject<Item> ORANGE_RIBBON = registerWithTab(REGISTRY.register("orange_ribbon", withProperties()));
	public static final RegistryObject<Item> PINK_RIBBON = registerWithTab(REGISTRY.register("pink_ribbon", withProperties()));
	public static final RegistryObject<Item> PURPLE_RIBBON = registerWithTab(REGISTRY.register("purple_ribbon", withProperties()));
	public static final RegistryObject<Item> RED_RIBBON = registerWithTab(REGISTRY.register("red_ribbon", withProperties()));
	public static final RegistryObject<Item> WHITE_RIBBON = registerWithTab(REGISTRY.register("white_ribbon", withProperties()));
	public static final RegistryObject<Item> YELLOW_RIBBON = registerWithTab(REGISTRY.register("yellow_ribbon", withProperties()));
	public static final RegistryObject<Item> PHANTOM_CROW_FEATHER = registerWithTab(REGISTRY.register("phantom_crow_feather", withProperties((p) -> p.stacksTo(64).rarity(Rarity.COMMON))));
	public static final RegistryObject<Item> CROW_SKULL_MASK_HELMET = registerWithTab(REGISTRY.register("crow_skull_mask_helmet", CrowSkullMaskItem.Helmet::new));


	public static Supplier<Item> withProperties() {
		return () -> new Item(new Item.Properties());
	}

	public static Supplier<Item> withProperties(Function<Item.Properties, Item.Properties> pBuilder) {
		return () -> new Item(pBuilder.apply(new Item.Properties()));
	}
	
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(SCROLL.get(), new ResourceLocation("messenger_crow:scroll_sealed"), (itemStackToRender, clientWorld, entity, itemEntityId) -> itemStackToRender.getOrCreateTag().getBoolean("sealed") ? 1 : 0);
			ItemProperties.register(SCROLL.get(), new ResourceLocation("messenger_crow:scroll_ribbon"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) getColourIndex(itemStackToRender));
		});
	}

	public static double getColourIndex(ItemStack itemstack) {
		String ribbonColour = (itemstack.getOrCreateTag().getString("ribbon"));
		String colourString = null;
		int colourValue = 0;
		switch (ribbonColour) {
			case "black" -> {
				colourString = "§0";
				colourValue = 1;
			}
			case "blue" -> {
				colourString = "§1";
				colourValue = 2;
			}
			case "brown" -> {
				colourString = "§6";
				colourValue = 3;
			}
			case "cyan" -> {
				colourString = "§3";
				colourValue = 4;
			}
			case "gray" -> {
				colourString = "§8";
				colourValue = 5;
			}
			case "green" -> {
				colourString = "§2";
				colourValue = 6;
			}
			case "light_blue" -> {
				colourString = "§9";
				colourValue = 7;
			}
			case "light_gray" -> {
				colourString = "§7";
				colourValue = 8;
			}
			case "lime" -> {
				colourString = "§a";
				colourValue = 9;
			}
			case "magenta" -> {
				colourString = "§d";
				colourValue = 10;
			}
			case "orange" -> {
				colourString = "§6";
				colourValue = 11;
			}
			case "pink" -> {
				colourString = "§d";
				colourValue = 12;
			}
			case "purple" -> {
				colourString = "§5";
				colourValue = 13;
			}
			case "red" -> {
				colourString = "§4";
				colourValue = 14;
			}
			case "white" -> {
				colourString = "§f";
				colourValue = 15;
			}
			case "yellow" -> {
				colourString = "§e";
				colourValue = 16;
			}
            default -> {}
        }
		
		
		if (colourString != null) {
			itemstack.getOrCreateTag().putString("lorecolor", colourString);
		}
		return colourValue;
	}
}
