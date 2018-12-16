package tera.gameserver.network.clientpackets;

import tera.gameserver.network.serverpackets.S_Reign_Info;

public class C_Reign_Info extends ClientPacket {
    private int unk;
    @Override
    protected void readImpl() {
        unk = readInt();
    }

    @Override
    protected void runImpl() {
        getOwner().sendPacket(S_Reign_Info.getInstance(unk, (short)0, (short)0));
    }
}
