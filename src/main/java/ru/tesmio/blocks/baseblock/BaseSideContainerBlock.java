package ru.tesmio.blocks.baseblock;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class BaseSideContainerBlock extends ContainerBlock implements ITileEntityProvider {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    boolean isCustomDrop = false;
    protected BaseSideContainerBlock(Properties builder) {
        super(builder);

        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    public boolean isCustomDrop() {
        return isCustomDrop;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
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
    public ItemStack getStackAddDrop(PlayerEntity pl) {
        return ItemStack.EMPTY;
    }
    @Nullable
    public void getAdditionDrops(World w, BlockPos p, ItemStack is) {
        spawnAsEntity(w, p, is);
    }

    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[] {
                ItemStack.EMPTY
        };
    }

    protected void getDropsWithBlock(World w, BlockPos p,PlayerEntity pl) {
        for(ItemStack is : getItemsDrop(pl)) {
            spawnAsEntity(w, p, is);
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


}
