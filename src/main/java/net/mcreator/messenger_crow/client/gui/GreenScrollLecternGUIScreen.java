package net.mcreator.messenger_crow.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.messenger_crow.world.inventory.GreenScrollLecternGUIMenu;
import net.mcreator.messenger_crow.network.GreenScrollLecternGUIButtonMessage;
import net.mcreator.messenger_crow.MessengerCrowMod;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class GreenScrollLecternGUIScreen extends AbstractContainerScreen<GreenScrollLecternGUIMenu> {
	private final static HashMap<String, Object> guistate = GreenScrollLecternGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox Username;
	public static EditBox Line1;
	public static EditBox Line2;
	public static EditBox Line3;
	Button button_empty;
	Button button_empty1;

	public GreenScrollLecternGUIScreen(GreenScrollLecternGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("messenger_crow:textures/screens/green_scroll_lectern_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		Username.render(guiGraphics, mouseX, mouseY, partialTicks);
		Line1.render(guiGraphics, mouseX, mouseY, partialTicks);
		Line2.render(guiGraphics, mouseX, mouseY, partialTicks);
		Line3.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (Username.isFocused())
			return Username.keyPressed(key, b, c);
		if (Line1.isFocused())
			return Line1.keyPressed(key, b, c);
		if (Line2.isFocused())
			return Line2.keyPressed(key, b, c);
		if (Line3.isFocused())
			return Line3.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		Username.tick();
		Line1.tick();
		Line2.tick();
		Line3.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String UsernameValue = Username.getValue();
		String Line1Value = Line1.getValue();
		String Line2Value = Line2.getValue();
		String Line3Value = Line3.getValue();
		super.resize(minecraft, width, height);
		Username.setValue(UsernameValue);
		Line1.setValue(Line1Value);
		Line2.setValue(Line2Value);
		Line3.setValue(Line3Value);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public void init() {
		super.init();
		Username = new EditBox(this.font, this.leftPos + 59, this.topPos + 10, 88, 18, Component.translatable("gui.messenger_crow.green_scroll_lectern_gui.Username")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.messenger_crow.green_scroll_lectern_gui.Username").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.messenger_crow.green_scroll_lectern_gui.Username").getString());
				else
					setSuggestion(null);
			}
		};
		Username.setSuggestion(Component.translatable("gui.messenger_crow.green_scroll_lectern_gui.Username").getString());
		Username.setMaxLength(16);
		Username.setBordered(false);
		Username.setTextColor(7840025);
		guistate.put("text:Username", Username);
		this.addWidget(this.Username);
		Line1 = new EditBox(this.font, this.leftPos + 12, this.topPos + 31, 160, 18, Component.translatable("gui.messenger_crow.scroll_gui.Line1"));
		Line1.setMaxLength(25);
		Line1.setBordered(false);
		Line1.setTextColor(14270876);
		guistate.put("text:Line1", Line1);
		this.addWidget(this.Line1);
		Line2 = new EditBox(this.font, this.leftPos + 12, this.topPos + 47, 160, 18, Component.translatable("gui.messenger_crow.scroll_gui.Line2"));
		Line2.setMaxLength(25);
		Line2.setBordered(false);
		Line2.setTextColor(14270876);
		guistate.put("text:Line2", Line2);
		this.addWidget(this.Line2);
		Line3 = new EditBox(this.font, this.leftPos + 12, this.topPos + 63, 160, 18, Component.translatable("gui.messenger_crow.scroll_gui.Line3"));
		Line3.setMaxLength(25);
		Line3.setBordered(false);
		Line3.setTextColor(14270876);
		guistate.put("text:Line3", Line3);
		this.addWidget(this.Line3);
		this.setInitialFocus(this.Line1);
		button_empty = new PlainTextButton(this.leftPos + 5, this.topPos + 4, 25, 20, Component.translatable("gui.messenger_crow.green_scroll_lectern_gui.button_empty"), e -> {
			if (true) {
				textstate.put("textin:Username", Username.getValue());
				textstate.put("textin:Line1", Line1.getValue());
				textstate.put("textin:Line2", Line2.getValue());
				textstate.put("textin:Line3", Line3.getValue());
				MessengerCrowMod.PACKET_HANDLER.sendToServer(new GreenScrollLecternGUIButtonMessage(0, x, y, z, textstate));
				GreenScrollLecternGUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}, this.font);
		guistate.put("button:button_empty", button_empty);
		this.addRenderableWidget(button_empty);
		button_empty1 = new PlainTextButton(this.leftPos + 148, this.topPos + 3, 25, 20, Component.translatable("gui.messenger_crow.green_scroll_lectern_gui.button_empty1"), e -> {
			if (true) {
				textstate.put("textin:Username", Username.getValue());
				textstate.put("textin:Line1", Line1.getValue());
				textstate.put("textin:Line2", Line2.getValue());
				textstate.put("textin:Line3", Line3.getValue());
				MessengerCrowMod.PACKET_HANDLER.sendToServer(new GreenScrollLecternGUIButtonMessage(1, x, y, z, textstate));
				GreenScrollLecternGUIButtonMessage.handleButtonAction(entity, 1, x, y, z, textstate);
			}
		}, this.font);
		guistate.put("button:button_empty1", button_empty1);
		this.addRenderableWidget(button_empty1);
	}
}
