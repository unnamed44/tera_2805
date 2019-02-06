package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Empty_Guild_Window extends ServerPacket {

    private static final ServerPacket instance = new S_Empty_Guild_Window();

    public static S_Empty_Guild_Window getInstance()
    {
        return (S_Empty_Guild_Window) instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_EMPTY_GUILD_WINDOW;
    }

    @Override
    protected final void writeImpl() {
        writeOpcode();
    }
}
