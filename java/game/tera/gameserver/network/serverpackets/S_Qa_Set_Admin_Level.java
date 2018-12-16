package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

import java.nio.ByteBuffer;

public class S_Qa_Set_Admin_Level extends ServerConstPacket {

    private static final S_Qa_Set_Admin_Level instance = new S_Qa_Set_Admin_Level();

    public static S_Qa_Set_Admin_Level getInstance()
    {
        return instance;
    }
    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_QA_SET_ADMIN_LEVEL;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer)
    {
        writeOpcode(buffer);
        writeInt(buffer, 1);
    }
}
