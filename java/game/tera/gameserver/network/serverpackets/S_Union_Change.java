package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

public class S_Union_Change extends ServerPacket {
    private static final ServerPacket instance = new S_Union_Change();

    public static S_Union_Change getInstance(Player player)
    {
        S_Union_Change packet = (S_Union_Change) instance.newInstance();
        packet.player = player;
        return packet;
    }

    /** кол-во ожидания секунд */
    private Player player;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_UNION_CHANGE;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(player.getObjectId());
        writeInt(player.getSubId());
        writeInt(player.getGuild().getAllianceId());
        writeLong(player.getAllianceClass());
        writeByte(1);
    }
}
