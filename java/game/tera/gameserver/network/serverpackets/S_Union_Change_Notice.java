package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Union_Change_Notice extends ServerPacket {
    private static final ServerPacket instance = new S_Union_Change_Notice();

    public static S_Union_Change_Notice getInstance(String message)
    {
        S_Union_Change_Notice packet = (S_Union_Change_Notice) instance.newInstance();

        packet.message = message;

        return packet;
    }

    /** кол-во ожидания секунд */
    private String message;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_UNION_CHANGE_NOTICE;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeShort(7);
        writeByte(1);//success ?
        writeString(message);
    }
}
