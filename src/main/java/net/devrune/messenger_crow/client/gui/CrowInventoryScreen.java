package net.devrune.messenger_crow.client.gui;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.devrune.messenger_crow.world.inventory.CrowInventoryMenu;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CrowInventoryScreen extends AbstractContainerScreen<CrowInventoryMenu> {
	private final static HashMap<String, Object> guistate = CrowInventoryMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();

	public CrowInventoryScreen(CrowInventoryMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("messenger_crow:textures/screens/crow_inventory.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		ItemStack stack = entity.containerMenu.getSlot(0).getItem();
		String type = stack == ItemStack.EMPTY ? "" : stack.getOrCreateTag().getString("ribbon");
		ResourceLocation overlayLocation = switch(type) {
			case "black" -> new ResourceLocation("messenger_crow:textures/screens/black_scroll_overlay.png");
			case "blue" -> new ResourceLocation("messenger_crow:textures/screens/blue_scroll_overlay.png");
			case "brown" -> new ResourceLocation("messenger_crow:textures/screens/brown_scroll_overlay.png");
			case "cyan" -> new ResourceLocation("messenger_crow:textures/screens/cyan_scroll_overlay.png");
			case "gray" -> new ResourceLocation("messenger_crow:textures/screens/gray_scroll_overlay.png");
			case "green" -> new ResourceLocation("messenger_crow:textures/screens/green_scroll_overlay.png");
			case "light_blue" -> new ResourceLocation("messenger_crow:textures/screens/light_blue_scroll_overlay.png");
			case "light_gray" -> new ResourceLocation("messenger_crow:textures/screens/light_gray_scroll_overlay.png");
			case "lime" -> new ResourceLocation("messenger_crow:textures/screens/lime_scroll_overlay.png");
			case "magenta" -> new ResourceLocation("messenger_crow:textures/screens/magenta_scroll_overlay.png");
			case "orange" -> new ResourceLocation("messenger_crow:textures/screens/orange_scroll_overlay.png");
			case "pink" -> new ResourceLocation("messenger_crow:textures/screens/pink_scroll_overlay.png");
			case "purple" -> new ResourceLocation("messenger_crow:textures/screens/purple_scroll_overlay.png");
			case "red" -> new ResourceLocation("messenger_crow:textures/screens/red_scroll_overlay.png");
			case "white" -> new ResourceLocation("messenger_crow:textures/screens/white_scroll_overlay.png");
			case "yellow" -> new ResourceLocation("messenger_crow:textures/screens/yellow_scroll_overlay.png");

			default -> null;
		};

		if (overlayLocation != null) {
			guiGraphics.blit(overlayLocation, this.leftPos, this.topPos, 0, 0, 176, 166, 176, 166);
		}

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
	}
}
