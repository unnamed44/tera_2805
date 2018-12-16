package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.network.model.UserClient;
import tera.gameserver.network.serverpackets.S_Check_Username;

/**
 * Created by Luciole on 15/06/2016.
 */
public class C_Check_Username extends ClientPacket {

    private String name;
    @Override
    public boolean isSynchronized()
    {
        return false;
    }

    @Override
    public void readImpl(){
        readByte();
        readByte();
        name = readString();
    }

    @Override
    public void runImpl()
    {
        UserClient client = getOwner();
        DataBaseManager dbManager = DataBaseManager.getInstance();

        if(dbManager.isFreeName(name))
            client.sendPacket(S_Check_Username.getInstance(S_Check_Username.SUCCESSFUL), true);
        else
            client.sendPacket(S_Check_Username.getInstance(S_Check_Username.INCORRECT), true);
    }
}
