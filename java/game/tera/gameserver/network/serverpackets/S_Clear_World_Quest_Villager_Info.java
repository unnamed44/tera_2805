package tera.gameserver.network.serverpackets;


import tera.gameserver.network.ServerPacketType;

public class S_Clear_World_Quest_Villager_Info extends ServerPacket {
    private static final ServerPacket instance = new S_Clear_World_Quest_Villager_Info();

    public static S_Clear_World_Quest_Villager_Info getInstance()
    {
        return (S_Clear_World_Quest_Villager_Info) instance;
    }

    /** кол-во ожидания секунд */
    private int seconds;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_CLEAR_WORLD_QUEST_VILLAGER_INFO;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
    }
}
