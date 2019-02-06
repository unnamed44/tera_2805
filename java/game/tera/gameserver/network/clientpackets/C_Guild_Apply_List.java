package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Guild_Apply_List;

public class C_Guild_Apply_List extends ClientPacket {
    @Override
    protected void readImpl() {

    }

    @Override
    protected void runImpl() {
        if(owner.getOwner().hasGuild())
            getOwner().sendPacket(S_Guild_Apply_List.getInstance(owner.getOwner().getGuild()));
    }
}
