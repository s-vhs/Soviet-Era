package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;

import java.util.concurrent.ThreadLocalRandom;

public class SystemBlock extends BlockSideDevice {
    public SystemBlock(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();

        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1,3)),
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(2,4)),
                new ItemStack(RegBlocks.SILVER_CIRCUIT.get(), tr.nextInt(3,6)),
                new ItemStack(RegBlocks.GOLD_CIRCUIT.get(),  tr.nextInt(2,5))
        };
    }
    @Override
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();

            if (tr.nextInt(25) == 4) {
                return new ItemStack(RegBlocks.PLATE_GOLDEN_JACKS.get(), 1);
            } else {
                return ItemStack.EMPTY;
            }

    }
    final VoxelShape NS = VoxelShapes.create(0.25D, 0.0D, 0D, 0.75D, 0.91D, 1D);
    final VoxelShape WE = VoxelShapes.create(0D, 0.0D, 0.25D, 1D, 0.91D, 0.75D);
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case SOUTH:
            case NORTH:
                return NS;
            case WEST:
            case EAST:
                return WE;
        }
        return VoxelShapes.fullCube();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
}
