package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

public class S_View_Union_Info extends ServerPacket {
    private static final ServerPacket instance = new S_View_Union_Info();

    private Player player;

    public static S_View_Union_Info getInstance(Player player)
    {
        S_View_Union_Info packet = (S_View_Union_Info) instance.newInstance();
        packet.player = player;

        return packet;
    }

    /** кол-во ожидания секунд */

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_VIEW_UNION_INFO;
    }

    @Override
    protected final void writeImpl()
    {
        int n = 60;
        writeOpcode();
        writeShort(3);//alliance count
        writeShort(n);//position level
        writeShort(3);//alliance count
        writeShort(n + (16*3));//position KDA
        writeInt(player.getObjectId());//playerid
        writeInt(player.getGuild().getAllianceId());//alliance id
        writeInt(player.getAllianceClass());//union class 100/200/300/400
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);//actual ranking
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);

        for(int i = 0; i < 3; i++) {
            writeShort(n);
            n += 16;
            if(i == 2)
                writeShort(0);
            else
                writeShort(n);
            writeInt(i+1);//alliance id
            writeInt(0);//alliance cumulated points
            writeInt(0);

        }

        for(int i = 0; i < 3; i++) {
            writeShort(n);
            n += 20;
            if(i == 2)
                writeShort(0);
            else
                writeShort(n);
            writeInt(i+1);//alliance id
            writeInt(0);//kill
            writeInt(0);//death
            writeInt(0);//assists
        }
    }
}
