package net.devrune.messenger_crow.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.util.Calendar;

public class CrowOnSpawnProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY(), entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("messenger_crow:crow_poof")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound((entity.getX()), (entity.getY()), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("messenger_crow:crow_poof")), SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		entity.getPersistentData().putBoolean("particle1", false);
		entity.getPersistentData().putBoolean("particle2", false);
		entity.getPersistentData().putDouble("scarecrowX", (entity.getX()));
		entity.getPersistentData().putDouble("scarecrowY", (entity.getY() - 2));
		entity.getPersistentData().putDouble("scarecrowZ", (entity.getZ()));
		if (Calendar.getInstance().get(Calendar.MONTH) == 11 && (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 24 || Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 25)) {
			entity.getPersistentData().putString("crowvariant", "christmascrow");
		} else if (Calendar.getInstance().get(Calendar.MONTH) == 9 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 31) {
			entity.getPersistentData().putString("crowvariant", "halloweencrow");
		} else {
			entity.getPersistentData().putString("crowvariant", "crow");
		}
	}
}
