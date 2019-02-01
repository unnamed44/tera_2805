package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет для окраски ника персонажа.
 *
 * @author Ronn
 */
public class S_Change_Relation extends ServerPacket
{
	/** обычный */
	public static final int COLOR_NORMAL = 1;
	/** синий */
	public static final int COLOR_BLUE = 2;
	/** красный */
	public static final int COLOR_RED = 3;
	/** оранжевый */
	public static final int COLOR_ORANGE = 4;
	/** красный для пвп */
	public static final int COLOR_RED_PVP = 5;
	/** зеленый */
	public static final int COLOR_GREEN = 6;
	/** голубой */
	public static final int COLOR_LIGHT_BLUE = 7;
	/** еще какой-то красный */
	public static final int COLOR_RED_STATUS = 8;

	// 9 голубой, 10 насыщенно зеленый,
		// 11 бледно голубой, 12 - темно серый, 14 - белый,
		// 15 - красноватый, 16 - темно красный, 19 - ярко салатовый
		//

	private static final ServerPacket instance = new S_Change_Relation();

	public static S_Change_Relation getInstance(int color, Character character)
	{
		S_Change_Relation packet = (S_Change_Relation) instance.newInstance();

		packet.color = color;
		packet.objectId = character.getObjectId();
		packet.subId = character.getSubId();

		return packet;
	}

	/** обджект ид персонажа */
	private int objectId;
	/** саб ид персонажа */
	private int subId;
	/** цвет */
	private int color;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CHANGE_RELATION;
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
		writeInt(buffer, color);
	}
}