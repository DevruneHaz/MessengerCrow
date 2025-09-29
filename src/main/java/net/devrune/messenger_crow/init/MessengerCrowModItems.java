package net.devrune.messenger_crow.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
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
	public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = new LinkedHashSet<>();

	public static RegistryObject<Item> registerWithTab(RegistryObject<Item> i) {
		CREATIVE_TAB_ITEMS.add(i);
		return i;
	}

	public static final RegistryObject<Item> SCROLL = REGISTRY.register("scroll", ScrollItem::new);
	public static final RegistryObject<Item> CROW_FEATHER = REGISTRY.register("crow_feather", withProperties((p) -> p.stacksTo(64).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> SCARECROW = REGISTRY.register(MessengerCrowModBlocks.SCARECROW.getId().getPath(), () -> new ScarecrowDisplayItem(MessengerCrowModBlocks.SCARECROW.get(), new Item.Properties()));
	public static final RegistryObject<Item> FEATHER_FAN = REGISTRY.register("feather_fan", FeatherFanItem::new);
	public static final RegistryObject<Item> BLACK_RIBBON = REGISTRY.register("black_ribbon", withProperties());
	public static final RegistryObject<Item> BLUE_RIBBON = REGISTRY.register("blue_ribbon", withProperties());
	public static final RegistryObject<Item> BROWN_RIBBON = REGISTRY.register("brown_ribbon", withProperties());
	public static final RegistryObject<Item> CYAN_RIBBON = REGISTRY.register("cyan_ribbon", withProperties());
	public static final RegistryObject<Item> GRAY_RIBBON = REGISTRY.register("gray_ribbon", withProperties());
	public static final RegistryObject<Item> GREEN_RIBBON = REGISTRY.register("green_ribbon", withProperties());
	public static final RegistryObject<Item> LIGHT_BLUE_RIBBON = REGISTRY.register("light_blue_ribbon", withProperties());
	public static final RegistryObject<Item> LIGHT_GRAY_RIBBON = REGISTRY.register("light_gray_ribbon", withProperties());
	public static final RegistryObject<Item> LIME_RIBBON = REGISTRY.register("lime_ribbon", withProperties());
	public static final RegistryObject<Item> MAGENTA_RIBBON = REGISTRY.register("magenta_ribbon", withProperties());
	public static final RegistryObject<Item> ORANGE_RIBBON = REGISTRY.register("orange_ribbon", withProperties());
	public static final RegistryObject<Item> PINK_RIBBON = REGISTRY.register("pink_ribbon", withProperties());
	public static final RegistryObject<Item> PURPLE_RIBBON = REGISTRY.register("purple_ribbon", withProperties());
	public static final RegistryObject<Item> RED_RIBBON = REGISTRY.register("red_ribbon", withProperties());
	public static final RegistryObject<Item> WHITE_RIBBON = REGISTRY.register("white_ribbon", withProperties());
	public static final RegistryObject<Item> YELLOW_RIBBON = REGISTRY.register("yellow_ribbon", withProperties());
	public static final RegistryObject<Item> PHANTOM_CROW_FEATHER = REGISTRY.register("phantom_crow_feather", withProperties((p) -> p.stacksTo(64).rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> CROW_SKULL_MASK_HELMET = REGISTRY.register("crow_skull_mask_helmet", CrowSkullMaskItem.Helmet::new);


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
		}
		return 0;
	}
}
