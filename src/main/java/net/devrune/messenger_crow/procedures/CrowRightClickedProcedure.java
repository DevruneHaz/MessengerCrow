package net.devrune.messenger_crow.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class CrowRightClickedProcedure {
	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity.isShiftKeyDown()) {
			if (!entity.isShiftKeyDown()) {
				entity.setShiftKeyDown(true);
				entity.getPersistentData().putDouble("sittingX", (entity.getX()));
				entity.getPersistentData().putDouble("sittingY", Math.floor(entity.getY()));
				entity.getPersistentData().putDouble("sittingZ", (entity.getZ()));
				while ((world.getBlockState(BlockPos.containing(entity.getX(), entity.getPersistentData().getDouble("sittingY") - 1, entity.getZ()))).getBlock() == Blocks.AIR) {
					entity.getPersistentData().putDouble("sittingY", (entity.getPersistentData().getDouble("sittingY") - 1));
				}
			} else if (entity.isShiftKeyDown()) {
				entity.setShiftKeyDown(false);
			}
		}
		entity.getPersistentData().putString("holliday", "christmas");
	}
}
