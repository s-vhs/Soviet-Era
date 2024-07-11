package ru.tesmio.blocks.circuits;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
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
import ru.tesmio.blocks.baseblock.BlockCircuit;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

public class BlockCopperCircuit extends BlockCircuit {
    protected static VoxelShape SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty DISSECTION = IntegerProperty.create("diss", 0,5);
    public BlockCopperCircuit(Properties properties, VoxelShape shape) {
        super(properties);
        this.SHAPE = shape;
        this.setDefaultState(this.stateContainer.getBaseState().with(DISSECTION, 0).with(WATERLOGGED, Boolean.FALSE));
    }
    public IItemProvider getDrop(BlockState s, BlockPos p) {
        if(s.get(DISSECTION) != 0) {
            return RegBlocks.COPPER_CIRCUIT_EMPTY.get();
        } else {
            return RegBlocks.COPPER_CIRCUIT.get();
        }
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerEntity, Hand handIn, BlockRayTraceResult hit) {
        ItemStack itemStack = playerEntity.getHeldItemMainhand();
            Item item = playerEntity.getHeldItemMainhand().getItem();
        if(item == RegItems.WIRE_CUTTERS.get()) {
            if (!worldIn.isRemote) {
                switch (state.get(DISSECTION)) {
                    case 0:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 1));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.ORANGE_CONDENSER.get(), 20));
                        itemStack.damageItem(20, playerEntity, (player) -> player.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 1:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 2));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.GREEN_CONDENSER.get(), 2));
                        itemStack.damageItem(2, playerEntity, (player) -> player.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 2:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 3));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_YELLOW_TRANSISTOR.get(), 5));
                        itemStack.damageItem(5, playerEntity, (player) -> player.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 3:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 4));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_RED_MICRO.get(), 9));
                        itemStack.damageItem(4, playerEntity, (player) -> player.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 4:
                        worldIn.setBlockState(pos, state.with(DISSECTION, 5));
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.BLACK_MICRO.get(), 36));
                        itemStack.damageItem(36, playerEntity, (player) -> player.sendBreakAnimation(handIn));
                        return ActionResultType.SUCCESS;
                    case 5:
                        worldIn.setBlockState(pos, RegBlocks.COPPER_CIRCUIT_EMPTY.get().getDefaultState());
                        state.getBlock().spawnAsEntity(worldIn, pos, new ItemStack(RegItems.DARK_YELLOW_MICRO.get(), 1));
                        itemStack.damageItem(1, playerEntity, (player) -> player.sendBreakAnimation(handIn));
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
