package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.utils.VoxelShapeUtil;

public class DSPPanel extends BlockSideDevice {

    public DSPPanel(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return VoxelShapes.or(VoxelShapes.create(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 1),
             VoxelShapes.create(0, 0, 0.96875, 0.0625, 1, 1),
            VoxelShapes.create(0.9375, 0, 0.96875, 1, 1, 1),
           VoxelShapes.create(0.0625, 0.9375, 0.96875, 0.9375, 1, 1),
            VoxelShapes.create(0.0625, 0, 0.96875, 0.9375, 0.0625, 1));

            case SOUTH:
                return VoxelShapes.or(VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 1)),
                        VoxelShapeUtil.shapeRot180(VoxelShapes.create(0, 0, 0.96875, 0.0625, 1, 1)),
                                VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.9375, 0, 0.96875, 1, 1, 1)),
                                        VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.0625, 0.9375, 0.96875, 0.9375, 1, 1)),
                                                VoxelShapeUtil.shapeRot180(VoxelShapes.create(0.0625, 0, 0.96875, 0.9375, 0.0625, 1)));
            case WEST:
                return VoxelShapes.or(VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 1)),
                        VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0, 0, 0.96875, 0.0625, 1, 1)),
                        VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.9375, 0, 0.96875, 1, 1, 1)),
                        VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.0625, 0.9375, 0.96875, 0.9375, 1, 1)),
                        VoxelShapeUtil.shapeRotCW90(VoxelShapes.create(0.0625, 0, 0.96875, 0.9375, 0.0625, 1)));

            case EAST:
                return VoxelShapes.or(VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 1)),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0, 0, 0.96875, 0.0625, 1, 1)),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.9375, 0, 0.96875, 1, 1, 1)),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.0625, 0.9375, 0.96875, 0.9375, 1, 1)),
                        VoxelShapeUtil.shapeRotCCW90(VoxelShapes.create(0.0625, 0, 0.96875, 0.9375, 0.0625, 1)));

        }
        return VoxelShapes.fullCube();
    }
}


