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
        writeInt(6);//account type
        //1 = P2P (active subscription), 2 = P2P (no active subscription)
        //3 = F2P (free-play event), 4 = F2P (legacy restriction)
        //5 = Premium, 6 = Basic
        writeInt(0);//minutes left
    }
}

