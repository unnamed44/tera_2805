package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerConstPacket;

import java.nio.ByteBuffer;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_My_Description extends ServerConstPacket
{
    private static final S_My_Description instance = new S_My_Description();

    public static S_My_Description getInstance(Player player)
    {
        S_My_Description packet = (S_My_Description) instance.newInstance();
        packet.player = player;

        return packet;
    }

    private Player player;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_MY_DESCRIPTION;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer)
    {
        writeOpcode(buffer);
        writeShort(buffer,6);
        writeString(buffer, player.getDescription());//description
    }
}
