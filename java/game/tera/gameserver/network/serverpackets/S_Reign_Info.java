package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

import java.nio.ByteBuffer;

public class S_Reign_Info extends ServerPacket {
    public static final ServerPacket instance = new S_Reign_Info();

    //Region id ?
    private int unk1;
    //Region status ?
    private short unk2;
    //Region status ?
    private short unk3;

    public static S_Reign_Info getInstance(int unk1, short unk2, short unk3){
        S_Reign_Info packet = (S_Reign_Info) instance.newInstance();

        packet.unk1 = unk1;
        packet.unk2 = unk2;
        packet.unk3 = unk3;

        return packet;
    }
    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_REIGN_INFO;
    }

    @Override
    public boolean isSynchronized()
    {
        return false;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer){
        writeOpcode(buffer);
        writeShort(buffer, 12);//pos
        writeShort(buffer, 14);//pos
        writeInt(buffer, unk1);
        writeShort(buffer, unk2);
        writeShort(buffer, unk3);
    }
}
