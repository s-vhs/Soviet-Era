package ru.tesmio.reg;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegScreen {
    private static ResourceLocation GASMASK_GUI = new ResourceLocation("soviet:textures/gui/gasmask_gui.png");;
    protected int scaledWidth;
    protected int scaledHeight;
    Minecraft mc = Minecraft.getInstance();
    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post e) {
        scaledWidth = e.getWindow().getScaledWidth();
        scaledHeight = e.getWindow().getScaledHeight();

        if (e.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE) {

            ItemStack itemstack = this.mc.player.inventory.armorItemInSlot(3);

            if (!itemstack.isEmpty()) {
                Item item = itemstack.getItem();

                if (item == RegItems.SUIT_GAS_MASK.get()) {

                    if (this.mc.gameSettings.getPointOfView().func_243192_a() && itemstack.getItem() == RegItems.SUIT_GAS_MASK.get()) {

                        RenderSystem.disableDepthTest();
                        RenderSystem.depthMask(false);

                        RenderSystem.defaultBlendFunc();

                        //    RenderSystem.enableCull();
                        RenderSystem.enableBlend();
                        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                        RenderSystem.disableAlphaTest();
                        this.mc.getTextureManager().bindTexture(GASMASK_GUI);
                        Tessellator tessellator = Tessellator.getInstance();
                        BufferBuilder bufferbuilder = tessellator.getBuffer();
                        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
                        bufferbuilder.pos(0.0D, (double) this.scaledHeight, -90.0D).tex(0.0F, 1.0F).endVertex();
                        bufferbuilder.pos((double) this.scaledWidth, (double) this.scaledHeight, -90.0D).tex(1.0F, 1.0F).endVertex();
                        bufferbuilder.pos((double) this.scaledWidth, 0.0D, -90.0D).tex(1.0F, 0.0F).endVertex();
                        bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0F, 0.0F).endVertex();
                        tessellator.draw();
                        RenderSystem.depthMask(true);
                        RenderSystem.disableBlend();
                        RenderSystem.enableDepthTest();
                        RenderSystem.enableAlphaTest();
                        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

                        //      RenderSystem.disableCull();
                    }
                }
            }

        }
    }


}
