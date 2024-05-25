package ru.tesmio.blocks.tablet2;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

public class TabletTileEntityRenderer extends TileEntityRenderer<TabletTileEntity> {
    public TabletTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TabletTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        matrixStackIn.push();
        FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
        matrixStackIn.translate(0.0D, (double)1.33333334F, (double)0.046666667F);
        matrixStackIn.scale(0.10416667F, -0.10416667F, 0.10416667F);
        int i = 0;

    //случайная критическая ошибка по рендеру. Выяснить.// из тайла сюда не приходят строки
        for(int k1 = 0; k1 < 4; ++k1) {
            tileEntityIn.getText(k1);
            //     System.out.println("text " + tileEntityIn.getText(k1));
            fontrenderer.drawText(matrixStackIn, tileEntityIn.getText(k1), 0, k1 * 10 - 20, i);
           }

        matrixStackIn.pop();
    }

}
