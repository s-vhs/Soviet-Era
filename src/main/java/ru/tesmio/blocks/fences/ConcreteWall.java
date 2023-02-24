package ru.tesmio.blocks.fences;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

public class ConcreteWall extends BlockSideCustomModel {
    final VoxelShape BOXS[] = new VoxelShape[] {Block.makeCuboidShape(0D, 0D, 6D, 16D, 16D, 10D),
            Block.makeCuboidShape(0D, 0D, 6D, 16D, 16D, 10D),
            Block.makeCuboidShape(6D, 0D, 0D, 10D, 16D, 16D),
            Block.makeCuboidShape(6D, 0D, 0D, 10D, 16D, 16D)};
    public ConcreteWall(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape BOXS[] = new VoxelShape[] {Block.makeCuboidShape(0D, 0D, 6D, 16D, 16D, 10D),
                Block.makeCuboidShape(0D, 0D, 6D, 16D, 16D, 10D),
                Block.makeCuboidShape(6D, 0D, 0D, 10D, 16D, 16D),
                Block.makeCuboidShape(6D, 0D, 0D, 10D, 16D, 16D)};
        switch (state.get(FACING)) {
            case NORTH:
                return BOXS[0];
            case SOUTH:
                return BOXS[1];
            case WEST:
                return BOXS[2];
            case EAST:
                return BOXS[3];
        }
        return VoxelShapes.fullCube();
    }
}
