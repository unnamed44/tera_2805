package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Change_Guild_Chief extends ServerPacket {
    private static final ServerPacket instance = new S_Change_Guild_Chief();

    public static S_Change_Guild_Chief getInstance(int playerId)
    {
        S_Change_Guild_Chief packet = (S_Change_Guild_Chief) instance.newInstance();

        packet.playerId = playerId;

        return packet;
    }

    /** кол-во ожидания секунд */
    private int playerId;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_CHANGE_GUILD_CHIEF;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(playerId);
    }
}
