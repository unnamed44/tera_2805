package tera.gameserver.network.clientpackets;

import tera.gameserver.network.clientpackets.ClientPacket;
import tera.gameserver.network.serverpackets.S_Trade_Broker_Highest_Item_Level;

/**
 * Created by Luciole on 24/06/2016.
 */
public class C_Trade_Broker_Highest_Item_Level extends ClientPacket {
    @Override
    protected void readImpl() {

    }

    @Override
    protected void runImpl() {
        //todo: set ilvl
        getOwner().sendPacket(S_Trade_Broker_Highest_Item_Level.getInstance(450));
    }
}
