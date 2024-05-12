package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;

public class VentPipeFilter extends BlockSideCustomModel {
    public VentPipeFilter(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
            //    new ItemStack(RegItems.CERAMIC_SHARD.get(), tr.nextInt(1,3))
        };
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
            case WEST:
            case SOUTH:
            case NORTH:
                return VoxelShapes.or(
                        Block.makeCuboidShape(0,0,0,16,2,16),
                        Block.makeCuboidShape(1,2,2,15,14,14),
                        Block.makeCuboidShape(2,2,1,14,14,15),
                        Block.makeCuboidShape(0,14,0,16,16,16)

                );
        }
        return VoxelShapes.fullCube();
    }

}
