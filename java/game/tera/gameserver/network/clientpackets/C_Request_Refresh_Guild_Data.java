package tera.gameserver.network.clientpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Empty_Guild_Window;

/**
 * Клиентский пакет с запросом информации о клане.
 *
 * @author Ronn
 */
public class C_Request_Refresh_Guild_Data extends ClientPacket
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
		if(player != null) {
			if(player.hasGuild())
				player.updateGuild();
			else
				player.sendPacket(S_Empty_Guild_Window.getInstance(), true);
		}
	}
}