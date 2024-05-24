package ru.tesmio.network.packets;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import ru.tesmio.blocks.tablet.TileEntityTablet;

import java.util.function.Supplier;

public class PacketTabletUpdate {

    public String line1, line2, line3, line4;

    public BlockPos pos;
    public PacketTabletUpdate() {

    }

    public PacketTabletUpdate(BlockPos pos, String line1,String line2,String line3,String line4) {
        this.pos = pos;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;

    }

    public static void encode(PacketTabletUpdate packet, PacketBuffer buffer) {
        buffer.writeBlockPos(packet.pos);
        buffer.writeString(packet.line1);
        buffer.writeString(packet.line2);
        buffer.writeString(packet.line3);
        buffer.writeString(packet.line4);

    }

    public static PacketTabletUpdate decode(PacketBuffer buffer) {
        return new PacketTabletUpdate(buffer.readBlockPos(), buffer.readString(),buffer.readString(),buffer.readString(),buffer.readString());
    }

    public static void handle(PacketTabletUpdate packet, Supplier<NetworkEvent.Context> c) {
        NetworkEvent.Context cont = c.get();
        cont.enqueueWork(() -> {
            ServerPlayerEntity player = cont.getSender();
            assert player != null;
            World world = player.getEntityWorld();
            BlockPos tilePos = packet.pos;

           TileEntity te = world.getTileEntity(tilePos);
            if(te instanceof TileEntityTablet) {
                ((TileEntityTablet) te).setText(0, new StringTextComponent(packet.line1));
                ((TileEntityTablet) te).setText(1, new StringTextComponent(packet.line2));
                ((TileEntityTablet) te).setText(2, new StringTextComponent(packet.line3));
                ((TileEntityTablet) te).setText(3, new StringTextComponent(packet.line4));
            }
            te.markDirty();
            world.notifyBlockUpdate(tilePos, world.getBlockState(tilePos), world.getBlockState(tilePos), 3);
        });
        cont.setPacketHandled(true);
    }
}
