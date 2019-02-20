package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, описыающий обновление уровня игрока.
 *
 * @author Ronn
 */
public class S_User_Levelup extends ServerPacket
{
	private static final ServerPacket instance = new S_User_Levelup();

	public static S_User_Levelup getInstance(Player player)
	{
		S_User_Levelup packet = (S_User_Levelup) instance.newInstance();

		packet.objectId = player.getObjectId();
		packet.subId = player.getSubId();
		packet.level = player.getLevel();

		return packet;
	}

	/** ид игрока */
	private int objectId;
	/** под ид игрока */
	private int subId;
	/** уровень игрока */
	private int level;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_USER_LEVELUP;
	}

	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		writeInt(objectId); // наш ИД
		writeInt(subId);
		writeInt(level); // преобретаемый лвл
	}
}