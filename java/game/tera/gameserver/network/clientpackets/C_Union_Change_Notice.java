package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.AllianceManager;
import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Union_Change_Notice;

public class C_Union_Change_Notice extends ClientPacket {
    private String message;
    @Override
    protected void readImpl() {
        readShort();
        message = readString();
    }

    @Override
    protected void runImpl() {
        Player player = owner.getOwner();
        //is exarch
        if(player.getAllianceClass() == 400) {
            player.sendPacket(S_Union_Change_Notice.getInstance(message), true);
            AllianceManager.getInstance().getAlliance(player.getGuild().getAllianceId()).setMessage(message);
            DataBaseManager.getInstance().saveUnionMessage(player.getGuild().getAllianceId(),message);
        }
    }
}
