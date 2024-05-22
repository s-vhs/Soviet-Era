package ru.tesmio.blocks.decorative.devices.base;

import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.blocks.decorative.devices.IRedstoneDevice;

public class BlockSideDevice extends BlockSideCustomModel implements IRedstoneDevice {

    public BlockSideDevice(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockSideDevice(float shadingInside) {
        super(shadingInside);
    }

    public boolean isCustomDrop() {
        return true;
    }

}
