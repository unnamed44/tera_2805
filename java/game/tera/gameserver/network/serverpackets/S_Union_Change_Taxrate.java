package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Union_Change_Taxrate extends ServerPacket {
    private static final ServerPacket instance = new S_Union_Change_Taxrate();

    public static S_Union_Change_Taxrate getInstance(int rate)
    {
        S_Union_Change_Taxrate packet = (S_Union_Change_Taxrate) instance.newInstance();

        packet.rate = rate;

        return packet;
    }

    /** кол-во ожидания секунд */
    private int rate;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_UNION_CHANGE_TAXRATE;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeByte(0);
        writeInt(rate);
    }
}
