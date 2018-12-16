package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerPacket;

/**
 * Created by Luciole on 15/06/2016.
 */
public class S_Create_User extends ServerPacket {

    public static final int SUCCESS= 1;
    public static final int ERROR = 0;

    private static final ServerPacket instance = new S_Create_User();

    public static S_Create_User getInstance(int result)
    {
        S_Create_User packet = (S_Create_User) instance.newInstance();
        packet.result = result;
        return packet;
    }

    private int result;
    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_CREATE_USER;
    }

    @Override
    protected void writeImpl()
    {
        writeOpcode();
        writeByte(this.result);
    }
}
