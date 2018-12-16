package tera.gameserver.network.clientpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Return_To_Lobby;

/**
 * Клиентский пакет, запрашивающий выход из игры
 *
 * @author Ronn
 */
public class C_Return_To_Lobby extends ClientPacket
{
	/** игрок */
	private Player player;

	@Override
	public void finalyze()
	{
		player = null;
	}

	@Override
	public void readImpl()
	{
		player = owner.getOwner();
	}

	@Override
	public void runImpl()
	{
		if(player == null || player.isBattleStanced())
			return;

		owner.sendPacket(S_Return_To_Lobby.getInstance(0), true);//как настроите таймер поставить 1

		player.deleteMe();

		player.setClient(null);
		player.setConnected(false);
	}
}