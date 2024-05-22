package ru.tesmio.blocks.baseblock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockForFacing extends BaseBlock {
    public static final EnumProperty<EnumOrientation> FACING = EnumProperty.create("facing", EnumOrientation.class);

    public BlockForFacing(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, EnumOrientation.NORTH));
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
    @Override
    public void harvestBlock(World w, PlayerEntity pl, BlockPos p, BlockState s, @Nullable TileEntity te, ItemStack st) {
        if(this.disableJSONDrop()) {
            if (!w.isRemote) {
                if (!pl.isCreative()) {
                    getDropsWithBlock(w, p, pl);
                    getAdditionDrops(w,p,getStackAddDrop(pl));
                }
            }
        } else {
            pl.addStat(Stats.BLOCK_MINED.get(this));
            pl.addExhaustion(0.005F);
            spawnDrops(s, w, p, te, pl, st);
        }
    }
    public boolean disableJSONDrop() {
        return true;
    }
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return ItemStack.EMPTY;
    }
    @Nullable
    public void getAdditionDrops(World w, BlockPos p, ItemStack is) {
        spawnAsEntity(w, p, is);
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {ItemStack.EMPTY};
    }

    protected void getDropsWithBlock(World w, BlockPos p, PlayerEntity pl) {
        for(ItemStack is : getItemsDrop(pl)) {
            spawnAsEntity(w, p, is);
        }
    }
    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot)
        {
            case CLOCKWISE_180:
                switch (state.get(FACING))
                {
                    case EAST:
                        return state.with(FACING, EnumOrientation.WEST);
                    case WEST:
                        return state.with(FACING, EnumOrientation.EAST);
                    case SOUTH:
                        return state.with(FACING, EnumOrientation.NORTH);
                    case NORTH:
                        return state.with(FACING, EnumOrientation.SOUTH);
                    default:
                        return state;
                }

            case COUNTERCLOCKWISE_90:

                switch (state.get(FACING))
                {
                    case EAST:
                        return state.with(FACING, EnumOrientation.NORTH);
                    case WEST:
                        return state.with(FACING, EnumOrientation.SOUTH);
                    case SOUTH:
                        return state.with(FACING, EnumOrientation.EAST);
                    case NORTH:
                        return state.with(FACING, EnumOrientation.WEST);
                    case UP:
                        return state.with(FACING, EnumOrientation.UP);
                    case DOWN:
                        return state.with(FACING, EnumOrientation.DOWN);
                }

            case CLOCKWISE_90:
                switch (state.get(FACING))
                {
                    case EAST:
                        return state.with(FACING, EnumOrientation.SOUTH);
                    case WEST:
                        return state.with(FACING, EnumOrientation.NORTH);
                    case SOUTH:
                        return state.with(FACING, EnumOrientation.WEST);
                    case NORTH:
                        return state.with(FACING, EnumOrientation.EAST);
                    case UP:
                        return state.with(FACING, EnumOrientation.UP);
                    case DOWN:
                        return state.with(FACING, EnumOrientation.DOWN);
                }

            default:
                return state;
        }
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    public enum EnumOrientation implements IStringSerializable {
        EAST("east", Direction.EAST),
        WEST("west", Direction.WEST),
        SOUTH("south", Direction.SOUTH),
        NORTH("north", Direction.NORTH),
        UP("up", Direction.UP),
        DOWN("down", Direction.DOWN);
        private final String name;
        private final Direction dir;

        EnumOrientation(String name, Direction dir) {
            this.name = name;
            this.dir = dir;
        }

        @Override
        public String getString() {
            return this.name;
        }

        public Direction getDirection() {
            return this.dir;
        }

        public static EnumOrientation forFacing(Direction clickedSide, Direction entityFacing) {
            switch (clickedSide) {
                case DOWN:
                    return DOWN;
                case UP:
                    return UP;
                case NORTH:
                    return NORTH;
                case SOUTH:
                    return SOUTH;
                case WEST:
                    return WEST;
                case EAST:
                    return EAST;
                default:
                    throw new IllegalArgumentException("Invalid facing: " + clickedSide);
            }
        }
    }
}
