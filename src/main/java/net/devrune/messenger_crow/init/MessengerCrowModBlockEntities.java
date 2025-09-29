package net.devrune.messenger_crow.init;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.devrune.messenger_crow.block.entity.ScrollLecternBlockEntity;
import net.devrune.messenger_crow.block.entity.ScarecrowBlockEntity;
import net.devrune.messenger_crow.MessengerCrowMod;

public class MessengerCrowModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MessengerCrowMod.MODID);

	public static final RegistryObject<BlockEntityType<ScarecrowBlockEntity>> SCARECROW = register("scarecrow",
			MessengerCrowModBlocks.SCARECROW, ScarecrowBlockEntity::new);
	public static final RegistryObject<BlockEntityType<ScrollLecternBlockEntity>> SCROLL_LECTERN = register("scroll_lectern",
			MessengerCrowModBlocks.SCROLL_LECTERN, ScrollLecternBlockEntity::new);


	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
