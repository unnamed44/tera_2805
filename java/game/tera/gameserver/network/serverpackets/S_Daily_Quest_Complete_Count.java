package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Daily_Quest_Complete_Count extends ServerPacket {
    private static final ServerPacket instance = new S_Daily_Quest_Complete_Count();

    public static S_Daily_Quest_Complete_Count getInstance()
    {

        return (S_Daily_Quest_Complete_Count) instance;
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_DAILY_QUEST_COMPLETE_COUNT;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeShort(0);//total did
        writeShort(10);//total
        writeByte(0);
    }
}
