
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.devrune.messenger_crow.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.devrune.messenger_crow.block.entity.ScrollLecternTileEntity;
import net.devrune.messenger_crow.block.entity.ScarecrowTileEntity;
import net.devrune.messenger_crow.MessengerCrowMod;

public class MessengerCrowModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MessengerCrowMod.MODID);
	public static final RegistryObject<BlockEntityType<ScarecrowTileEntity>> SCARECROW = REGISTRY.register("scarecrow", () -> BlockEntityType.Builder.of(ScarecrowTileEntity::new, MessengerCrowModBlocks.SCARECROW.get()).build(null));
	public static final RegistryObject<BlockEntityType<ScrollLecternTileEntity>> SCROLL_LECTERN = REGISTRY.register("scroll_lectern",
			() -> BlockEntityType.Builder.of(ScrollLecternTileEntity::new, MessengerCrowModBlocks.SCROLL_LECTERN.get()).build(null));

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
