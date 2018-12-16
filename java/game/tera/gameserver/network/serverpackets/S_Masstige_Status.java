package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerPacket;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_Masstige_Status extends ServerPacket
{
    private static final ServerPacket instance = new S_Masstige_Status();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_MASSTIGE_STATUS;
    }

    @Override
    protected void writeImpl()
    {
        writeOpcode();
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
    }
}
