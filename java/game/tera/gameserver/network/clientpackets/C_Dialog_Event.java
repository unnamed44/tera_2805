package tera.gameserver.network.clientpackets;

import tera.gameserver.model.npc.Npc;
import tera.gameserver.network.serverpackets.S_Dialog_Event;

public class C_Dialog_Event extends ClientPacket {
    private int id;
    @Override
    protected void readImpl() {
        readInt();
        id = readInt();
    }

    @Override
    protected void runImpl() {
        Npc target = owner.getOwner().getLastNpc();
        if(target == null)
            return;
        owner.getOwner().sendPacket(S_Dialog_Event.getInstance(owner.getOwner(), target, id), true);
    }
}
