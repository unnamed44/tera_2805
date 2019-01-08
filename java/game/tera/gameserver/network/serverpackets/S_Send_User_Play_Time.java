package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn Mod Evestu
 */
public class S_Send_User_Play_Time extends ServerPacket
{
    private static final ServerPacket instance = new S_Send_User_Play_Time();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_SEND_USER_PLAY_TIME;
    }

    @Override
    protected void writeImpl()
    {

        writeOpcode();
        writeInt(0);
        writeInt(0x55F8922A);
        writeInt(0);
    }
}