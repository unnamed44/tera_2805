package tera.gameserver.network.clientpackets;

import tera.gameserver.model.World;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Dungeon_Clear_Count_List;

public class C_Dungeon_Clear_Count_List extends ClientPacket {
    private String name;
    @Override
    protected void readImpl() {
        readShort();
        name = readString();
    }

    @Override
    protected void runImpl() {
        Player player = World.getPlayer(name);
        owner.getOwner().sendPacket(S_Dungeon_Clear_Count_List.getInstance(player), false);
    }
}
