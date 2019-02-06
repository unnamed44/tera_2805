package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Answer_Interactive;

public class C_Ask_Interactive extends ClientPacket {
    private String name;

    @Override
    protected void readImpl() {
        readShort();
        readInt();
        readInt();//serverId
        name = readString();
    }

    @Override
    protected void runImpl() {
        getOwner().sendPacket(S_Answer_Interactive.getInstance(name), true);
    }
}
