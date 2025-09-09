
package net.devrune.messenger_crow.entity;

import net.devrune.messenger_crow.init.MessengerCrowModBlocks;
import net.devrune.messenger_crow.init.MessengerCrowModParticleTypes;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.items.wrapper.EntityHandsInvWrapper;
import net.minecraftforge.items.wrapper.EntityArmorInvWrapper;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.devrune.messenger_crow.world.inventory.CrowInventoryMenu;
import net.devrune.messenger_crow.init.MessengerCrowModItems;
import net.devrune.messenger_crow.init.MessengerCrowModEntities;

import javax.annotation.Nullable;
import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.netty.buffer.Unpooled;

@Mod.EventBusSubscriber
public class CrowEntity extends TamableAnimal implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(CrowEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(CrowEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(CrowEntity.class, EntityDataSerializers.STRING);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public CrowEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(MessengerCrowModEntities.CROW.get(), world);
	}

	public CrowEntity(EntityType<CrowEntity> type, Level world) {
		super(type, world);
		xpReward = 3;
		setNoAi(false);
		setMaxUpStep(0.6f);
		setPersistenceRequired();
		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "crow");
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new TemptGoal(this, 1.3, Ingredient.of(Items.BEETROOT), false));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8));
		this.goalSelector.addGoal(4, new RemoveBlockGoal(Blocks.BEETROOTS, this, 2, (int) 6));
		this.goalSelector.addGoal(5, new FloatGoal(this));
		this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1, 20) {
			@Override
			protected Vec3 getPosition() {
				RandomSource random = CrowEntity.this.getRandom();
				double dir_x = CrowEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = CrowEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = CrowEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
				return new Vec3(dir_x, dir_y, dir_z);
			}
		});
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public double getPassengersRidingOffset() {
		return super.getPassengersRidingOffset() + -0.3;
	}

	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		this.spawnAtLocation(new ItemStack(MessengerCrowModItems.CROW_FEATHER.get()));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
	}

	@Override
	public void thunderHit(ServerLevel serverWorld, LightningBolt lightningBolt) {
		super.thunderHit(serverWorld, lightningBolt);
		handleLightningStrike(this);
	}

	@Override
	public boolean causeFallDamage(float l, float d, DamageSource source) {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.IN_FIRE))
			return false;
		if (source.getDirectEntity() instanceof AbstractArrow)
			return false;
		if (source.getDirectEntity() instanceof Player)
			return false;
		if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
			return false;
		if (source.is(DamageTypes.FALL))
			return false;
		if (source.is(DamageTypes.CACTUS))
			return false;
		if (source.is(DamageTypes.DROWN))
			return false;
		if (source.is(DamageTypes.LIGHTNING_BOLT))
			return false;
		if (source.is(DamageTypes.EXPLOSION))
			return false;
		if (source.is(DamageTypes.TRIDENT))
			return false;
		if (source.is(DamageTypes.FALLING_ANVIL))
			return false;
		if (source.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (source.is(DamageTypes.WITHER))
			return false;
		if (source.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		handleCrowSpawn(world, this);
		return retval;
	}

	public static void handleCrowSpawn(LevelAccessor world, Entity entity) {
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

	private final ItemStackHandler inventory = new ItemStackHandler(1) {
		@Override
		public int getSlotLimit(int slot) {
			return 1;
		}
	};
	private final CombinedInvWrapper combined = new CombinedInvWrapper(inventory, new EntityHandsInvWrapper(this), new EntityArmorInvWrapper(this));

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction side) {
		if (this.isAlive() && capability == ForgeCapabilities.ITEM_HANDLER && side == null)
			return LazyOptional.of(() -> combined).cast();
		return super.getCapability(capability, side);
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		for (int i = 0; i < inventory.getSlots(); ++i) {
			ItemStack itemstack = inventory.getStackInSlot(i);
			if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
				this.spawnAtLocation(itemstack);
			}
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.put("InventoryCustom", inventory.serializeNBT());
		compound.putString("Texture", this.getTexture());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		Tag inventoryCustom = compound.get("InventoryCustom");
		if (inventoryCustom instanceof CompoundTag inventoryTag)
			inventory.deserializeNBT(inventoryTag);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());
		if (sourceentity instanceof ServerPlayer serverPlayer) {
			NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
				@Override
				public Component getDisplayName() {
					return Component.literal("Crow");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
					FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
					packetBuffer.writeBlockPos(sourceentity.blockPosition());
					packetBuffer.writeByte(0);
					packetBuffer.writeVarInt(CrowEntity.this.getId());
					return new CrowInventoryMenu(id, inventory, packetBuffer);
				}
			}, buf -> {
				buf.writeBlockPos(sourceentity.blockPosition());
				buf.writeByte(0);
				buf.writeVarInt(this.getId());
			});
		}
		Item item = itemstack.getItem();
		if (itemstack.getItem() instanceof SpawnEggItem) {
			retval = super.mobInteract(sourceentity, hand);
		} else if (this.level().isClientSide()) {
			retval = (this.isTame() && this.isOwnedBy(sourceentity) || this.isFood(itemstack)) ? InteractionResult.sidedSuccess(this.level().isClientSide()) : InteractionResult.PASS;
		} else {
			if (this.isTame()) {
				if (this.isOwnedBy(sourceentity)) {
					if (item.isEdible() && this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
						this.usePlayerItem(sourceentity, hand, itemstack);
						this.heal((float) item.getFoodProperties().getNutrition());
						retval = InteractionResult.sidedSuccess(this.level().isClientSide());
					} else if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
						this.usePlayerItem(sourceentity, hand, itemstack);
						this.heal(4);
						retval = InteractionResult.sidedSuccess(this.level().isClientSide());
					} else {
						retval = super.mobInteract(sourceentity, hand);
					}
				}
			} else if (this.isFood(itemstack)) {
				this.usePlayerItem(sourceentity, hand, itemstack);
				if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, sourceentity)) {
					this.tame(sourceentity);
					this.level().broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level().broadcastEntityEvent(this, (byte) 6);
				}
				this.setPersistenceRequired();
				retval = InteractionResult.sidedSuccess(this.level().isClientSide());
			} else {
				retval = super.mobInteract(sourceentity, hand);
				if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME)
					this.setPersistenceRequired();
			}
		}
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level();

		handleRightClick(world, entity, sourceentity);
		return retval;
	}

	public static void handleRightClick(LevelAccessor world, Entity entity, Entity sourceentity) {
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

	@Override
	public void baseTick() {
		super.baseTick();
		update(this.level(), this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	public static void update(LevelAccessor world, double x, double y, double z, Entity entity) {
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
				followTarget(world, x, y, z);
			}
		} else if ((((CrowEntity) entity).animationprocedure).equals("circle")) {
			if (Math.random() < 0.25) {
				dropFeather(world, x, y, z);
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
				sitCrow(world, x, y, z);
			} else {
				doMovement(world, x, y, z);
			}
		}
		if (!((world.getBlockState(BlockPos.containing(entity.getPersistentData().getDouble("scarecrowX"), entity.getPersistentData().getDouble("scarecrowY"), entity.getPersistentData().getDouble("scarecrowZ"))))
				.getBlock() == MessengerCrowModBlocks.SCARECROW.get())) {
			dropFeather(world, x, y, z);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (MessengerCrowModParticleTypes.CROW_TELEPORT.get()), (entity.getX()), (entity.getY()), (entity.getZ()), 10, 1, 1, 1, 1);
			if (!entity.level().isClientSide())
				entity.discard();
		}
	}

	public static void followTarget(LevelAccessor world, double x, double y, double z) {
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

	public static void doMovement(LevelAccessor world, double x, double y, double z) {
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

	public static void dropFeather(LevelAccessor world, double x, double y, double z) {
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

	public static void sitCrow(LevelAccessor world, double x, double y, double z) {
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

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		return super.getDimensions(p_33597_).scale((float) 1.1);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		CrowEntity retval = MessengerCrowModEntities.CROW.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		return retval;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return List.of(Items.BEETROOT).contains(stack.getItem());
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	@Override
	public void setNoGravity(boolean ignored) {
		super.setNoGravity(true);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.updateSwingTime();
		this.setNoGravity(true);
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 1);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.FLYING_SPEED, 0.2);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F)) && this.onGround() && !this.isSprinting()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("sprint"));
			}
			if (this.isDeadOrDying()) {
				return event.setAndContinue(RawAnimation.begin().thenPlay(""));
			}
			if (this.isShiftKeyDown()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("sneak"));
			}
			if (this.isSprinting()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("sprint"));
			}
			if (!this.onGround()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("fly"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}
		return PlayState.STOP;
	}

	String prevAnim = "empty";

	private PlayState procedurePredicate(AnimationState event) {
		if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED || (!this.animationprocedure.equals(prevAnim) && !this.animationprocedure.equals("empty"))) {
			if (!this.animationprocedure.equals(prevAnim))
				event.getController().forceAnimationReset();
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				this.animationprocedure = "empty";
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("empty")) {
			prevAnim = "empty";
			return PlayState.STOP;
		}
		prevAnim = this.animationprocedure;
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(CrowEntity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	public static void handleLightningStrike(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putString("crowvariant", "phantom");
		entity.getPersistentData().putBoolean("phantom", true);
		entity.clearFire();
		if (entity instanceof LivingEntity _entity)
			_entity.setHealth(entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1);
	}

	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingTickEvent event) {
		Entity entity = event.getEntity();
		if (entity == null)
			return;
		if (entity instanceof CrowEntity) {
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
				if ((entity.getPersistentData().getString("crowvariant")).equals("crow")) {
					entity.getPersistentData().putString("crowvariant", "messengercrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("phantom")) {
					entity.getPersistentData().putString("crowvariant", "messengerphantom");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmascrow")) {
					entity.getPersistentData().putString("crowvariant", "christmasmessengercrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasphantom")) {
					entity.getPersistentData().putString("crowvariant", "christmasmessengerphantom");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweencrow")) {
					entity.getPersistentData().putString("crowvariant", "halloweenmessengercrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenphantom")) {
					entity.getPersistentData().putString("crowvariant", "halloweenmessengerphantom");
				}
			} else {
				if ((entity.getPersistentData().getString("crowvariant")).equals("messengercrow")) {
					entity.getPersistentData().putString("crowvariant", "crow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("messengerphantom")) {
					entity.getPersistentData().putString("crowvariant", "phantom");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasmessengercrow")) {
					entity.getPersistentData().putString("crowvariant", "christmascrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasmessengerphantom")) {
					entity.getPersistentData().putString("crowvariant", "christmasphantom");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenmessengercrow")) {
					entity.getPersistentData().putString("crowvariant", "halloweencrow");
				} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenmessengerphantom")) {
					entity.getPersistentData().putString("crowvariant", "halloweenphantom");
				}
			}
			if ((entity.getPersistentData().getString("crowvariant")).equals("crow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("phantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("messengercrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("messenger_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("messengerphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("messenger_phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmascrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("christmas_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("christmas_phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasmessengercrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("christmas_messenger_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("christmasmessengerphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("christmas_messenger_phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweencrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("halloween_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("halloween_phantom_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenmessengercrow")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("halloween_messenger_crow");
			} else if ((entity.getPersistentData().getString("crowvariant")).equals("halloweenmessengerphantom")) {
				if (entity instanceof CrowEntity animatable)
					animatable.setTexture("halloween_messenger_phantom_crow");
			}
		}
	}
}
