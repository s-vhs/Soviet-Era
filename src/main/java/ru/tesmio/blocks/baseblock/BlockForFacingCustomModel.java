package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.utils.VoxelShapeUtil;

public class BlockForFacingCustomModel extends BlockForFacing implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private float shadingInside;
    public static VoxelShape FACING_SHAPE = VoxelShapes.fullCube();
    public BlockForFacingCustomModel(Properties properties, float shadingInside) {
        super(properties);
        this.shadingInside = shadingInside;
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
    }


    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return  stateIn;
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (s.get(FACING)) {
            case NORTH:
                return getFacingShape(s);
            case SOUTH:
                return VoxelShapeUtil.shapeRot180(getFacingShape(s));
            case EAST:
                return VoxelShapeUtil.shapeRotCCW90(getFacingShape(s));
            case WEST:
                return VoxelShapeUtil.shapeRotCW90(getFacingShape(s));
            case UP:
                return VoxelShapeUtil.shapeRotCWX90(getFacingShape(s));
            case DOWN:
                return VoxelShapeUtil.shapeRotCCWX90(getFacingShape(s));
        }
        return VoxelShapes.fullCube();
    }

    public VoxelShape getFacingShape(BlockState s) {
        return this.FACING_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (s.get(FACING)) {
            case NORTH:
                return getFacingShape(s);
            case SOUTH:
                return VoxelShapeUtil.shapeRot180(getFacingShape(s));
            case EAST:
                return VoxelShapeUtil.shapeRotCCW90(getFacingShape(s));
            case WEST:
                return VoxelShapeUtil.shapeRotCW90(getFacingShape(s));
        }
        return VoxelShapes.fullCube();
    }


    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1F;
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
