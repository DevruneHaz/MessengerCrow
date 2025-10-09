package net.devrune.messenger_crow.block;

import net.devrune.messenger_crow.MessengerCrowMod;
import net.devrune.messenger_crow.init.MessengerCrowModEntities;
import net.devrune.messenger_crow.init.MessengerCrowModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelAccessor;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.devrune.messenger_crow.init.MessengerCrowModBlockEntities;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Collections;

public class ScarecrowBlock extends BaseEntityBlock implements EntityBlock {
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	public static final IntegerProperty ANIMATION = IntegerProperty.create("animation", 0, 3);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public ScarecrowBlock() {
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.WOOD).strength(2f).lightLevel(s -> s.getValue(LIT) ? 6 : 0)
				.noOcclusion()
				.isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
	}

	@Override
	public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
		return MessengerCrowModBlockEntities.SCARECROW.get().create(blockPos, blockState);
	}

	@Override
	public boolean propagatesSkylightDown(@NotNull BlockState state, @NotNull BlockGetter reader, @NotNull BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
		return 0;
	}

	@Override
	public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {

		return switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(box(6, 0, 6, 10, 12, 10), box(4, 12, 6, 12, 23, 10), box(-8, 19, 6, 4, 23, 10), box(12, 19, 6, 24, 23, 10), box(5, 23, 6, 11, 29, 10));
			case EAST -> Shapes.or(box(6, 0, 6, 10, 12, 10), box(6, 12, 4, 10, 23, 12), box(6, 19, -8, 10, 23, 4), box(6, 19, 12, 10, 23, 24), box(6, 23, 5, 10, 29, 11));
			case WEST -> Shapes.or(box(6, 0, 6, 10, 12, 10), box(6, 12, 4, 10, 23, 12), box(6, 19, 12, 10, 23, 24), box(6, 19, -8, 10, 23, 4), box(6, 23, 5, 10, 29, 11));
            default -> Shapes.or(box(6, 0, 6, 10, 12, 10), box(4, 12, 6, 12, 23, 10), box(12, 19, 6, 24, 23, 10), box(-8, 19, 6, 4, 23, 10), box(5, 23, 6, 11, 29, 10));
        };
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ANIMATION, FACING, LIT);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public List<ItemStack> getDrops(@NotNull BlockState state, LootParams.@NotNull Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Override
	public InteractionResult use(@NotNull BlockState blockstate, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player entity, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		super.use(blockstate, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		handleLighting(world, x, y, z, blockstate, entity);
		return InteractionResult.SUCCESS;
	}

	public static void handleLighting(LevelAccessor world, double x, double y, double z, BlockState blockstate, Player player) {
		if (player.getMainHandItem().getItem() != Items.FLINT_AND_STEEL) {
			return;
		}

		PlayerInfo pInfo =  Minecraft.getInstance().getConnection().getPlayerInfo(player.getGameProfile().getId());
		if (pInfo != null && pInfo.getGameMode() == GameType.SURVIVAL) {
			ItemStack _ist = player.getMainHandItem();
			if (_ist.hurt(1, RandomSource.create(), null)) {
				_ist.shrink(1);
				_ist.setDamageValue(0);
			}
		}

		if (!blockstate.getValue(LIT)) {
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			world.setBlock(_pos, _bs.setValue(LIT, true).setValue(ANIMATION, 1), Block.UPDATE_ALL);

			MessengerCrowMod.queueServerWork(20, () -> {
				world.setBlock(_pos, world.getBlockState(_pos).setValue(ANIMATION, 3), Block.UPDATE_ALL);
			});

			MessengerCrowMod.queueServerWork(60, () -> {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = MessengerCrowModEntities.CROW.get().spawn(_level, BlockPos.containing(x, y + 2, z), MobSpawnType.MOB_SUMMONED);
					if (entityToSpawn != null) {
						entityToSpawn.setDeltaMovement(0, 0, 0);
						_level.sendParticles((MessengerCrowModParticleTypes.CROW_TELEPORT.get()), x, (y + 2), z, 10, 1, 1, 1, 1);
					}
				}
			});

			MessengerCrowMod.queueServerWork(100, () -> {
				world.setBlock(_pos, world.getBlockState(_pos).setValue(LIT, false).setValue(ANIMATION, 2), Block.UPDATE_ALL);
			});
		}
	}
}
