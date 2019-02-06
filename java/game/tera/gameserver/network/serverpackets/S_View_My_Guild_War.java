package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_View_My_Guild_War extends ServerPacket {
    private static final ServerPacket instance = new S_View_My_Guild_War();

    public static S_View_My_Guild_War getInstance()
    {
        return (S_View_My_Guild_War) instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_VIEW_MY_GUILD_WAR;
    }

    @Override
    protected final void writeImpl() {
        writeOpcode();
        writeInt(0);
    }

}
