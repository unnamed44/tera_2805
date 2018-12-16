package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

import java.nio.ByteBuffer;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_Pet_Incubator_Info_Change extends ServerConstPacket {

    private static final S_Pet_Incubator_Info_Change instance = new S_Pet_Incubator_Info_Change();

    public static S_Pet_Incubator_Info_Change getInstance()
    {
        return instance;
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_PET_INCUBATOR_INFO_CHANGE;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer)
    {
        writeOpcode(buffer);

        writeShort(buffer, 5);
        writeShort(buffer, 22);
        writeInt(buffer, 1);
        writeInt(buffer, 0);

        writeInt(buffer, 60);
        writeShort(buffer, 0);

        writeShort(buffer, 22);
        writeShort(buffer, 34);
        writeInt(buffer, 0);
        writeInt(buffer, 0);

        writeShort(buffer, 34);
        writeShort(buffer, 46);//2E00
        writeInt(buffer, -1);
        writeInt(buffer, 0);

        writeShort(buffer, 46);
        writeShort(buffer, 58);
        writeInt(buffer, -1);
        writeInt(buffer, 0);

        writeShort(buffer, 58); //3A00
        writeShort(buffer, 70);
        writeInt(buffer, -1);
        writeInt(buffer, 0);

        writeShort(buffer, 70); //46000000
        writeShort(buffer, 0); //46000000
        writeInt(buffer, -1);
        writeInt(buffer, 0);
    }
}
