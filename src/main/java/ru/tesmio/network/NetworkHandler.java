package ru.tesmio.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import ru.tesmio.core.Core;
import ru.tesmio.network.packets.PacketTabletUpdate;

public class NetworkHandler {
    public static final String NETWORK_VERSION = "0.1.0";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(Core.MODID, "network"), () -> NETWORK_VERSION, version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

    public static void init() {
        CHANNEL.registerMessage(0, PacketTabletUpdate.class, PacketTabletUpdate::encode, PacketTabletUpdate::decode, PacketTabletUpdate::handle);
    }
}
