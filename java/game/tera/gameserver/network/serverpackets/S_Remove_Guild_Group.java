package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Remove_Guild_Group extends ServerPacket {
    private static final ServerPacket instance = new S_Remove_Guild_Group();

    public static S_Remove_Guild_Group getInstance(int id)
    {
        S_Remove_Guild_Group packet = (S_Remove_Guild_Group) instance.newInstance();

        packet.id = id;

        return packet;
    }

    /** кол-во ожидания секунд */
    private int id;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_REMOVE_GUILD_GROUP;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(id);
    }
}
