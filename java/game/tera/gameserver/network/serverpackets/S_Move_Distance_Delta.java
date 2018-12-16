package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerConstPacket;

import java.nio.ByteBuffer;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_Move_Distance_Delta extends ServerConstPacket
{
    private static final S_Move_Distance_Delta instance = new S_Move_Distance_Delta();

    public static S_Move_Distance_Delta getInstance()
    {
        return instance;
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_MOVE_DISTANCE_DELTA;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer)
    {
        writeOpcode(buffer);
        writeInt(buffer, 0x43480000);
    }
}
