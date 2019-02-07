package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_View_Union_Info;

public class C_View_Union_Info extends ClientPacket {
    private String name;
    private int guildId;
    @Override
    protected void readImpl() {
        readShort();
        name = readString();
        readInt();
        guildId = readInt();
    }

    @Override
    protected void runImpl() {
        owner.getOwner().sendPacket(S_View_Union_Info.getInstance(owner.getOwner()), true);
    }
}
