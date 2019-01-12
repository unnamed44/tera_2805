package tera.gameserver.network.serverpackets;

import rlib.util.Strings;
import tera.gameserver.network.ServerPacketType;

import java.nio.ByteBuffer;

public class S_Union_Summary extends ServerPacket {
    private static final ServerPacket instance = new S_Union_Summary();

    public static S_Union_Summary getInstance(){
        S_Union_Summary packet = (S_Union_Summary) instance.newInstance();

        return packet;
    }

    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_UNION_SUMMARY;
    }

    @Override
    public boolean isSynchronized()
    {
        return false;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer){
        String[] names = new String[3];
        names[0] = "Lancer1";
        names[1] = "Lancer2";
        names[2] = "Lancer3";

        int[] unk1 = new int[3];
        unk1[0] = 1249;
        unk1[1] = 2827;
        unk1[2] = 1542;

        int[] bonuses = new int[3];
        bonuses[0] = 10;
        bonuses[1] = 62;
        bonuses[2] = 27;

        int[] unk3 = new int[3];
        unk3[0] = 2;
        unk3[1] = 11;
        unk3[2] = 7;

        writeOpcode(buffer);
        writeShort(buffer, 3);//nbr of union
        writeShort(buffer, 12);
        writeInt(buffer, 0);
        int pos = 12;
        for(int i = 0; i < 3; i++) {
            int namePos = pos + 53;
            writeShort(buffer, pos);//current pos
            pos = 67 * (i+1) + Strings.length(names[i]);
            if(i == 1)
                pos += 4;
            if(i == 2)
                writeShort(buffer, 0);
            else
                writeShort(buffer, pos);//next
            writeShort(buffer, namePos);
            writeShort(buffer, namePos + Strings.length(names[1]));
            writeInt(buffer, i+1);
            writeInt(buffer, unk3[i]);
            writeInt(buffer, 7);//%
            if(i == 2)
                writeByte(buffer, 1);//vault access
            else
                writeByte(buffer, 0);
            writeInt(buffer, bonuses[i]);
            writeInt(buffer, 0);//bonuses activation time
            writeInt(buffer, unk1[i]);//E1 04
            writeInt(buffer, 1);//previous exarch number
            writeLong(buffer, 0);
            writeByte(buffer, 0);
            writeInt(buffer, 168750);// 2E 93 02 00
            writeShort(buffer, 0);
            writeByte(buffer,0);
            writeString(buffer, names[i]);
            writeShort(buffer, 0);
        }
    }
}
