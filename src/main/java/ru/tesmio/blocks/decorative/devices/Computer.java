package ru.tesmio.blocks.decorative.devices;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;
import ru.tesmio.blocks.decorative.devices.base.BlockSideDevice;
import ru.tesmio.reg.RegBlocks;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegSounds;
import ru.tesmio.utils.VoxelShapeUtil;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Computer extends BlockSideDevice {

    final VoxelShape BOXS[] = new VoxelShape[] {
            Block.makeCuboidShape(2,3,1,15,15,15),
            Block.makeCuboidShape(2,1,2,3,16,14),
            Block.makeCuboidShape(4,15,3,15,16,13),
            Block.makeCuboidShape(4,2,3,15,3,13),
            Block.makeCuboidShape(4,0,4,12,1,12),
            Block.makeCuboidShape(2,4,0,14,14,2),
            Block.makeCuboidShape(2,4,14,14,14,16),
            Block.makeCuboidShape(4,3,4,16,15,12),
            Block.makeCuboidShape(4,4,2,16,14,14)
    };
    public static final BooleanProperty ENABLE = BooleanProperty.create("enable");
    public Computer(Properties properties, float shadingInside) {
        super(properties, shadingInside);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)).with(ENABLE, false));
    }

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1,3)),
                new ItemStack(RegItems.ALUMINUM_SCRAP.get(), tr.nextInt(2,4)),
                new ItemStack(RegBlocks.COPPER_CIRCUIT.get(), tr.nextInt(3,5)),
                new ItemStack(RegBlocks.GOLD_CIRCUIT.get(),  tr.nextInt(1,2))
        };
    }

    @Override
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        ThreadLocalRandom tr = ThreadLocalRandom.current();

            if (tr.nextInt(25) == 4) {
                return new ItemStack(RegBlocks.DIAMOND_CIRCUIT.get(), 1);
            } else {
                return ItemStack.EMPTY;

        }
    }

    @Override
    public void tick(BlockState s, ServerWorld w, BlockPos p, Random rand) {
        if(!w.isRemote()) {
            if (w.isBlockPowered(p)) {
                w.getPendingBlockTicks().scheduleTick(p, this, 113);
                w.playSound(null, p, RegSounds.SOUND_DEVICE.get(), SoundCategory.BLOCKS, 0.10f, 1f);
            }
        }
    }
    public void neighborChanged(BlockState s, World w, BlockPos p, Block b, BlockPos fromPos, boolean isMoving) {
        w.getPendingBlockTicks().scheduleTick(p, this, 6);
    }
    @Override
    public BlockState updatePostPlacement(BlockState s, Direction f, BlockState bs, IWorld w, BlockPos p, BlockPos facingPos) {
        if(w instanceof WorldGenRegion) return s;
        return updateState((World) w,p,s);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, worldIn, pos, context);
    }
    public BlockState updateState(World w, BlockPos p, BlockState s) {
        if (!w.isRemote()) {
            if(w.isBlockPowered(p)) {
                return s.with(ENABLE, true);
            } else {
                return s.with(ENABLE, false);
            }
        }
        return s;
    }
    @Override
    public int getLightValue(BlockState s, IBlockReader br, BlockPos p) {

        if(s.get(ENABLE)) {
            return 2;
        }
        return 0;
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

            switch (state.get(FACING)) {
                case WEST:
                    return VoxelShapes.or(
                            BOXS[0],
                            BOXS[1],
                            BOXS[2],
                            BOXS[3],
                            BOXS[4],
                            BOXS[5],
                            BOXS[6],
                            BOXS[7],
                            BOXS[8]
                    );
                case EAST:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(BOXS[0]),
                            VoxelShapeUtil.shapeRot180(BOXS[1]),
                            VoxelShapeUtil.shapeRot180(BOXS[2]),
                            VoxelShapeUtil.shapeRot180(BOXS[3]),
                            VoxelShapeUtil.shapeRot180(BOXS[4]),
                            VoxelShapeUtil.shapeRot180(BOXS[5]),
                            VoxelShapeUtil.shapeRot180(BOXS[6]),
                            VoxelShapeUtil.shapeRot180(BOXS[7]),
                            VoxelShapeUtil.shapeRot180(BOXS[8])
                    );
                case NORTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRotCW90(BOXS[0]),
                            VoxelShapeUtil.shapeRotCW90(BOXS[1]),
                            VoxelShapeUtil.shapeRotCW90(BOXS[2]),
                            VoxelShapeUtil.shapeRotCW90(BOXS[3]),
                            VoxelShapeUtil.shapeRotCW90(BOXS[4]),
                            VoxelShapeUtil.shapeRotCW90(BOXS[5]),
                            VoxelShapeUtil.shapeRotCW90(BOXS[6]),
                            VoxelShapeUtil.shapeRotCW90(BOXS[7]),
                            VoxelShapeUtil.shapeRotCW90(BOXS[8])
                    );
                case SOUTH:
                    return VoxelShapes.or(
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[0])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[1])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[2])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[3])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[4])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[5])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[6])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[7])),
                            VoxelShapeUtil.shapeRot180(VoxelShapeUtil.shapeRotCCW90(BOXS[8]))
                    );
            }
            return VoxelShapes.fullCube();
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,ENABLE, WATERLOGGED);
    }
}
