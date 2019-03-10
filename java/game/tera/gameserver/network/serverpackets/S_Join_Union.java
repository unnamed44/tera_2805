package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Join_Union extends ServerPacket {
    private static final ServerPacket instance = new S_Join_Union();

    public static S_Join_Union getInstance(int allianceId, int success)
    {
        S_Join_Union packet = (S_Join_Union) instance.newInstance();

        packet.allianceId = allianceId;
        packet.success = success;

        return packet;
    }

    /** кол-во ожидания секунд */
    private int allianceId;
    private int success;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_JOIN_UNION;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(allianceId);
        writeByte(success);
    }
}
