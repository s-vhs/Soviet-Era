package ru.tesmio.blocks.tablet;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.IReorderingProcessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TabletTER extends TileEntityRenderer<TileEntityTablet> {
    public TabletTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntityTablet te, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.scale(0.6666667F, -0.6666667F, -0.6666667F);
        FontRenderer fontrenderer = this.renderDispatcher.getFontRenderer();
        float f2 = 0.010416667F;
        matrixStackIn.translate(0.0D, 0F, 0F);
       // matrixStackIn.scale(0.010416667F, 0.010416667F, 0.010416667F);
        matrixStackIn.scale(0.10416667F, 0.10416667F, 0.10416667F);
        int color = te.getTextColor().getTextColor();
        for(int k1 = 0; k1 < 4; ++k1) {
            IReorderingProcessor ireorderingprocessor = te.reorderText(k1, (p_243502_1_) -> {
                List<IReorderingProcessor> list = fontrenderer.trimStringToWidth(p_243502_1_, 90);
                return list.isEmpty() ? IReorderingProcessor.field_242232_a : list.get(0);
            });
            if (ireorderingprocessor != null) {
                float f3 = (float)(-fontrenderer.func_243245_a(ireorderingprocessor) / 2);
               fontrenderer.drawString(matrixStackIn, te.getText(0).getString(),   f3, (float)(k1 * 10 - 60), color);
                fontrenderer.drawEntityText(ireorderingprocessor, f3, (float)(k1 * 10 -70), color, false, matrixStackIn.getLast().getMatrix(), bufferIn, false, 0, combinedLightIn);

            }
        }

        matrixStackIn.pop();
    }
}
