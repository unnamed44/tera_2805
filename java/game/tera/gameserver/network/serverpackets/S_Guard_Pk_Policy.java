package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

import java.nio.ByteBuffer;

public class S_Guard_Pk_Policy extends ServerPacket {
    public static final ServerPacket instance = new S_Guard_Pk_Policy();

    public static S_Guard_Pk_Policy getInstance(){
        S_Guard_Pk_Policy packet = (S_Guard_Pk_Policy) instance.newInstance();

        return packet;
    }
    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_GUARD_PK_POLICY;
    }

    @Override
    public boolean isSynchronized()
    {
        return false;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer){
        writeOpcode(buffer);
        writeByte(buffer, 1);
    }
}
