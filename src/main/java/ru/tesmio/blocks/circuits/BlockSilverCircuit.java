package ru.tesmio.blocks.circuits;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.baseblock.BlockCircuit;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class BlockSilverCircuit extends BlockCircuit {
    protected static VoxelShape SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty DISSECTION = IntegerProperty.create("diss", 0,7);
    public BlockSilverCircuit(Properties properties, VoxelShape shape) {
        super(properties);
        this.SHAPE = shape;
        this.setDefaultState(this.stateContainer.getBaseState().with(DISSECTION, 0).with(WATERLOGGED, Boolean.FALSE));
    }
    public IItemProvider getDrop(BlockState s, BlockPos p) {
        if(s.get(DISSECTION) != 0) {
            return RegBlocks.SILVER_CIRCUIT_EMPTY.get();
        } else {

            return RegBlocks.SILVER_CIRCUIT.get();
        }
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        if(player.getHeldItemMainhand().getItem() == RegItems.WIRE_CUTTERS.get()) {
            if (worldIn instanceof ServerWorld) {
                switch (state.get(DISSECTION)) {
                    case 0:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 1));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.ORANGE_CONDENSER.get(), 19));
                        player.getHeldItemMainhand().damageItem(19, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 1:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 2));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.GREEN_CONDENSER.get(), 24));
                        player.getHeldItemMainhand().damageItem(24, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 2:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 3));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.YELLOW_CONDENSER.get(), 5));
                        player.getHeldItemMainhand().damageItem(5, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 3:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 4));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_YELLOW_TRANSISTOR.get(), 7));
                        player.getHeldItemMainhand().damageItem(7, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 4:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 5));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.BLACK_MICRO.get(), 39));
                        player.getHeldItemMainhand().damageItem(39, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 5:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 6));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_YELLOW_MICRO.get(), 13));
                        player.getHeldItemMainhand().damageItem(13, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 6:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 7));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_BLUE_MICRO.get(), 3));
                        player.getHeldItemMainhand().damageItem(3, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 7:
                        worldIn.setBlockState(pos, RegBlocks.SILVER_CIRCUIT_EMPTY.get().getDefaultState());
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.YELLOW_JACK.get(), 1));
                        player.getHeldItemMainhand().damageItem(1, player, (p) -> p.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                }

            }
        }
        return ActionResultType.SUCCESS;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DISSECTION, WATERLOGGED);
    }
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }
}
