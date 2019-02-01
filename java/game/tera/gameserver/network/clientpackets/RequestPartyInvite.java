package tera.gameserver.network.clientpackets;

import tera.gameserver.model.actions.ActionType;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Reply_Request_Contract;

/**
 * Клиентский пакет с приглашением в пати
 *
 * @author Ronn
 * @created 06.03.2012
 */
public class RequestPartyInvite extends ClientPacket
{
	/** ид акшена */
	private int actionId;
	/** имя того, кому предлагаем */
	private String name;
	/** лидер пати */
	private Player player;

	@Override
	public void finalyze()
	{
		player = null;
		name = null;
	}

	@Override
	protected void readImpl()
	{
		player = owner.getOwner();

		//87 82
		readShort();
		readShort();
		readShort();
		actionId = readInt();//04
		readInt();
		readInt();
		readInt();
		name = readString();//00 61 00 73 00 64 00 61 00 00 00   //
	}

	@Override
	protected void runImpl()
	{
		if(player.getParty() != null && !player.getParty().isLeader(player))
			return;

		ActionType type = ActionType.valueOf(actionId);

		player.sendPacket(S_Reply_Request_Contract.getInstance(type), true);

		if(!type.isImplemented() || player.hasLastAction())
			return;

		player.getAI().startAction(type.newInstance(player, name));
	}
}
