package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Dungeon_Coll_Time_List extends ServerPacket {
    private static final ServerPacket instance = new S_Dungeon_Coll_Time_List();

    public static S_Dungeon_Coll_Time_List getInstance()
    {

        return (S_Dungeon_Coll_Time_List) instance;
    }


    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_DUNGEON_COOL_TIME_LIST;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(0);
        writeInt(0);
    }
}
