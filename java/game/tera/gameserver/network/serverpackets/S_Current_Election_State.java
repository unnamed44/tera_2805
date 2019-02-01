package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerPacket;

/**
 * Created by Luciole on 23/06/2016.
 */
public class S_Current_Election_State extends ServerPacket {

    private static final ServerPacket instance = new S_Current_Election_State();

    public static ServerPacket getInstance()
    {
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_CURRENT_ELECTION_STATE;
    }

    @Override
    protected void writeImpl()
    {
        writeOpcode();
        writeInt(0);
        writeInt(0); // sometimes 19
        writeInt(0);
        writeShort(0);
        writeShort(51228);
        writeShort(23651);
        writeShort(0);
    }
}
