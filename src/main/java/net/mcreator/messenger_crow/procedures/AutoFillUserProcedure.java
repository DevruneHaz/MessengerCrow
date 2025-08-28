package net.mcreator.messenger_crow.procedures;

import org.apache.logging.log4j.core.config.Order;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.messenger_crow.MessengerCrowMod;

import java.util.HashMap;
import java.util.ArrayList;

public class AutoFillUserProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		ArrayList<Object> Usernames = new ArrayList<>();
		double Order = 0;
		double MaxNames = 0;
		MessengerCrowMod.LOGGER.info("Began");
		{
			Usernames.clear();
		}
		for (Entity entityiterator : new ArrayList<>(world.players())) {
			{
				Usernames.add((entityiterator.getDisplayName().getString()));
			}
		}
		MaxNames = Usernames.size();
		if (Order >= MaxNames) {
			Order = 0;
		}
		if ((new Object() {
			public String get(ArrayList<?> list, int index) {
				if (list.get(index) instanceof String text) {
					return text;
				}
				return "";
			}
		}.get(Usernames, (int) Order)).equals(guistate.containsKey("textin:Username") ? (String) guistate.get("textin:Username") : "")) {
			Order = Order + 1;
		}
		if (guistate.get("text:Username") instanceof EditBox _tf)
			_tf.setValue((new Object() {
				public String get(ArrayList<?> list, int index) {
					if (list.get(index) instanceof String text) {
						return text;
					}
					return "";
				}
			}.get(Usernames, (int) Order)));
		MessengerCrowMod.LOGGER.info("Success");
	}
}
