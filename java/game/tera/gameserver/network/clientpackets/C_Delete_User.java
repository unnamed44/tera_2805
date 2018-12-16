package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.PlayerManager;

/**
 * Запрос на удаление игрока.
 *
 * @author Ronn
 */
public class C_Delete_User extends ClientPacket
{
	/** ид игрока */
	private int objectId;

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	public void readImpl()
	{
		objectId = readInt();
	}

	@Override
	public void runImpl()
	{
		// получаем менеджера игроков
		PlayerManager playerManager = PlayerManager.getInstance();

		// выполняем удаление
		playerManager.removePlayer(getOwner(), objectId);
	}
}