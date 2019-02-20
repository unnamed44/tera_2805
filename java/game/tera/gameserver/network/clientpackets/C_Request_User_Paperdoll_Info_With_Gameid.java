package tera.gameserver.network.clientpackets;

import tera.gameserver.model.World;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_User_Paperdoll_info;

public class C_Request_User_Paperdoll_Info_With_Gameid extends ClientPacket {
    private int objectId;
    @Override
    protected void readImpl() {
        objectId = readInt();
    }

    @Override
    protected void runImpl() {
        Player player = World.getPlayer(objectId);
        if(player != null)
            owner.getOwner().sendPacket(S_User_Paperdoll_info.getInstance(player), true);
    }
}
