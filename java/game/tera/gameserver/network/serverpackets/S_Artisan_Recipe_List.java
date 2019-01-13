package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerPacket;

public class S_Artisan_Recipe_List extends ServerPacket {

    private static final ServerPacket instance = new S_Artisan_Recipe_List();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_ARTISAN_RECIPE_LIST;
    }

    @Override
    protected void writeImpl()
    {

        writeOpcode();
        writeInt(0);
        writeByte(buffer, 0);
        writeByte(buffer, 1);

    }
}
