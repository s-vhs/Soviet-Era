package ru.tesmio.blocks.doors;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class ContainmentDoor extends LockedDoor implements IWaterLoggable {

    Map<String, VoxelShape> SHAPE_MAP = new HashMap<>();

    public ContainmentDoor(Properties builder) {
        super(builder);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(OPEN, false).with(HINGE, DoorHingeSide.LEFT).with(POWERED, Boolean.valueOf(false)).with(HALF, DoubleBlockHalf.LOWER).with(LOCKED, Boolean.valueOf(true)).with(WATERLOGGED, Boolean.valueOf(false)));

    }

    public boolean isCustomDrop() {
        return true;
    }
    public void putMapVoxelShape() {
        VoxelShape SHAPES[] = new VoxelShape[] {
                Block.makeCuboidShape(0D, 0D, 12D, 16D, 16D, 16D),
                Block.makeCuboidShape(0D, 0D, 12D, 0.5D, 16D, 16D),
                Block.makeCuboidShape(15.5D, 0D, 12D, 16D, 16D, 16D),
                Block.makeCuboidShape(0D, 0D, 12D, 16D, 0.5D, 16D),
                Block.makeCuboidShape(0D, 15.5D, 12D, 16D, 16D, 16D),

                Block.makeCuboidShape(0.5D, 0.5D, 0D, 3.51D, 16D, 14D),
                Block.makeCuboidShape(0.5D, 0D, 0D, 3.51D, 15.5D, 14D),
                Block.makeCuboidShape(15.5D, 0.5D, 0D, 12.49D, 16D, 14D),
                Block.makeCuboidShape(15.5D, 0D, 0D, 12.49D, 15.5D, 14D)
        };
        SHAPE_MAP.put("close", SHAPES[0]);
        SHAPE_MAP.put("right_frame", SHAPES[1]);
        SHAPE_MAP.put("left_frame", SHAPES[2]);
        SHAPE_MAP.put("lower_frame", SHAPES[3]);
        SHAPE_MAP.put("upper_frame", SHAPES[4]);
        SHAPE_MAP.put("open_door_right_lower", SHAPES[5]);
        SHAPE_MAP.put("open_door_right_upper", SHAPES[6]);
        SHAPE_MAP.put("open_door_left_lower", SHAPES[7]);
        SHAPE_MAP.put("open_door_left_upper", SHAPES[8]);
    }
    ThreadLocalRandom tr = ThreadLocalRandom.current();
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                new ItemStack(RegItems.COPPER_SCRAP.get(), tr.nextInt(2)),
                new ItemStack(RegItems.ARMATURES.get(), tr.nextInt(5)),
                new ItemStack(RegItems.RUSTY_SCRAP.get(), tr.nextInt(13)),
        };
    }
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand h, BlockRayTraceResult hit) {
        {
            boolean isLocked = state.get(LOCKED);

                 if(player.getHeldItem(h).getItem() == RegItems.WRENCH.get()) {
                     worldIn.playSound(null, pos, RegSounds.SOUND_CONTAINMENT_DOOR.get(), SoundCategory.BLOCKS, 0.30f, 1f);
                     state = state.cycleValue(LOCKED);
                     worldIn.setBlockState(pos, state, 10);
                     return ActionResultType.SUCCESS;

             }
            if(hit.getFace() == state.get(FACING)) {
                if(player.isCrouching()) {
                    if(!state.get(OPEN)) {
                        worldIn.playSound(null, pos, RegSounds.SOUND_CONTAINMENT_DOOR.get(), SoundCategory.BLOCKS, 0.30f, 1f);
                        state = state.cycleValue(LOCKED);
                    }
                    worldIn.setBlockState(pos, state, 10);
                    return ActionResultType.SUCCESS;
                }
            }
            if(!isLocked) {
                worldIn.playSound(null, pos, RegSounds.SOUND_CONTAINMENT_DOOR.get(), SoundCategory.BLOCKS, 0.30f, 1f);
                state = state.cycleValue(OPEN);
                worldIn.setBlockState(pos, state, 10);
                return ActionResultType.SUCCESS;
             }

            return ActionResultType.PASS;
        }
    }
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        putMapVoxelShape();
        Direction direction = state.get(FACING);
        boolean isOpen = state.get(OPEN);
        boolean isHingeRight = state.get(HINGE) == DoorHingeSide.RIGHT;
        boolean isLower = state.get(HALF) == DoubleBlockHalf.LOWER;
        switch (direction) {
            case SOUTH:
                if(isOpen) {
                    if(isLower) {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("right_frame"),
                                    SHAPE_MAP.get("left_frame"),
                                    SHAPE_MAP.get("open_door_left_lower"),
                                    SHAPE_MAP.get("lower_frame"));
                        } else {
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("right_frame"),
                                    SHAPE_MAP.get("left_frame"),
                                    SHAPE_MAP.get("open_door_right_lower"),
                                    SHAPE_MAP.get("lower_frame"));
                        }
                    } else {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("right_frame"),
                                    SHAPE_MAP.get("left_frame"),
                                    SHAPE_MAP.get("open_door_left_upper"),
                                    SHAPE_MAP.get("upper_frame"));
                        } else {
                            return VoxelShapes.or(
                                    SHAPE_MAP.get("right_frame"),
                                    SHAPE_MAP.get("left_frame"),
                                    SHAPE_MAP.get("open_door_right_upper"),
                                    SHAPE_MAP.get("upper_frame"));
                        }
                    }
                }
                return SHAPE_MAP.get("close");
            case WEST:
                if(isOpen) {
                    if(isLower) {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("open_door_left_lower")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("lower_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("open_door_right_lower")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("lower_frame")));
                        }
                    } else {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("open_door_left_upper")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("upper_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("open_door_right_upper")),
                                    VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("upper_frame")));
                        }
                    }
                }
                return VoxelShapeUtil.shapeRotCCW90(SHAPE_MAP.get("close"));
            case NORTH:
                if(isOpen) {
                    if(isLower) {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("open_door_left_lower")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("lower_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("open_door_right_lower")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("lower_frame")));
                        }
                    } else {
                        if (!isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("open_door_left_upper")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("upper_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("open_door_right_upper")),
                                    VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("upper_frame")));
                        }
                    }
                }
                return VoxelShapeUtil.shapeRot180(SHAPE_MAP.get("close"));
            case EAST:
                if(isOpen) {
                    if(isLower) {
                        if (isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("open_door_left_lower")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("lower_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("open_door_right_lower")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("lower_frame")));
                        }
                    } else {
                        if (isHingeRight) {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("open_door_left_upper")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("upper_frame")));
                        } else {
                            return VoxelShapes.or(
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("right_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("left_frame")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("open_door_right_upper")),
                                    VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("upper_frame")));
                        }
                    }
                }
                return VoxelShapeUtil.shapeRotCW90(SHAPE_MAP.get("close"));
        }
        return VoxelShapes.fullCube();
    }
}
