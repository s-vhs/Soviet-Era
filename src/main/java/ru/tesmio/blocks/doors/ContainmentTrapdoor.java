package ru.tesmio.blocks.doors;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.Half;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.reg.RegItems;
import ru.tesmio.reg.RegSounds;
import ru.tesmio.utils.VoxelShapeUtil;

import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public class ContainmentTrapdoor extends TrapDoorBlock {
    protected static final VoxelShape EAST_OPEN_AABB = VoxelShapes.create(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
    protected static final VoxelShape WEST_OPEN_AABB =  VoxelShapes.create(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final VoxelShape SOUTH_OPEN_AABB =  VoxelShapes.create(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
    protected static final VoxelShape NORTH_OPEN_AABB =  VoxelShapes.create(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
    protected static final VoxelShape BOTTOM_AABB =  VoxelShapes.create(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
    protected static final VoxelShape TOP_AABB =  VoxelShapes.create(0.0D, 0.8125D, 0.0D, 1.0D, 1.0D, 1.0D);
    public ContainmentTrapdoor(Properties properties) {
        super(properties);
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
             worldIn.playSound(null, pos, RegSounds.SOUND_CONTAINMENT_DOOR.get(), SoundCategory.BLOCKS, 0.30f, 1f);
             state = state.cycleValue(OPEN);
            worldIn.setBlockState(pos, state, 2);
            if (state.get(WATERLOGGED)) worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
            return ActionResultType.SUCCESS;

    }

    protected void getDropsWithBlock(World w, BlockPos p,PlayerEntity pl) {
        for(ItemStack is : getItemsDrop(pl)) {
            spawnAsEntity(w, p, is);
        }
    }
    @Override
    public void harvestBlock(World w, PlayerEntity pl, BlockPos p, BlockState s, @Nullable TileEntity te, ItemStack st) {
        if(isCustomDrop()) {
            if (!w.isRemote) {
                if (!pl.isCreative()) {
                    getDropsWithBlock(w, p, pl);
                    getAdditionDrops(w, p, getStackAddDrop(pl));
                }
            }
        }
        pl.addStat(Stats.BLOCK_MINED.get(this));
        pl.addExhaustion(0.005F);
        spawnDrops(s, w, p, te, pl, st);
    }
    public boolean isCustomDrop() {
        return true;
    }

    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return ItemStack.EMPTY;
    }
    @Nullable
    public void getAdditionDrops(World w, BlockPos p, ItemStack is) {
        spawnAsEntity(w, p, is);
    }

    @Override
    public boolean isLadder(BlockState state, net.minecraft.world.IWorldReader world, BlockPos pos, net.minecraft.entity.LivingEntity entity) {
        return true;
    }
    ThreadLocalRandom tr = ThreadLocalRandom.current();
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(1)),
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(3)),
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(6)),
        };
    }
    final  VoxelShape FRAME[] = new VoxelShape[] {
            Block.makeCuboidShape(0,0,0,0.5,3,16),
            Block.makeCuboidShape(15.5,0,0,16,3,16),
            Block.makeCuboidShape(0,0,0,16,3,0.5),
            Block.makeCuboidShape(0,0,15.5,16,3,16),

            Block.makeCuboidShape(0.5,0,13,15.5,16,16)
    };
    final VoxelShape FRAME_TOP[] = new VoxelShape[] {
            Block.makeCuboidShape(0,13,0,0.5,16,16),
            Block.makeCuboidShape(15.5,13,0,16,16,16),
            Block.makeCuboidShape(0,13,0,16,16,0.5),
            Block.makeCuboidShape(0,13,15.5,16,16,16),

            Block.makeCuboidShape(0.5,0,13,15.5,16,16)
    };
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        VoxelShape SHP,SHP2;

        if ((state.get(OPEN)).booleanValue())
        {
            if (state.get(HALF) == Half.BOTTOM) {
            switch (state.get(HORIZONTAL_FACING)) {

                    case NORTH:
                    default:
                        SHP = VoxelShapes.or(FRAME[0], FRAME[1], FRAME[2], FRAME[3], FRAME[4]);
                        break;
                    case SOUTH:
                        SHP = VoxelShapes.or(FRAME[0], FRAME[1], FRAME[2], FRAME[3], VoxelShapeUtil.shapeRot180(FRAME[4]));
                        break;
                    case WEST:
                        SHP = VoxelShapes.or(FRAME[0], FRAME[1], FRAME[2], FRAME[3], VoxelShapeUtil.shapeRotCW90(FRAME[4]));
                        break;
                    case EAST:
                        SHP = VoxelShapes.or(FRAME[0], FRAME[1], FRAME[2], FRAME[3], VoxelShapeUtil.shapeRotCCW90(FRAME[4]));
                }
                return SHP;
            }
            if (state.get(HALF) == Half.TOP) {
                switch (state.get(HORIZONTAL_FACING)) {

                    case NORTH:
                    default:
                        SHP2 = VoxelShapes.or(FRAME_TOP[0], FRAME_TOP[1], FRAME_TOP[2], FRAME_TOP[3], FRAME[4]);
                        break;
                    case SOUTH:
                        SHP2 = VoxelShapes.or(FRAME_TOP[0], FRAME_TOP[1], FRAME_TOP[2], FRAME_TOP[3], VoxelShapeUtil.shapeRot180(FRAME[4]));
                        break;
                    case WEST:
                        SHP2 = VoxelShapes.or(FRAME_TOP[0], FRAME_TOP[1], FRAME_TOP[2], FRAME_TOP[3], VoxelShapeUtil.shapeRotCW90(FRAME[4]));
                        break;
                    case EAST:
                        SHP2 = VoxelShapes.or(FRAME_TOP[0], FRAME_TOP[1], FRAME_TOP[2], FRAME_TOP[3], VoxelShapeUtil.shapeRotCCW90(FRAME[4]));
                }
                return SHP2;
            }
        } else {
            if (state.get(HALF) == Half.TOP) {
                return TOP_AABB;
            } else if (state.get(HALF) == Half.BOTTOM) {
                return BOTTOM_AABB;
            }
        }

        return VoxelShapes.fullCube();
    }
}
