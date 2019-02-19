package tera.gameserver.network.clientpackets;

import tera.gameserver.model.actions.ActionType;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Reply_Request_Contract;

public class C_Request_Contract extends ClientPacket {

    private Player player;
    private ActionType actionType;

    @Override
    protected void readImpl() {
        player = owner.getOwner();

        readShort();
        readShort();
        readShort();
        actionType = ActionType.valueOf(readInt());//04
        readInt();
        readInt();
        readInt();
        if(actionType == ActionType.BIND_ITEM) {
            readShort();
            name = String.valueOf(readInt());
        }
        else
            name = readString();//00 61 00 73 00 64 00 61 00 00 00   //
    }

    @Override
    protected void runImpl() {
        switch (actionType)
        {
            case CREATE_GUILD:
            {
                break;
            }
            case BIND_ITEM:
            {
                player.sendPacket(S_Reply_Request_Contract.getInstance(actionType), true);
                if(!actionType.isImplemented() || player.hasLastAction())
                    return;

                player.getAI().startAction(actionType.newInstance(player, name));
                break;
            }
            case PARTY: {
                if(player.getParty() != null && !player.getParty().isLeader(player))
                    return;
                player.sendPacket(S_Reply_Request_Contract.getInstance(actionType), true);

                if(!actionType.isImplemented() || player.hasLastAction())
                    return;

                player.getAI().startAction(actionType.newInstance(player, name));
            }
            case ENCHANT_ITEM:
                player.sendPacket(S_Reply_Request_Contract.getInstance(actionType), true);
                if(!actionType.isImplemented() || player.hasLastAction())
                    return;

                player.getAI().startAction(actionType.newInstance(player, ""));
                break;
            default:
                break;
        }
    }
}
