package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_View_Inter_Party_Match_Dungoen_List;

public class C_View_Inter_Party_Match_Dungeon_List extends ClientPacket {
    @Override
    protected void readImpl() {

    }

    @Override
    protected void runImpl() {
        owner.getOwner().sendPacket(S_View_Inter_Party_Match_Dungoen_List.getInstance(owner.getOwner()), false);
    }
}
