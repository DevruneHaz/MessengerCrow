package net.devrune.messenger_crow.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.devrune.messenger_crow.init.MessengerCrowModBlocks;
import net.devrune.messenger_crow.entity.CrowEntity;

import java.util.Comparator;

public class CrowMovementProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		Entity crow = null;
		crow = (Entity) world.getEntitiesOfClass(CrowEntity.class, AABB.ofSize(new Vec3(x, y, z), 1, 1, 1), e -> true).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
		if ((world.getBlockState(BlockPos.containing(crow.getX(), crow.getY() - 6, crow.getZ()))).getBlock() == Blocks.AIR) {
			if (crow instanceof Mob _entity)
				_entity.getNavigation().moveTo((crow.getX()), (crow.getY() - 3), (crow.getZ()), 1);
		}
		sx = -10;
		found = false;
		for (int index0 = 0; index0 < 20; index0++) {
			sy = -10;
			for (int index1 = 0; index1 < 20; index1++) {
				sz = -10;
				for (int index2 = 0; index2 < 20; index2++) {
					if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).getBlock() == MessengerCrowModBlocks.SCARECROW.get()) {
						found = true;
					}
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
		if (found == false) {
			if (crow instanceof Mob _entity)
				_entity.getNavigation().moveTo((crow.getPersistentData().getDouble("scarecrowX")), (crow.getPersistentData().getDouble("scarecrowY") + 2), (crow.getPersistentData().getDouble("scarecrowZ")), 1);
		}
	}
}
