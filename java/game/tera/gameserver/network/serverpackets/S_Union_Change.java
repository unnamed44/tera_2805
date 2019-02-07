package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Union_Change extends ServerPacket {
    private static final ServerPacket instance = new S_Union_Change();

    public static S_Union_Change getInstance()
    {
        S_Union_Change packet = (S_Union_Change) instance.newInstance();
        packet.value = 1;
        packet.alliance = 2;
        return packet;
    }

    /** кол-во ожидания секунд */
    private int value;
    private int alliance;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_UNION_CHANGE;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(464877);
        writeByte(0);
        writeShort(128);
        writeByte(0);
        writeInt(alliance);
        writeLong(400);
        writeByte(value);
    }
}
