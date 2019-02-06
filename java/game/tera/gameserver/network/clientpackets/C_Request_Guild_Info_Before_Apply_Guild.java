package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Request_Guild_Info_Before_Apply_Guild;

public class C_Request_Guild_Info_Before_Apply_Guild extends ClientPacket {
    private String guildName;

    @Override
    protected void readImpl() {
        readShort();
        readInt();
        guildName = readString();
    }

    @Override
    protected void runImpl() {
        getOwner().sendPacket(S_Request_Guild_Info_Before_Apply_Guild.getInstance(guildName));
    }
}
