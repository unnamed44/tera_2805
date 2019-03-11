package tera.gameserver.network.serverpackets;

import tera.gameserver.model.dungeons.DungeonList;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

import java.util.List;

public class S_View_Inter_Party_Match_Dungoen_List extends ServerPacket {
    private static final ServerPacket instance = new S_View_Inter_Party_Match_Dungoen_List();

    public static S_View_Inter_Party_Match_Dungoen_List getInstance(Player player)
    {
        S_View_Inter_Party_Match_Dungoen_List packet = (S_View_Inter_Party_Match_Dungoen_List) instance.newInstance();

        packet.player = player;

        return packet;
    }

    /** кол-во ожидания секунд */
    private Player player;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_VIEW_INTER_PARTY_MATCH_DUNGOEN_LIST;
    }

    @Override
    protected final void writeImpl()
    {
        int n = 11;
        List<DungeonList> dungeons = DungeonList.getDungeonAvailableTroughtLevel(player.getLevel());

        writeOpcode();
        writeShort(dungeons.size());
        writeShort(n);
        writeShort(1);
        writeByte(1);

        for(int i = dungeons.size() - 1; i >= 0; i--) {
            writeShort(n);
            writeShort((i == 0) ? 0 : (n += 19));
            writeInt(0);
            writeInt(dungeons.get(i).getDungeonId());
            writeByte(0);
            writeInt(-1);// -1 OK / 0 item level too low
            writeByte(0);
            writeByte(0);//entries ?
        }
    }
}
