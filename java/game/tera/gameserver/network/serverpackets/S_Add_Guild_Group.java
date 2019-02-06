package tera.gameserver.network.serverpackets;

import tera.gameserver.model.GuildRank;
import tera.gameserver.network.ServerPacketType;

public class S_Add_Guild_Group extends ServerPacket {
    private static final ServerPacket instance = new S_Add_Guild_Group();

    public static S_Add_Guild_Group getInstance(GuildRank rank)
    {
        S_Add_Guild_Group packet = (S_Add_Guild_Group) instance.newInstance();

        packet.rank = rank;

        return packet;
    }

    /** кол-во ожидания секунд */
    private GuildRank rank;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_ADD_GUILD_GROUP;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeShort(14);
        writeInt(rank.getIndex());
        writeInt(rank.getLawId());
        writeString(rank.getName());
    }
}
