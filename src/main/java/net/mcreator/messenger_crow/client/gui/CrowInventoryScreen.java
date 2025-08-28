package net.mcreator.messenger_crow.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.messenger_crow.world.inventory.CrowInventoryMenu;
import net.mcreator.messenger_crow.procedures.YellowCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.WhiteCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.RedCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.PurpleCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.PinkCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.OrangeCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.MagentaCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.LimeCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.LightGrayCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.LightBlueCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.GreenCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.GrayCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.CyanCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.BrownCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.BlueCrowOverlayProcedure;
import net.mcreator.messenger_crow.procedures.BlackCrowOverlayProcedure;
import net.mcreator.messenger_crow.init.MessengerCrowModScreens.WidgetScreen;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class CrowInventoryScreen extends AbstractContainerScreen<CrowInventoryMenu> implements WidgetScreen {
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
		if (BlackCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/black_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BlueCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/blue_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (BrownCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/brown_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (CyanCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/cyan_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GrayCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/gray_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (GreenCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/green_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (LightBlueCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/light_blue_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (LightGrayCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/light_gray_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (LimeCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/lime_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (MagentaCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/magenta_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (OrangeCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/orange_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (PinkCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/pink_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (PurpleCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/purple_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (RedCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/red_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (WhiteCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/white_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		if (YellowCrowOverlayProcedure.execute(entity)) {
			guiGraphics.blit(new ResourceLocation("messenger_crow:textures/screens/yellow_scroll_overlay.png"), this.leftPos + 0, this.topPos + 0, 0, 0, 176, 166, 176, 166);
		}
		RenderSystem.disableBlend();
	}

	public HashMap<String, Object> getWidgets() {
		return guistate;
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
