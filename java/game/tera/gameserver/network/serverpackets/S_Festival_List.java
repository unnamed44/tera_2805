package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerPacket;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_Festival_List extends ServerPacket
{
    private static final ServerPacket instance = new S_Festival_List();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_FESTIVAL_LIST;
    }

    @Override
    protected void writeImpl()
    {
        writeOpcode();
        writeInt(0x00080002);
        writeInt(0x00100008);
        writeInt(0x00000004);
        writeInt(0x00000010);
        writeInt(0x00000050);
    }
}
