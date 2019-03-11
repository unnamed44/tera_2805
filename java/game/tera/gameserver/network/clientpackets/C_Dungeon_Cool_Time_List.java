package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Dungeon_Coll_Time_List;

public class C_Dungeon_Cool_Time_List extends ClientPacket {
    @Override
    protected void readImpl() {

    }

    @Override
    protected void runImpl() {
        owner.getOwner().sendPacket(S_Dungeon_Coll_Time_List.getInstance(), true);
    }
}
