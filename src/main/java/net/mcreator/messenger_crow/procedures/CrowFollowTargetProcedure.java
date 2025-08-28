package net.mcreator.messenger_crow.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;

import net.mcreator.messenger_crow.init.MessengerCrowModParticleTypes;
import net.mcreator.messenger_crow.entity.CrowEntity;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Comparator;
import java.util.ArrayList;

public class CrowFollowTargetProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		Entity crow = null;
		crow = (Entity) world.getEntitiesOfClass(CrowEntity.class, AABB.ofSize(new Vec3(x, y, z), 1, 1, 1), e -> true).stream().sorted(new Object() {
			Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
				return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
			}
		}.compareDistOf(x, y, z)).findFirst().orElse(null);
		for (Entity entityiterator : new ArrayList<>(world.players())) {
			if ((entityiterator.getDisplayName().getString()).equals((new Object() {
				public ItemStack getItemStack(int sltid, Entity entity) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).ifPresent(capability -> {
						_retval.set(capability.getStackInSlot(sltid).copy());
					});
					return _retval.get();
				}
			}.getItemStack(0, crow)).getOrCreateTag().getString("target"))) {
				if (crow.getPersistentData().getBoolean("particle1") == false) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (MessengerCrowModParticleTypes.CROW_TELEPORT.get()), (crow.getX()), (crow.getY()), (crow.getZ()), 10, 1, 1, 1, 1);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(crow.getX(), crow.getY(), crow.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("messenger_crow:crow_poof")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound((crow.getX()), (crow.getY()), (crow.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("messenger_crow:crow_poof")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					crow.getPersistentData().putBoolean("particle1", true);
				}
				{
					Entity _ent = crow;
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
				crow.startRiding(entityiterator);
				if (crow.getPersistentData().getBoolean("particle2") == false) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (MessengerCrowModParticleTypes.CROW_TELEPORT.get()), (crow.getX()), (crow.getY()), (crow.getZ()), 10, 1, 1, 1, 1);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(crow.getX(), crow.getY(), crow.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("messenger_crow:crow_poof")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound((crow.getX()), (crow.getY()), (crow.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("messenger_crow:crow_poof")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					crow.getPersistentData().putBoolean("particle2", true);
					if (!(((CrowEntity) crow).animationprocedure).equals("circle")) {
						if (crow instanceof CrowEntity) {
							((CrowEntity) crow).setAnimation("circle");
						}
					}
				}
			}
		}
		crow.getPersistentData().putBoolean("following", true);
	}
}
