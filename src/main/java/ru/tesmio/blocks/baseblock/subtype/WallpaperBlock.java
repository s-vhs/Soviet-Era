package ru.tesmio.blocks.baseblock.subtype;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import ru.tesmio.blocks.baseblock.BaseBlock;

public class WallpaperBlock extends BaseBlock {
    public WallpaperBlock() {
        super(AbstractBlock.Properties.create(Material.PLANTS)
                .setRequiresTool()
                .hardnessAndResistance(1.5f,2f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(1)
                .sound(SoundType.PLANT));
    }
}
