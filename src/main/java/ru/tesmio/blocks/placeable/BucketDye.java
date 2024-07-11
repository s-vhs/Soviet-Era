package ru.tesmio.blocks.placeable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import ru.tesmio.blocks.baseblock.BlockCustomModel;
import ru.tesmio.enums.EnumSovietColor;
import ru.tesmio.reg.RegItems;

public class BucketDye extends BlockCustomModel {

    public static final EnumProperty<EnumSovietColor> COLOR = EnumProperty.create("color", EnumSovietColor.class);

    public BucketDye(Properties properties, float shadingInside) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(COLOR, EnumSovietColor.EMPTY).with(WATERLOGGED, Boolean.valueOf(false)));
    }
    VoxelShape[] SHP = new VoxelShape[] {
            Block.makeCuboidShape(4,3,4,12,10,12),
            Block.makeCuboidShape(5,3,3,11,10,13),
            Block.makeCuboidShape(3,3,5,13,10,11),
            Block.makeCuboidShape(5,3,3,11,10,13),
            Block.makeCuboidShape(5,0,4,11,3,12),
            Block.makeCuboidShape(4,0,5,12,3,11)
    };
    public VoxelShape getShape(BlockState s, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
                return VoxelShapes.or(
                        SHP[0], SHP[1], SHP[2], SHP[3], SHP[4], SHP[5]);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState s, IBlockReader w, BlockPos p, ISelectionContext c) {
        return getShape(s,w,p,c);
    }
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(COLOR, WATERLOGGED);
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            if(player.getHeldItemMainhand().getStack().getItem() == Items.WATER_BUCKET) {
                worldIn.setBlockState(pos, state.with(COLOR, EnumSovietColor.WATER));
                if(!player.isCreative()) player.getHeldItemMainhand().shrink(1);
                if(!player.isCreative()) player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
            }
            if(state.get(COLOR) == EnumSovietColor.WATER) {
                if(player.getHeldItemMainhand().getStack().getItem() == RegItems.BEIGE2_DYE.get()) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumSovietColor.BEIGE2));
                    if(!player.isCreative()) player.getHeldItemMainhand().shrink(1);
                }
                if(player.getHeldItemMainhand().getStack().getItem() == RegItems.BEIGE_DYE.get()) {
                    worldIn.setBlockState(pos, state.with(COLOR, EnumSovietColor.BEIGE));
                    if(!player.isCreative()) player.getHeldItemMainhand().shrink(1);
                }
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
}
