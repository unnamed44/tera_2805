package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_View_My_Guild_War;

public class C_View_My_Guild_War extends ClientPacket {
    @Override
    protected void readImpl() {

    }

    @Override
    protected void runImpl() {
       getOwner().sendPacket(S_View_My_Guild_War.getInstance());
    }
}
