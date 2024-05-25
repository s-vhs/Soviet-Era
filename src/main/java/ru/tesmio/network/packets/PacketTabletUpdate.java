package ru.tesmio.network.packets;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import ru.tesmio.blocks.tablet2.TabletTileEntity;

import java.util.function.Supplier;

public class PacketTabletUpdate {

    public ITextComponent line1, line2, line3, line4;

    public BlockPos pos;
    public PacketTabletUpdate() {

    }

    public PacketTabletUpdate(BlockPos pos, String line1,String line2,String line3,String line4) {
        this.pos = pos;
        this.line1 = new StringTextComponent(line1);
        this.line2 = new StringTextComponent(line2);
        this.line3 = new StringTextComponent(line3);
        this.line4 = new StringTextComponent(line4);
    }

    public static void encode(PacketTabletUpdate packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);
        buffer.writeString(packet.line1.getString());
        buffer.writeString(packet.line2.getString());
        buffer.writeString(packet.line3.getString());
        buffer.writeString(packet.line4.getString());

    }

    public static PacketTabletUpdate decode(PacketBuffer buffer) {
        return new PacketTabletUpdate(buffer.readBlockPos(), buffer.readString(),buffer.readString(),buffer.readString(),buffer.readString());
    }

    public static void handle(PacketTabletUpdate packet, Supplier<NetworkEvent.Context> c) {
        NetworkEvent.Context cont = c.get();
        cont.enqueueWork(() -> {
            ServerPlayerEntity player = cont.getSender();
            World world = player.getEntityWorld();
            BlockPos tilePos = packet.pos;

           TileEntity te = world.getTileEntity(tilePos);
            if(te instanceof TabletTileEntity) {

                ((TabletTileEntity) te).setText(0, packet.line1);
                ((TabletTileEntity) te).setText(1, packet.line2);
                ((TabletTileEntity) te).setText(2, packet.line3);
                ((TabletTileEntity) te).setText(3, packet.line4);

            }
           te.markDirty();
           world.notifyBlockUpdate(tilePos, world.getBlockState(tilePos), world.getBlockState(tilePos), 3);
        });
        cont.setPacketHandled(true);
    }
}
