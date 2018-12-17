package tera.gameserver.network.clientpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Update_Friend_Info;
import tera.gameserver.network.serverpackets.S_Friend_List;

/**
 * Клиентский пакет с запросом информации о друзьях.
 *
 * @author Ronn
 */
public class C_Update_Friend_Info extends ClientPacket
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
		if(player == null)
			return;

		player.sendPacket(S_Update_Friend_Info.getInstance(player), true);
		player.sendPacket(S_Friend_List.getInstance(player), true);
	}
}