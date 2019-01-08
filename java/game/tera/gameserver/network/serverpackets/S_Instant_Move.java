package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn Mod Evestu
 */
public class S_Instant_Move extends ServerPacket
{
    private static final ServerPacket instance = new S_Instant_Move();
    private static Player player;
    private static float targetX;
    private static float targetY;
    private static float targetZ;
    private static int heading;

    public static ServerPacket getInstance(Player p,float x,float y,float z,int h)
    {
        player = p;
        targetX = x;
        targetY = y;
        targetZ = z;
        heading = h;
        return instance.newInstance();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_INSTANT_MOVE;
    }

    @Override
    protected void writeImpl()
    {

        writeOpcode();
        writeInt(player.getObjectId());
        writeInt(player.getSubId());
        writeFloat(targetX); //A5A88E47
        writeFloat(targetY); //51818EC7
        writeFloat(targetZ); //80039AC4
        writeShort(heading); //0020
    }
}