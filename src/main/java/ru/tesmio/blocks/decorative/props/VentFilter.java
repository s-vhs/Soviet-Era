package ru.tesmio.blocks.decorative.props;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import ru.tesmio.blocks.decorative.devices.base.BlockForFacingDevice;
import ru.tesmio.reg.RegItems;

public class VentFilter extends BlockForFacingDevice {
    VoxelShape[] BOXS = new VoxelShape[] {
            VoxelShapes.create(0.19D, 0.19D, 1D, 0.81D, 0.81D, 0.89D),
            VoxelShapes.create(0.19D, 0.19D, 0D, 0.81D, 0.81D, 0.11D),
            VoxelShapes.create(0D, 0.19D, 0.19D, 0.11D, 0.81D, 0.81D),
            VoxelShapes.create(1D,  0.19D,  0.19D, 0.89D, 0.81D, 0.81D),
            VoxelShapes.create(0.19D, 0D, 0.19D, 0.81D, 0.11D, 0.81D),
            VoxelShapes.create(0.19D, 0.89D, 0.19D, 0.81D, 1D, 0.81D),
    };
    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(0,4)),
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(0,2))
        };
    }
    public VentFilter(Properties properties) {
        super(properties);
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction, context.getPlacementHorizontalFacing()));
            } else {
                return this.getDefaultState().with(FACING, EnumOrientation.forFacing(direction, direction));
            }
        }
        return this.getDefaultState();
    }
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (s.get(FACING)) {
            case NORTH:
                return BOXS[1];
            case SOUTH:
                return BOXS[0];
            case EAST:
                return BOXS[3];
            case WEST:
                return BOXS[2];
            case UP:
                return BOXS[5];
            case DOWN:
                return BOXS[4];
        }
        return VoxelShapes.fullCube();
    }

}
