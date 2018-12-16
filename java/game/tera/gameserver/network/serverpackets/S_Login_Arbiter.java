package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Login_Arbiter extends ServerPacket
{
    private static final ServerPacket instance = new S_Login_Arbiter();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_LOGIN_ARBITER;
    }

    @Override
    protected void writeImpl()
    {
        writeOpcode();
        writeLong(0x0000001000200010L);
        writeLong(0x0000000000600000);
        writeByte(0);
        writeByte(0);
        writeByte(0);
    }
}

