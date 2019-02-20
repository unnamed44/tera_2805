package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

public class S_Crest_Info extends ServerPacket {
    private static final ServerPacket instance = new S_Crest_Info();

    public static S_Crest_Info getInstance(Player player)
    {
        S_Crest_Info packet = (S_Crest_Info) instance.newInstance();

        packet.player = player;

        return packet;
    }

    /** кол-во ожидания секунд */
    private Player player;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_CREST_INFO;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeShort(0);
        writeShort(0);
        writeInt(50);
        writeInt(0);
    }
}
