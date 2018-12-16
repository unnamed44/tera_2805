package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Guard_Pk_Policy;

public class C_Guard_Pk_Policy extends ClientPacket {
    @Override
    protected void readImpl() {

    }

    @Override
    protected void runImpl() {
        getOwner().sendPacket(S_Guard_Pk_Policy.getInstance());
    }
}
