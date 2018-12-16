package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerConstPacket;

import java.nio.ByteBuffer;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_Pet_Info_Clear extends ServerConstPacket {

    private static final S_Pet_Info_Clear instance = new S_Pet_Info_Clear();

    public static S_Pet_Info_Clear getInstance()
    {
        return instance;
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_PET_INFO_CLEAR;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer)
    {
        writeOpcode(buffer);
    }
}
