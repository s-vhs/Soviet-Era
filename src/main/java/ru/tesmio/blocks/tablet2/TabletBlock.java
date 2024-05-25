package ru.tesmio.blocks.tablet2;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.blocks.baseblock.BlockSideCustomModel;
import ru.tesmio.reg.RegTileEntitys;

public class TabletBlock extends BlockSideCustomModel {
    public TabletBlock(Properties properties) {
        super(properties, 1F);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1f;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    final VoxelShape SHP = Block.makeCuboidShape(0, 0, 0, 16, 16, 16);

    @Override
    public ItemStack[] getItemsDrop(PlayerEntity pl) {
        return new ItemStack[]{
                ItemStack.EMPTY
        };
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RegTileEntitys.TABLET_TE.get().create();

    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

            TileEntity te = worldIn.getTileEntity(pos);
            if (te != null) {
                if (te instanceof TabletTileEntity) {
                    TabletTileEntity tet = (TabletTileEntity) te;
                    Minecraft.getInstance().displayGuiScreen(new TabletScreen(tet));

                    return ActionResultType.SUCCESS;
                }
            }

        return ActionResultType.SUCCESS;
    }
}
