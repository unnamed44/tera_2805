package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Reply_Guild_List;

public class C_Request_Guild_List extends ClientPacket {
    private int page;
    @Override
    protected void readImpl() {
        page = readInt();
    }

    @Override
    protected void runImpl() {
        getOwner().sendPacket(S_Reply_Guild_List.getInstance(page),true);
    }
}
