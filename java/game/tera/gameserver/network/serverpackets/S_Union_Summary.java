package tera.gameserver.network.serverpackets;

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
        //todo
        writeOpcode(buffer);
    }
}
