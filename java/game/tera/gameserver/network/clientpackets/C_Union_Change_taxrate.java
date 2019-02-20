package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.AllianceManager;
import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.model.Alliance;
import tera.gameserver.model.MessageType;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Sytem_Message;
import tera.gameserver.network.serverpackets.S_Union_Change_Taxrate;

public class C_Union_Change_taxrate extends ClientPacket {
    private int taxrate;
    @Override
    protected void readImpl() {
        taxrate = readInt();
    }

    @Override
    protected void runImpl() {
        Player player = owner.getOwner();
        //is exarch
        if(player.getAllianceClass() == Alliance.EXARCH_RANK_ID) {
            player.sendPacket(S_Sytem_Message.getInstance(MessageType.UNION_CHANGE_TAX_RATE), true);
            player.sendPacket(S_Union_Change_Taxrate.getInstance(taxrate), true);
            AllianceManager.getInstance().getAlliance(player.getGuild().getAllianceId()).setTaxRate(taxrate);
            DataBaseManager.getInstance().saveUnionTax(player.getGuild().getAllianceId(),taxrate);
        }
    }
}
