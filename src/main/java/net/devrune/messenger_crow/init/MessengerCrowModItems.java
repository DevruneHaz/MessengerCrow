
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.devrune.messenger_crow.init;

import net.minecraft.world.item.ItemStack;
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
import net.devrune.messenger_crow.item.PhantomCrowFeatherItem;
import net.devrune.messenger_crow.item.FeatherFanItem;
import net.devrune.messenger_crow.item.CrowSkullMaskItem;
import net.devrune.messenger_crow.item.CrowFeatherItem;
import net.devrune.messenger_crow.block.display.ScarecrowDisplayItem;
import net.devrune.messenger_crow.MessengerCrowMod;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MessengerCrowModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MessengerCrowMod.MODID);
	public static LinkedHashSet<RegistryObject<Item>> CREATIVE_TAB_ITEMS = new LinkedHashSet<>();

	public static RegistryObject<Item> registerWithTab(RegistryObject<Item> i) {
		CREATIVE_TAB_ITEMS.add(i);
		return i;
	}

	public static final RegistryObject<Item> SCROLL = registerWithTab(REGISTRY.register("scroll", () -> new ScrollItem()));
	public static final RegistryObject<Item> CROW_FEATHER = registerWithTab(REGISTRY.register("crow_feather", () -> new CrowFeatherItem()));
	public static final RegistryObject<Item> SCARECROW = registerWithTab(REGISTRY.register(MessengerCrowModBlocks.SCARECROW.getId().getPath(), () -> new ScarecrowDisplayItem(MessengerCrowModBlocks.SCARECROW.get(), new Item.Properties())));
	public static final RegistryObject<Item> FEATHER_FAN = registerWithTab(REGISTRY.register("feather_fan", () -> new FeatherFanItem()));
	public static final RegistryObject<Item> BLACK_RIBBON = registerWithTab(REGISTRY.register("black_ribbon", defaultItem()));
	public static final RegistryObject<Item> BLUE_RIBBON = registerWithTab(REGISTRY.register("blue_ribbon", defaultItem()));
	public static final RegistryObject<Item> BROWN_RIBBON = registerWithTab(REGISTRY.register("brown_ribbon", defaultItem()));
	public static final RegistryObject<Item> CYAN_RIBBON = registerWithTab(REGISTRY.register("cyan_ribbon", defaultItem()));
	public static final RegistryObject<Item> GRAY_RIBBON = registerWithTab(REGISTRY.register("gray_ribbon", defaultItem()));
	public static final RegistryObject<Item> GREEN_RIBBON = registerWithTab(REGISTRY.register("green_ribbon", defaultItem()));
	public static final RegistryObject<Item> LIGHT_BLUE_RIBBON = registerWithTab(REGISTRY.register("light_blue_ribbon", defaultItem()));
	public static final RegistryObject<Item> LIGHT_GRAY_RIBBON = registerWithTab(REGISTRY.register("light_gray_ribbon", defaultItem()));
	public static final RegistryObject<Item> LIME_RIBBON = registerWithTab(REGISTRY.register("lime_ribbon", defaultItem()));
	public static final RegistryObject<Item> MAGENTA_RIBBON = registerWithTab(REGISTRY.register("magenta_ribbon", defaultItem()));
	public static final RegistryObject<Item> ORANGE_RIBBON = registerWithTab(REGISTRY.register("orange_ribbon", defaultItem()));
	public static final RegistryObject<Item> PINK_RIBBON = registerWithTab(REGISTRY.register("pink_ribbon", defaultItem()));
	public static final RegistryObject<Item> PURPLE_RIBBON = registerWithTab(REGISTRY.register("purple_ribbon", defaultItem()));
	public static final RegistryObject<Item> RED_RIBBON = registerWithTab(REGISTRY.register("red_ribbon", defaultItem()));
	public static final RegistryObject<Item> WHITE_RIBBON = registerWithTab(REGISTRY.register("white_ribbon", defaultItem()));
	public static final RegistryObject<Item> YELLOW_RIBBON = registerWithTab(REGISTRY.register("yellow_ribbon", defaultItem()));
	public static final RegistryObject<Item> PHANTOM_CROW_FEATHER = registerWithTab(REGISTRY.register("phantom_crow_feather", () -> new PhantomCrowFeatherItem()));
	public static final RegistryObject<Item> CROW_SKULL_MASK_HELMET = registerWithTab(REGISTRY.register("crow_skull_mask_helmet", () -> new CrowSkullMaskItem.Helmet()));

	public static Supplier<Item> defaultItem() {
		return () -> new Item(new Item.Properties());
	}
	
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(SCROLL.get(), new ResourceLocation("messenger_crow:scroll_sealed"), (itemStackToRender, clientWorld, entity, itemEntityId) -> itemStackToRender.getOrCreateTag().getBoolean("sealed") ? 1 : 0);
			ItemProperties.register(SCROLL.get(), new ResourceLocation("messenger_crow:scroll_ribbon"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) getColourIndex(itemStackToRender));
		});
	}

	public static double getColourIndex(ItemStack itemstack) {
		if ((itemstack.getOrCreateTag().getString("ribbon")).equals("black")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A70");
			return 1;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("blue")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A71");
			return 2;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("brown")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A76");
			return 3;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("cyan")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A73");
			return 4;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("gray")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A78");
			return 5;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("green")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A72");
			return 6;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("light_blue")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A79");
			return 7;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("light_gray")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A77");
			return 8;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("lime")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7a");
			return 9;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("magenta")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7d");
			return 10;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("orange")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A76");
			return 11;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("pink")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7d");
			return 12;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("purple")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A75");
			return 13;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("red")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A74");
			return 14;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("white")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7f");
			return 15;
		} else if ((itemstack.getOrCreateTag().getString("ribbon")).equals("yellow")) {
			itemstack.getOrCreateTag().putString("lorecolor", "\u00A7e");
			return 16;
		}
		return 0;
	}
}
