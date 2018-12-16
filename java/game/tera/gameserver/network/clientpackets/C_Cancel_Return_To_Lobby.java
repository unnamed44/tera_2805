package tera.gameserver.network.clientpackets;

import tera.gameserver.network.model.UserClient;
import tera.gameserver.network.serverpackets.S_Cancel_Return_To_Lobby;

/**
 * Запрос на выход на выбор персов.
 * 
 * @author Ronn
 */
public class C_Cancel_Return_To_Lobby extends ClientPacket
{
	private UserClient client;
	
	@Override
	public void readImpl()
	{
		client = getClient();
	}

	@Override
	public void runImpl()
	{
		if(client == null)
		{
			log.warning(this, new Exception("not found client"));
			return;
		}
		
		// ложим на отправку
		client.sendPacket(S_Cancel_Return_To_Lobby.getInstance(), true);
	}
}