package tera.gameserver.model.npc.interaction.replyes;

import org.w3c.dom.Node;
import rlib.util.VarTable;
import tera.gameserver.manager.AllianceManager;
import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.model.MessageType;
import tera.gameserver.model.inventory.Inventory;
import tera.gameserver.model.npc.Npc;
import tera.gameserver.model.npc.interaction.Link;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Inven;
import tera.gameserver.network.serverpackets.S_Join_Union;
import tera.gameserver.network.serverpackets.S_Sytem_Message;
import tera.gameserver.network.serverpackets.S_Union_State_Info;

public class ReplyJoinAlliance extends AbstractReply
{
    private int allianceId;
    private final int cost = 1000000;

    public ReplyJoinAlliance(Node node){
        super(node);
        allianceId = VarTable.newInstance(node).getInteger("id");
    }
    @Override
    public void reply(Npc npc, Player player, Link link) {
        Inventory inventory = player.getInventory();
        if(player.hasGuild() && player.getGuild().getAllianceId() == 0 && player.getGuildRank().isGuildMaster() && inventory.getMoney() > cost) {
            player.getGuild().setAllianceId(allianceId);
            inventory.subMoney(cost);
            DataBaseManager.getInstance().updateGuildAlliance(player.getGuild(), allianceId);
            AllianceManager.getInstance().getAlliance(allianceId).addGuild(player.getGuild());

           player.getGuild().joinAlliance(allianceId);
        }
        else {
            if(player.hasGuild() && ! player.getGuildRank().isGuildMaster()) {
                if(!player.getGuildRank().isGuildMaster())
                    player.sendPacket(S_Sytem_Message.getInstance(MessageType.SMT_NOT_GUILD_MASTER), true);
                if(player.getGuild().getAllianceId() != 0)
                    player.sendPacket(S_Sytem_Message.getInstance(MessageType.SMT_ALREADY_JOIN_UNION), true);
            }

            if(inventory.getMoney() < cost)
                player.sendPacket(S_Sytem_Message.getInstance(MessageType.SMT_INVEN_NOT_ENOUGH_MONEY), true);
        }
    }
}
