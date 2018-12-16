package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Remain_Play_Time extends ServerPacket
{
    private static final ServerPacket instance = new S_Remain_Play_Time();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_REMAIN_PLAY_TIME;
    }

    @Override
    protected void writeImpl()
    {
        writeOpcode();
        writeLong(0x000668AD00000000L);
    }
}

