package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет для посадки персонажа на пегас
 *
 * @author Ronn
 */
public class S_Start_Pegasus extends ServerPacket
{
	private static final ServerPacket instance = new S_Start_Pegasus();

	public static S_Start_Pegasus getInstance(Character actor)
	{
		S_Start_Pegasus packet = (S_Start_Pegasus) instance.newInstance();

		packet.actor = actor;

		return packet;
	}

	/** персонаж, который нужно посадить */
	private Character actor;

	@Override
	public void finalyze()
	{
		actor = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_START_PEGASUS;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeInt(actor.getObjectId()); // наш ид
		writeInt(actor.getSubId());//саб ид перса
		writeInt(1);
	}
}
