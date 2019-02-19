package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;


/**
 * Серверный пакет, сообщающий о смерти персонажа.
 *
 * @author Ronn
 */
public class S_Creature_Life extends ServerPacket
{
	private static final ServerPacket instance = new S_Creature_Life();

	/**
	 * @return новый экземпляр пакета.
	 */
	public static S_Creature_Life getInstance(Character character, boolean dead)
	{
		S_Creature_Life packet = (S_Creature_Life) instance.newInstance();

		packet.objectId = character.getObjectId();
		packet.subId = character.getSubId();

		packet.state = dead? 0 : 1;

		packet.x = character.getX();
		packet.y = character.getY();
		packet.z = character.getZ();

		return packet;
	}

	/** уникальный ид перса */
	private int objectId;
	/** под ид перса */
	private int subId;

	/** состояние персонажа */
	private int state;

	/** координаты перса */
	private float x;
	private float y;
	private float z;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CREATURE_LIFE;
	}

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	protected void writeImpl(ByteBuffer buffer)
	{
		writeOpcode(buffer);
		writeInt(buffer, objectId);
        writeInt(buffer, subId);
        writeFloat(buffer, x);
        writeFloat(buffer, y);
        writeFloat(buffer, z);
        writeByte(buffer, state);
        writeByte(buffer,0);
        writeByte(buffer, 0);
	}
}