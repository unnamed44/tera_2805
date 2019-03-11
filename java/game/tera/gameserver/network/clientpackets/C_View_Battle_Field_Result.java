package tera.gameserver.network.clientpackets;

import tera.gameserver.model.World;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_View_Battle_Field_Result;

public class C_View_Battle_Field_Result extends ClientPacket {
    private String name;
    @Override
    protected void readImpl() {
        readShort();
        name = readString();
    }

    @Override
    protected void runImpl() {
        Player player = World.getPlayer(name);
        owner.getOwner().sendPacket(S_View_Battle_Field_Result.getInstance(player), false);
    }
}
