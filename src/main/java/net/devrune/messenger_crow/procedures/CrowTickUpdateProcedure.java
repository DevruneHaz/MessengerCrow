package net.devrune.messenger_crow.procedures;

import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.devrune.messenger_crow.init.MessengerCrowModParticleTypes;
import net.devrune.messenger_crow.init.MessengerCrowModItems;
import net.devrune.messenger_crow.init.MessengerCrowModBlocks;
import net.devrune.messenger_crow.entity.CrowEntity;

import java.util.concurrent.atomic.AtomicReference;

public class CrowTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean returnToSender = false;
		boolean particle1 = false;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double phantom = 0;
		if ((new Object() {
			public ItemStack getItemStack(int sltid, Entity entity) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					_retval.set(capability.getStackInSlot(sltid).copy());
				});
				return _retval.get();
			}
		}.getItemStack(0, entity)).getItem() == MessengerCrowModItems.SCROLL.get() && (new Object() {
			public ItemStack getItemStack(int sltid, Entity entity) {
				AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
				entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
					_retval.set(capability.getStackInSlot(sltid).copy());
				});
				return _retval.get();
			}
		}.getItemStack(0, entity)).getOrCreateTag().getBoolean("sealed") == true) {
			{
				Entity _ent = entity;
				_ent.setYRot(0);
				_ent.setXRot(0);
				_ent.setYBodyRot(_ent.getYRot());
				_ent.setYHeadRot(_ent.getYRot());
				_ent.yRotO = _ent.getYRot();
				_ent.xRotO = _ent.getXRot();
				if (_ent instanceof LivingEntity _entity) {
					_entity.yBodyRotO = _entity.getYRot();
					_entity.yHeadRotO = _entity.getYRot();
				}
			}
			if (entity.getPersistentData().getBoolean("following") == false) {
				CrowFollowTargetProcedure.execute(world, x, y, z);
			}
		} else if ((((CrowEntity) entity).animationprocedure).equals("circle")) {
			if (Math.random() < 0.25) {
				CrowDropFeatherProcedure.execute(world, x, y, z);
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (MessengerCrowModParticleTypes.CROW_TELEPORT.get()), (entity.getPersistentData().getDouble("scarecrowX")), (entity.getPersistentData().getDouble("scarecrowY") + 2),
						(entity.getPersistentData().getDouble("scarecrowZ")), 10, 1, 1, 1, 1);
			if (entity.getPersistentData().getBoolean("phantom") == true) {
				phantom = 1;
			} else {
				phantom = 0;
			}
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3((entity.getPersistentData().getDouble("scarecrowX")), (entity.getPersistentData().getDouble("scarecrowY") + 2), (entity.getPersistentData().getDouble("scarecrowZ"))),
								Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						("summon messenger_crow:crow ~ ~ ~ " + "{ForgeData: {scarecrowZ:" + entity.getPersistentData().getDouble("scarecrowZ") + "d, scarecrowY:" + entity.getPersistentData().getDouble("scarecrowY") + "d, scarecrowX:"
								+ entity.getPersistentData().getDouble("scarecrowX") + "d, crowvariant:\"" + entity.getPersistentData().getString("crowvariant") + "\", phantom:" + Math.round(phantom) + "b}}"));
			if (!entity.level().isClientSide())
				entity.discard();
		} else {
			if (entity.isShiftKeyDown()) {
				CrowSitProcedure.execute(world, x, y, z);
			} else {
				CrowMovementProcedure.execute(world, x, y, z);
			}
		}
		if (!((world.getBlockState(BlockPos.containing(entity.getPersistentData().getDouble("scarecrowX"), entity.getPersistentData().getDouble("scarecrowY"), entity.getPersistentData().getDouble("scarecrowZ"))))
				.getBlock() == MessengerCrowModBlocks.SCARECROW.get())) {
			CrowDropFeatherProcedure.execute(world, x, y, z);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (MessengerCrowModParticleTypes.CROW_TELEPORT.get()), (entity.getX()), (entity.getY()), (entity.getZ()), 10, 1, 1, 1, 1);
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}
}
