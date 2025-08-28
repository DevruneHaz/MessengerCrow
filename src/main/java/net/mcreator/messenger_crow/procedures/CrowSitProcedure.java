package net.mcreator.messenger_crow.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.BlockPos;

import net.mcreator.messenger_crow.entity.CrowEntity;

import java.util.Comparator;

public class CrowSitProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Entity crow = null;
		crow = (Entity) world.getEntitiesOfClass(CrowEntity.class, AABB.ofSize(new Vec3(x, y, z), 2, 2, 2), e -> true).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
		if ((world.getBlockState(BlockPos.containing(crow.getX(), crow.getY() - 1, crow.getZ()))).getBlock() == Blocks.AIR) {
			crow.setDeltaMovement(new Vec3(0, 0, 0));
			crow.push(0, (-0.78), 0);
		} else {
			crow.setDeltaMovement(new Vec3(0, 0, 0));
			{
				Entity _ent = crow;
				_ent.teleportTo((crow.getPersistentData().getDouble("sittingX")), (crow.getPersistentData().getDouble("sittingY")), (crow.getPersistentData().getDouble("sittingZ")));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((crow.getPersistentData().getDouble("sittingX")), (crow.getPersistentData().getDouble("sittingY")), (crow.getPersistentData().getDouble("sittingZ")), _ent.getYRot(), _ent.getXRot());
			}
		}
	}
}
