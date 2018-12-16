package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerConstPacket;

import java.nio.ByteBuffer;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_Virtual_Latency extends ServerConstPacket
{
    private static final S_Virtual_Latency instance = new S_Virtual_Latency();

    public static S_Virtual_Latency getInstance()
    {
        return instance;
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_VIRTUAL_LATENCY;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer)
    {
        writeOpcode(buffer);
        writeInt(buffer, 0);
        writeInt(buffer, 0);
    }
}