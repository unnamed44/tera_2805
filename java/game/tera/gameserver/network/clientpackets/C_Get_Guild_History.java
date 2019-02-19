package tera.gameserver.network.clientpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Guild_History;

public class C_Get_Guild_History extends ClientPacket {
    private int page;
    @Override
    protected void readImpl() {
        page = readInt();
    }

    @Override
    protected void runImpl() {
        Player player = owner.getOwner();
        if(player.hasGuild())
            player.sendPacket(S_Guild_History.getInstance(player), true);
    }
}
