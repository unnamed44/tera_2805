package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

public class S_Hide_HP extends ServerPacket {
    private static final ServerPacket instance = new S_Hide_HP();

    public static S_Hide_HP getInstance(Player player)
    {
        S_Hide_HP packet = (S_Hide_HP) instance.newInstance();

        packet.player = player;

        return packet;
    }

    /** кол-во ожидания секунд */
    private Player player;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_HIDE_HP;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(player.getObjectId());
        writeInt(player.getSubId());
    }
}
