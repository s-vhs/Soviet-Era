package ru.tesmio.blocks.tablet;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.client.CUpdateSignPacket;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ru.tesmio.reg.RegTileEntitys;

import javax.annotation.Nullable;
import java.util.function.Function;

public class TileEntityTablet extends TileEntity {
    private final ITextComponent[] signText = new ITextComponent[]{StringTextComponent.EMPTY, StringTextComponent.EMPTY, StringTextComponent.EMPTY, StringTextComponent.EMPTY};
 //   private boolean isEditable = true;
    private PlayerEntity player;
    private final IReorderingProcessor[] renderText = new IReorderingProcessor[4];
    private DyeColor textColor = DyeColor.BLACK;

    public TileEntityTablet getInstance() {
        return this;
    }

    public TileEntityTablet() {
        super(RegTileEntitys.TABLET_TE.get());
    }
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        for(int i = 0; i < 4; ++i) {
            String s = ITextComponent.Serializer.toJson(this.signText[i]);
            compound.putString("Text" + (i + 1), s);
        }

        compound.putString("Color", this.textColor.getTranslationKey());
        return compound;
    }

    public void read(BlockState state, CompoundNBT nbt) {
  //      this.isEditable = false;
        super.read(state, nbt);
        this.textColor = DyeColor.byTranslationKey(nbt.getString("Color"), DyeColor.BLACK);

        for(int i = 0; i < 4; ++i) {
            String s = nbt.getString("Text" + (i + 1));
            ITextComponent itextcomponent = ITextComponent.Serializer.getComponentFromJson(s.isEmpty() ? "\"\"" : s);
            if (this.world instanceof ServerWorld) {
                try {
                //    this.updateText();
                    this.signText[i] = TextComponentUtils.func_240645_a_(this.getCommandSource((ServerPlayerEntity)null), itextcomponent, (Entity)null, 0);
                } catch (CommandSyntaxException commandsyntaxexception) {
               //     this.updateText();
                    this.signText[i] = itextcomponent;
                }
            } else {
         //       this.updateText();
                this.signText[i] = itextcomponent;
            }

            this.renderText[i] = null;
        }

    }
    @OnlyIn(Dist.CLIENT)
    public ITextComponent getText(int line) {
        return this.signText[line];
    }

    public void setText(int line, ITextComponent signText) {
        this.signText[line] = signText;

        this.renderText[line] = null;

    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public IReorderingProcessor reorderText(int row, Function<ITextComponent, IReorderingProcessor> textProcessorFunction) {
    //    this.updateText();
        if (this.renderText[row] == null && this.signText[row] != null) {

            this.renderText[row] = textProcessorFunction.apply(this.signText[row]);
      //      this.updateText();
        }

        return this.renderText[row];
    }
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
      //  this.updateText();
        return new SUpdateTileEntityPacket(this.pos, 9, this.getUpdateTag());
    }
    public CompoundNBT getUpdateTag() {

        return this.write(new CompoundNBT());
    }


    public boolean onlyOpsCanSetNbt() {
        return true;
    }

//    public boolean getIsEditable() {
//        return this.isEditable;
//    }

    /**
     * Sets the sign's isEditable flag to the specified parameter.
     */
//    @OnlyIn(Dist.CLIENT)
//    public void setEditable(boolean isEditableIn) {
//        this.isEditable = isEditableIn;
//        if (!isEditableIn) {
//            this.player = null;
//        }
//
//    }

    public void setPlayer(PlayerEntity playerIn) {
        this.player = playerIn;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

//    @Override
//    public void markDirty() {
//        super.markDirty();
//    }

    public boolean executeCommand(PlayerEntity playerIn) {
        for(ITextComponent itextcomponent : this.signText) {
            Style style = itextcomponent == null ? null : itextcomponent.getStyle();
            if (style != null && style.getClickEvent() != null) {
                ClickEvent clickevent = style.getClickEvent();
                if (clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
                    playerIn.getServer().getCommandManager().handleCommand(this.getCommandSource((ServerPlayerEntity)playerIn), clickevent.getValue());


                }
            }
        }

        return true;
    }

    public CommandSource getCommandSource(@Nullable ServerPlayerEntity playerIn) {
        String s = playerIn == null ? "Sign" : playerIn.getName().getString();
        ITextComponent itextcomponent = (ITextComponent)(playerIn == null ? new StringTextComponent("Sign") : playerIn.getDisplayName());
        return new CommandSource(ICommandSource.DUMMY, Vector3d.copyCentered(this.pos), Vector2f.ZERO, (ServerWorld)this.world, 2, s, itextcomponent, this.world.getServer(), playerIn);
    }

    public DyeColor getTextColor() {
        return this.textColor;
    }
    public void updateText(String line1, String line2, String line3, String line4) {
        //нет пакета. Делать пакетную систему, отправлять вручную
        CUpdateSignPacket signPacket = new CUpdateSignPacket(this.getPos(),line1,line2,line3,line4);

    }
    public CUpdateSignPacket updateText(ITextComponent line1, ITextComponent line2, ITextComponent line3, ITextComponent line4) {

        return new CUpdateSignPacket(this.getPos(),line1.getString(),line2.getString(),line3.getString(),line4.getString());
    }
    public boolean setTextColor(DyeColor newColor) {
        if (newColor != this.getTextColor()) {
            this.textColor = newColor;
            this.markDirty();
            this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
            return true;
        } else {
            return false;
        }
    }
}
