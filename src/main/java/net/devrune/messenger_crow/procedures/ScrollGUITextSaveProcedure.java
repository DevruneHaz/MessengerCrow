package net.devrune.messenger_crow.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

public class ScrollGUITextSaveProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putString("target", (guistate.containsKey("text:Username") ? ((EditBox) guistate.get("text:Username")).getValue() : ""));
		(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putString("sender", (entity.getDisplayName().getString()));
		(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putString("line1", (guistate.containsKey("text:Line1") ? ((EditBox) guistate.get("text:Line1")).getValue() : ""));
		(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putString("line2", (guistate.containsKey("text:Line2") ? ((EditBox) guistate.get("text:Line2")).getValue() : ""));
		(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putString("line3", (guistate.containsKey("text:Line3") ? ((EditBox) guistate.get("text:Line3")).getValue() : ""));
	}
}
