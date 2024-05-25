package ru.tesmio.blocks.tablet2;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import ru.tesmio.reg.RegTileEntitys;

public class TabletTileEntity  extends TileEntity {
    public ITextComponent[] signText  = new ITextComponent[]{StringTextComponent.EMPTY, StringTextComponent.EMPTY, StringTextComponent.EMPTY, StringTextComponent.EMPTY};

    public TabletTileEntity() {

        super(RegTileEntitys.TABLET_TE.get());

    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        this.markDirty();
        for(int i = 0; i < 4; ++i) {
            String s = this.signText[i].getString();
            compound.putString("Text" + (i + 1), s);
        }
        return compound;
    }

    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        for(int i = 0; i < 4; ++i) {
            String s = nbt.getString("Text" + (i + 1));

            ITextComponent itextcomponent = new StringTextComponent(s);
                this.signText[i] = itextcomponent;

        }

    }
    public ITextComponent getText(int line) {
      return this.signText[line];
    }
    public void setText(int line, ITextComponent signText) {
        this.signText[line] = signText;

    }
//    @Nullable
//    public SUpdateTileEntityPacket getUpdatePacket() {
//        return new SUpdateTileEntityPacket(this.pos, 1, this.getUpdateTag());
//    }
    public CompoundNBT getUpdateTag() {

        return this.write(new CompoundNBT());
    }
}
