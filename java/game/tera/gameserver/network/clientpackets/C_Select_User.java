package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.PlayerManager;

/**
 * Клиентский пакет для выбора персонажа.
 *
 * @author Ronn
 */
public class C_Select_User extends ClientPacket
{
	/** ид игрока */
	private int objectId;

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

		// входим на игрока
		playerManager.selectPlayer(getOwner(), objectId);
	}
}