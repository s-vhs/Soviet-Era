package ru.tesmio.blocks.decorative.props;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import ru.tesmio.blocks.baseblock.BlockRotatedAxisCustomModelInfo;
import ru.tesmio.reg.RegItems;

public class RailingBlock extends BlockRotatedAxisCustomModelInfo {
    public RailingBlock(Properties builder, String info, float sI) {
        super(builder, info, sI);
    }
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(2,4)),
        };
    }
}
