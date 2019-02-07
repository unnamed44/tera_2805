package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Guild_History;

public class C_Get_Guild_History extends ClientPacket {
    private int page;
    @Override
    protected void readImpl() {
        page = readInt();
    }

    @Override
    protected void runImpl() {
        owner.getOwner().sendPacket(S_Guild_History.getInstance(owner.getOwner()), true);
    }
}
