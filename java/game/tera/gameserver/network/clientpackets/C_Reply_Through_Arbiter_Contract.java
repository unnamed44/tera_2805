package tera.gameserver.network.clientpackets;

import tera.gameserver.model.actions.Action;
import tera.gameserver.model.playable.Player;

/**
 * Подтверждение согласия на акшен
 *
 * @author Ronn
 * @created 07.03.2012
 */
public class C_Reply_Through_Arbiter_Contract extends ClientPacket
{
	/** потвердивший игрок */
	private Player player;
	/** сам акшен */
	private Action action;

	/** ответ 01 дв 02 нет */
	private int answer;
	/** обджект ид инициатора */
	private int objectId;

	@Override
	public void finalyze()
	{
		player = null;
		action = null;
	}

	@Override
	protected void readImpl()
	{
		player = owner.getOwner();

		readShort();
		readInt();
		objectId = readInt();
		readInt();
		answer = readInt();
		readString();
	}

	@Override
	protected void runImpl()
	{
		if(player == null)
			return;

		action = player.getLastAction();

		if(action == null || action.getObjectId() != objectId)
			return;

		if(answer == 1)
			action.assent(player);
		else if(answer == 2)
			action.cancel(player);
	}
}
