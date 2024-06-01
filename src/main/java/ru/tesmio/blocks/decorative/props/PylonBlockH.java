package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockAxisCustomModel;
import ru.tesmio.reg.RegItems;

public class PylonBlockH extends BlockAxisCustomModel {

    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    public PylonBlockH(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X).with(DOWN, Boolean.FALSE).with(UP, Boolean.FALSE).with(WEST, Boolean.FALSE).with(EAST, Boolean.FALSE).with(NORTH, Boolean.FALSE).with(SOUTH, Boolean.FALSE));
    }
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.fullCube();
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(1,3)),
        };
    }
    public BlockState getStateForPlacement(BlockItemUseContext c) {
        Direction facing = Direction.fromAngle(c.getPlayer().getRotationYawHead());
        Direction.Axis axis = facing.getAxis();
        FluidState fluidstate = c.getWorld().getFluidState(c.getPos());
        return this.getDefaultState().with(AXIS, axis).with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        return updateState((World) w,p,s);
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        updateState((World) w,p,s);
    }
    @Override
    public void onBlockAdded(BlockState s, World w, BlockPos p, BlockState oldState, boolean isMoving) {
        this.updateState((World) w,p,s);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        BlockState ts = w.getBlockState(p);
        BlockState up = w.getBlockState(p.up());
        BlockState down = w.getBlockState(p.down());
        BlockState south = w.getBlockState(p.south());
        BlockState north = w.getBlockState(p.north());
        BlockState west = w.getBlockState(p.west());
        BlockState east = w.getBlockState(p.east());
        if (!w.isRemote()) {
            if (down.getBlock() instanceof PylonBlock) {
                ts = ts.with(DOWN, Boolean.TRUE);
            } else {
                ts = ts.with(DOWN, Boolean.FALSE);
            }
            if (up.getBlock() instanceof PylonBlock) {
                ts = ts.with(UP, Boolean.TRUE);
            } else {
                ts = ts.with(UP, Boolean.FALSE);
            }
            if (east.getBlock() instanceof PylonBlock || east.isSolid()) {
                ts = ts.with(EAST, Boolean.TRUE);
            } else {
                ts = ts.with(EAST, Boolean.FALSE);
            }
            if (west.getBlock() instanceof PylonBlock|| west.isSolid()) {
                ts = ts.with(WEST, Boolean.TRUE);
            } else {
                ts = ts.with(WEST, Boolean.FALSE);
            }
            if (north.getBlock() instanceof PylonBlock|| north.isSolid()) {
                ts = ts.with(NORTH, Boolean.TRUE);
            } else {
                ts = ts.with(NORTH, Boolean.FALSE);
            }
            if (south.getBlock() instanceof PylonBlock|| south.isSolid()) {
                ts = ts.with(SOUTH, Boolean.TRUE);
            } else {
                ts = ts.with(SOUTH, Boolean.FALSE);
            }

        } return ts;

    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, EAST, WEST, NORTH, SOUTH, AXIS, WATERLOGGED);
    }
}
