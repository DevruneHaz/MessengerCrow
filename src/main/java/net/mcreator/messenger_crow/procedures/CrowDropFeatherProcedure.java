package net.mcreator.messenger_crow.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.messenger_crow.init.MessengerCrowModItems;
import net.mcreator.messenger_crow.entity.CrowEntity;

import java.util.Comparator;

public class CrowDropFeatherProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Entity crow = null;
		crow = (Entity) world.getEntitiesOfClass(CrowEntity.class, AABB.ofSize(new Vec3(x, y, z), 2, 2, 2), e -> true).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
		if (crow.getPersistentData().getBoolean("phantom") == true) {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, (crow.getX()), (crow.getY()), (crow.getZ()), new ItemStack(MessengerCrowModItems.PHANTOM_CROW_FEATHER.get()));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
		} else {
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn = new ItemEntity(_level, (crow.getX()), (crow.getY()), (crow.getZ()), new ItemStack(MessengerCrowModItems.CROW_FEATHER.get()));
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
		}
	}
}
