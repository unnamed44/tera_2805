package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Union_My_Union;

public class C_Union_My_Union extends ClientPacket {
    @Override
    protected void readImpl() {

    }

    @Override
    protected void runImpl() {
        owner.getOwner().sendPacket(S_Union_My_Union.getInstance(owner.getOwner().getGuild().getAllianceId()), true);
    }
}
