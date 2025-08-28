
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.messenger_crow.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.messenger_crow.block.ScrollLecternBlock;
import net.mcreator.messenger_crow.block.ScarecrowBlock;
import net.mcreator.messenger_crow.MessengerCrowMod;

public class MessengerCrowModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, MessengerCrowMod.MODID);
	public static final RegistryObject<Block> SCARECROW = REGISTRY.register("scarecrow", () -> new ScarecrowBlock());
	public static final RegistryObject<Block> SCROLL_LECTERN = REGISTRY.register("scroll_lectern", () -> new ScrollLecternBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
