package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerConstPacket;

import java.nio.ByteBuffer;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_My_Description extends ServerConstPacket
{
    private static final S_My_Description instance = new S_My_Description();

    public static S_My_Description getInstance()
    {
        return instance;
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_MY_DESCRIPTION;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer)
    {
        writeOpcode(buffer);
        writeInt(buffer, 0x00000006);

    }
}
