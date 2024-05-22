package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.concurrent.ThreadLocalRandom;

public class Cryocapsule extends BlockSideDevice {
    public Cryocapsule(Properties properties, float shadingInside) {
        super(properties, shadingInside);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    final VoxelShape[] BOXS = new VoxelShape[]{
            Block.makeCuboidShape(3,0,1,14.75,16,15),
            Block.makeCuboidShape(3.5,2,0,15.5,14,16),
    };
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch (state.get(FACING)) {
            case EAST:  return VoxelShapes.or(BOXS[0],BOXS[1]);
            case WEST: return VoxelShapes.or(VoxelShapeUtil.shapeRot180(BOXS[0]),VoxelShapeUtil.shapeRot180(BOXS[1]));
            case NORTH: return VoxelShapes.or(VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCW90(BOXS[0])),VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[1])));
            case SOUTH: return VoxelShapes.or(VoxelShapeUtil.shapeRotCCW90(BOXS[0]),VoxelShapeUtil.shapeRotCCW90(BOXS[1]));
        }
        return VoxelShapes.fullCube();
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();
        return new ItemStack[] {
                new ItemStack(RegBlocks.SILVER_CIRCUIT.get(), tr.nextInt(2,4)),
                new ItemStack(RegBlocks.GOLD_CIRCUIT.get(), tr.nextInt(1,3)),
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(2,4)),
        };
    }
    @Override
    public VoxelShape getFacingShape(BlockState s) {
        return super.getFacingShape(s);
    }
}
