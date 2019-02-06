package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Request_Guild_Info_Before_Apply_Guild extends ServerPacket {
    private static final ServerPacket instance = new S_Request_Guild_Info_Before_Apply_Guild();
    private String guildName;

    public static S_Request_Guild_Info_Before_Apply_Guild getInstance(String guildName)
    {
        S_Request_Guild_Info_Before_Apply_Guild packet = (S_Request_Guild_Info_Before_Apply_Guild) instance.newInstance();

        packet.guildName = guildName;

        return packet;
    }

    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_REQUEST_GUILD_INFO_BEFORE_APPLY_GUILD;
    }

    @Override
    protected final void writeImpl() {
        writeOpcode();
        writeShort(11);
        writeInt(0);
        writeByte(1);
        writeString(guildName);
    }
}
