package tera.gameserver.network.clientpackets;

import tera.gameserver.network.model.UserClient;
import tera.gameserver.network.serverpackets.S_Can_Create_User;

/**
 * Created by Luciole on 15/06/2016.
 */
public class C_Can_Create_User extends ClientPacket {

    @Override
    public boolean isSynchronized()
    {
        return false;
    }

    @Override
    public void readImpl(){}

    @Override
    public void runImpl()
    {
        UserClient client = getOwner();
        client.sendPacket(S_Can_Create_User.getInstance(), true);
    }
}
