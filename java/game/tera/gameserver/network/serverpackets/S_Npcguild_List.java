package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_Npcguild_List extends ServerPacket {

    private static final ServerPacket instance = new S_Npcguild_List();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_NPC_GUILD_LIST;
    }

    @Override
    protected void writeImpl()
    {

        writeOpcode();
        writeInt(0);
        writeInt(0x0FAC2722);
        writeInt(0x00008000);
    }
}
