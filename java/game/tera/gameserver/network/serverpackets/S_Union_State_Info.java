package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Union_State_Info extends ServerPacket {

    private static final ServerPacket instance = new S_Union_State_Info();

    public static S_Union_State_Info getInstance()
    {
        return  (S_Union_State_Info) instance.newInstance();
    }


    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_UNION_STATE_INFO;
    }

    @Override
    protected final void writeImpl()
    {
        int n = 95;
        writeOpcode();
        writeShort(18);//count
        writeShort(n);//pos
        writeShort(n - 4);//pos
        writeShort(n - 2);//pos
        writeByte(1);
        writeInt(0);
        writeInt(0x5C4AD404);
        writeInt(0);
        writeInt(0x5C51531D);//exarch start date
        writeInt(0);
        writeInt(0x5C5B58E0);//exarch end date
        writeInt(0);
        writeInt(0x5C59A4F0);
        writeInt(0);
        writeInt(0x5CABE31);
        writeInt(0);
        writeLong(0);
        writeShort(1);
        writeInt(0x5C601240);//alliance conflit start time
        writeInt(0);
        writeInt(0x5C602E60);//alliance conflit end time
        writeInt(0);
        writeInt(0xFFFFFDE4);
        writeInt(0);
        writeShort(0);
        writeShort(0);

        for(int i = 0; i < 17; i++) {
            writeShort(n);
            if(i == 0)
                writeShort(0);
            else
                writeShort(n += 12);
            writeInt(i);
            writeInt(2);
        }
    }
}
