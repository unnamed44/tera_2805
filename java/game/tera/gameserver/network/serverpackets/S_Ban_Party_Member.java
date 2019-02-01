package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

public class S_Ban_Party_Member extends ServerPacket {
    private static final ServerPacket instance = new S_Ban_Party_Member();
    private Player player;

    public static S_Ban_Party_Member getInstance(Player player)
    {
        S_Ban_Party_Member packet = (S_Ban_Party_Member) instance.newInstance();

        packet.player = player;

        return packet;
    }


    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_BAN_PARTY_MEMBER;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(12);
        writeInt(player.getObjectId());
        writeInt(0xFFFFFFFF);
        writeString(player.getName());
    }
}
