package net.devrune.messenger_crow.block.display.util;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class DisplayItemClientExtensions implements IClientItemExtensions {
    private final BlockEntityWithoutLevelRenderer renderer;

    public DisplayItemClientExtensions(BlockEntityWithoutLevelRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return renderer;
    }
}
