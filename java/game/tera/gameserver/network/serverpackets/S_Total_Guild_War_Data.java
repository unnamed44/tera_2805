package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Total_Guild_War_Data extends ServerPacket {
    private static final ServerPacket instance = new S_Total_Guild_War_Data();

    public static S_Total_Guild_War_Data getInstance()
    {
        S_Total_Guild_War_Data packet = (S_Total_Guild_War_Data) instance.newInstance();
        return packet;
    }

    /** кол-во ожидания секунд */

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_TOTAL_GUILD_WAR_DATA;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(0);
    }
}
