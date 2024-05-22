package ru.tesmio.blocks.decorative.lamp.base;

import ru.tesmio.blocks.baseblock.BlockRotatedAxisCustomModel;
import ru.tesmio.blocks.decorative.devices.IRedstoneDevice;

public class BlockRotLamp extends BlockRotatedAxisCustomModel implements IRedstoneDevice {
    public BlockRotLamp(Properties p) {
        super(p, 1F);
    }


}
