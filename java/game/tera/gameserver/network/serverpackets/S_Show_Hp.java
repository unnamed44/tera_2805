package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет с отображением состояния хп у нпс.
 *
 * @author Ronn
 */
public class S_Show_Hp extends ServerPacket
{
	private static final ServerPacket instance = new S_Show_Hp();

	public static final int BLUE = 0;
	
	public static final int RED = 1;
	
	public static S_Show_Hp getInstance(Character target, int type)
	{
		S_Show_Hp packet = (S_Show_Hp) instance.newInstance();

		packet.objectId = target.getObjectId();
		packet.subId = target.getSubId();
		packet.hp = target.getCurrentHp() / (float) target.getMaxHp();
		packet.type = type;

		return packet;
	}

	/** ид персонажа */
	private int objectId;
	/** саб ид персонажа */
	private int subId;
	/** тип полоски */
	private int type;

	/** состояние хп */
	private float hp;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SHOW_HP;
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
		writeFloat(buffer, hp);
		writeLong(buffer, type);// 0 cиняя полоска, 1 красная
		writeByte(buffer, 0);
		writeInt(buffer, 0x00001F40);
		writeInt(buffer, 5);
	}
}