package tera.gameserver.network.clientpackets;

import tera.gameserver.network.model.UserClient;
import tera.gameserver.network.serverpackets.S_Cancel_Exit;

/**
 * Запрос на закрытие клиента.
 *
 * @author Ronn
 */
public class C_Cancel_Exit extends ClientPacket
{
	private UserClient client;

	@Override
	public void readImpl()
	{
		client = getOwner();
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
		client.sendPacket(S_Cancel_Exit.getInstance(), true);
	}
}