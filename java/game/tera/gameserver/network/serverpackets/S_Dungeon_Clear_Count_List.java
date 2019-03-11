package tera.gameserver.network.serverpackets;

import tera.gameserver.config.MissingConfig;
import tera.gameserver.model.dungeons.DungeonList;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

import java.util.List;

public class S_Dungeon_Clear_Count_List extends ServerPacket {
    private static final ServerPacket instance = new S_Dungeon_Clear_Count_List();

    public static S_Dungeon_Clear_Count_List getInstance(Player player)
    {
        S_Dungeon_Clear_Count_List packet = (S_Dungeon_Clear_Count_List) instance.newInstance();

        packet.player = player;

        return packet;
    }

    /** кол-во ожидания секунд */
    private Player player;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_DUNGEON_CLEAR_COUNT_LIST;
    }

    @Override
    protected final void writeImpl()
    {
        List<DungeonList> dungeons = DungeonList.getDungeonAvailableTroughtLevel(player.getLevel());
        int n = 12;
        writeOpcode();
        writeShort(dungeons.size());
        writeShort(n);
        writeInt(player.getObjectId());
        for(int i = dungeons.size() - 1; i >= 0; i--) {
            int clearcount = player.getDungeonClearCount(dungeons.get(i).getDungeonId());
            writeShort(n);
            writeShort((i == 0) ? 0 : (n += 13));
            writeInt(dungeons.get(i).getDungeonId());
            writeInt(clearcount);
            writeByte((clearcount < MissingConfig.DUNGEON_CLEAR_COUNT_ROOKIE) ? 1 : 0);
        }
    }
}
