package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Union_Summary;

public class C_Union_Summary extends ClientPacket {
    @Override
    protected void readImpl() {

    }

    @Override
    protected void runImpl() {
        getOwner().sendPacket(S_Union_Summary.getInstance());
    }
}
