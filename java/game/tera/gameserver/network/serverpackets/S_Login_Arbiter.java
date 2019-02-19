package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Login_Arbiter extends ServerPacket
{
    private static final ServerPacket instance = new S_Login_Arbiter();
    private int language;

    public static ServerPacket getInstance(int language)
    {
        S_Login_Arbiter packet = (S_Login_Arbiter) instance.newInstance();
        packet.language = language;
        return packet;
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
        writeByte(1);//success
        writeByte(0);
        writeShort(2);
        writeShort(1);
        writeInt(0);
        writeInt(language);
        writeByte(1);//pvp disable
        writeShort(0);
        writeShort(0);
    }
}

