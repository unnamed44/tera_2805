package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerPacket;

/**
 * Created by Luciole on 15/06/2016.
 */
public class S_Check_Username extends ServerPacket {

    public static final int SUCCESSFUL = 1;
    public static final int INCORRECT = 0;

    private static final ServerPacket instance = new S_Check_Username();

    public static S_Check_Username getInstance(int result)
    {
        S_Check_Username packet = (S_Check_Username) instance.newInstance();
        packet.result = result;
        return packet;
    }

    private int result;
    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_CHECK_USERNAME;
    }

    @Override
    protected void writeImpl()
    {
        writeOpcode();
        writeByte(this.result);
    }
}
