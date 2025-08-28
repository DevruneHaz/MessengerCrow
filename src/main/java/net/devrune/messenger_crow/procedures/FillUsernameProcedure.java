package net.devrune.messenger_crow.procedures;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

import java.util.ArrayList;
import java.util.HashMap;

public class FillUsernameProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		double order = 0;
		double maxnames = 0;
		ArrayList<Object> Usernames = new ArrayList<>();
		{
			Usernames.clear();
		}
		for (Entity entityiterator : new ArrayList<>(world.players())) {
			{
				Usernames.add((entityiterator.getDisplayName().getString()));
			}
		}
		for (int index0 = 0; index0 < (int) Usernames.size(); index0++) {
			if ((new Object() {
				public String get(ArrayList<?> list, int index) {
					if (list.get(index) instanceof String text) {
						return text;
					}
					return "";
				}
			}.get(Usernames, (int) order)).equals(guistate.containsKey("textin:Username") ? (String) guistate.get("textin:Username") : "")) {
				break;
			}
			order = order + 1;
		}
		order = order + 1;
		if (order >= Usernames.size()) {
			order = 0;
		}
		if (guistate.get("text:Username") instanceof EditBox _tf)
			_tf.setValue((new Object() {
				public String get(ArrayList<?> list, int index) {
					if (list.get(index) instanceof String text) {
						return text;
					}
					return "";
				}
			}.get(Usernames, (int) order)));
	}
}