
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.messenger_crow.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.item.ItemProperties;

import net.mcreator.messenger_crow.procedures.ScrollSealedTextureProcedure;
import net.mcreator.messenger_crow.procedures.ScrollRibbonTextureProcedure;
import net.mcreator.messenger_crow.item.YellowRibbonItem;
import net.mcreator.messenger_crow.item.WhiteRibbonItem;
import net.mcreator.messenger_crow.item.ScrollItem;
import net.mcreator.messenger_crow.item.RedRibbonItem;
import net.mcreator.messenger_crow.item.PurpleRibbonItem;
import net.mcreator.messenger_crow.item.PinkRibbonItem;
import net.mcreator.messenger_crow.item.PhantomCrowFeatherItem;
import net.mcreator.messenger_crow.item.OrangeRibbonItem;
import net.mcreator.messenger_crow.item.MagentaRibbonItem;
import net.mcreator.messenger_crow.item.LimeRibbonItem;
import net.mcreator.messenger_crow.item.LightGrayRibbonItem;
import net.mcreator.messenger_crow.item.LightBlueRibbonItem;
import net.mcreator.messenger_crow.item.GreenRibbonItem;
import net.mcreator.messenger_crow.item.GrayRibbonItem;
import net.mcreator.messenger_crow.item.FeatherFanItem;
import net.mcreator.messenger_crow.item.CyanRibbonItem;
import net.mcreator.messenger_crow.item.CrowSkullMaskItem;
import net.mcreator.messenger_crow.item.CrowFeatherItem;
import net.mcreator.messenger_crow.item.BrownRibbonItem;
import net.mcreator.messenger_crow.item.BlueRibbonItem;
import net.mcreator.messenger_crow.item.BlackRibbonItem;
import net.mcreator.messenger_crow.block.display.ScrollLecternDisplayItem;
import net.mcreator.messenger_crow.block.display.ScarecrowDisplayItem;
import net.mcreator.messenger_crow.MessengerCrowMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MessengerCrowModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, MessengerCrowMod.MODID);
	public static final RegistryObject<Item> SCROLL = REGISTRY.register("scroll", () -> new ScrollItem());
	public static final RegistryObject<Item> CROW_FEATHER = REGISTRY.register("crow_feather", () -> new CrowFeatherItem());
	public static final RegistryObject<Item> SCARECROW = REGISTRY.register(MessengerCrowModBlocks.SCARECROW.getId().getPath(), () -> new ScarecrowDisplayItem(MessengerCrowModBlocks.SCARECROW.get(), new Item.Properties()));
	public static final RegistryObject<Item> FEATHER_FAN = REGISTRY.register("feather_fan", () -> new FeatherFanItem());
	public static final RegistryObject<Item> BLACK_RIBBON = REGISTRY.register("black_ribbon", () -> new BlackRibbonItem());
	public static final RegistryObject<Item> BLUE_RIBBON = REGISTRY.register("blue_ribbon", () -> new BlueRibbonItem());
	public static final RegistryObject<Item> BROWN_RIBBON = REGISTRY.register("brown_ribbon", () -> new BrownRibbonItem());
	public static final RegistryObject<Item> CYAN_RIBBON = REGISTRY.register("cyan_ribbon", () -> new CyanRibbonItem());
	public static final RegistryObject<Item> GRAY_RIBBON = REGISTRY.register("gray_ribbon", () -> new GrayRibbonItem());
	public static final RegistryObject<Item> GREEN_RIBBON = REGISTRY.register("green_ribbon", () -> new GreenRibbonItem());
	public static final RegistryObject<Item> LIGHT_BLUE_RIBBON = REGISTRY.register("light_blue_ribbon", () -> new LightBlueRibbonItem());
	public static final RegistryObject<Item> LIGHT_GRAY_RIBBON = REGISTRY.register("light_gray_ribbon", () -> new LightGrayRibbonItem());
	public static final RegistryObject<Item> LIME_RIBBON = REGISTRY.register("lime_ribbon", () -> new LimeRibbonItem());
	public static final RegistryObject<Item> MAGENTA_RIBBON = REGISTRY.register("magenta_ribbon", () -> new MagentaRibbonItem());
	public static final RegistryObject<Item> ORANGE_RIBBON = REGISTRY.register("orange_ribbon", () -> new OrangeRibbonItem());
	public static final RegistryObject<Item> PINK_RIBBON = REGISTRY.register("pink_ribbon", () -> new PinkRibbonItem());
	public static final RegistryObject<Item> PURPLE_RIBBON = REGISTRY.register("purple_ribbon", () -> new PurpleRibbonItem());
	public static final RegistryObject<Item> RED_RIBBON = REGISTRY.register("red_ribbon", () -> new RedRibbonItem());
	public static final RegistryObject<Item> WHITE_RIBBON = REGISTRY.register("white_ribbon", () -> new WhiteRibbonItem());
	public static final RegistryObject<Item> YELLOW_RIBBON = REGISTRY.register("yellow_ribbon", () -> new YellowRibbonItem());
	public static final RegistryObject<Item> SCROLL_LECTERN = REGISTRY.register(MessengerCrowModBlocks.SCROLL_LECTERN.getId().getPath(), () -> new ScrollLecternDisplayItem(MessengerCrowModBlocks.SCROLL_LECTERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> PHANTOM_CROW_FEATHER = REGISTRY.register("phantom_crow_feather", () -> new PhantomCrowFeatherItem());
	public static final RegistryObject<Item> CROW_SKULL_MASK_HELMET = REGISTRY.register("crow_skull_mask_helmet", () -> new CrowSkullMaskItem.Helmet());
	public static final RegistryObject<Item> SCROLL_SHELF = block(MessengerCrowModBlocks.SCROLL_SHELF);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}

	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemProperties.register(SCROLL.get(), new ResourceLocation("messenger_crow:scroll_sealed"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) ScrollSealedTextureProcedure.execute(itemStackToRender));
			ItemProperties.register(SCROLL.get(), new ResourceLocation("messenger_crow:scroll_ribbon"), (itemStackToRender, clientWorld, entity, itemEntityId) -> (float) ScrollRibbonTextureProcedure.execute(itemStackToRender));
		});
	}
}
