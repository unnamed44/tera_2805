package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerPacket;

public class S_Available_Social_List extends ServerPacket
{
    private static final ServerPacket instance = new S_Available_Social_List();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_AVAILABLE_SOCIAL_LIST;
    }

    @Override
    protected void writeImpl()
    {

        writeOpcode();
        writeInt(0);

    }
}