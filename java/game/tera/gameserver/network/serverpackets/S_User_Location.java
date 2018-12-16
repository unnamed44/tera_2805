package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.Character;
import tera.gameserver.model.MoveType;
import tera.gameserver.network.ServerPacketType;


/**
 * Серверный пакет о перемещении персонажа с одной точки в другую
 *
 * @author Ronn
 */
public class S_User_Location extends ServerPacket
{
	private static final ServerPacket instance = new S_User_Location();

	public static S_User_Location getInstance(Character actor, MoveType type, float x, float y, float z, int heading, float targetX, float targetY, float targetZ)
	{
		S_User_Location packet = (S_User_Location) instance.newInstance();

		packet.objectId = actor.getObjectId();
		packet.subId = actor.getSubId();
		packet.speed = actor.getRunSpeed();
		packet.type = type;
		packet.x = x;
		packet.y = y;
		packet.z = z;
		packet.heading = heading;
		packet.targetX = targetX;
		packet.targetY = targetY;
		packet.targetZ = targetZ;

		return packet;
	}

	/** тип перемещения */
	private MoveType type;

	/** стартовая точка */
	private float x;
	private float y;
	private float z;

	/** обджект ид */
	private int objectId;
	/** саб ид */
	private int subId;
	/** направление */
	private int heading;
	/** скорость */
	private int speed;

	/** конечная точка */
	private float targetX;
	private float targetY;
	private float targetZ;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_USER_LOCATION;
	}

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	protected final void writeImpl(ByteBuffer buffer)
	{
		writeOpcode(buffer);
		//ид обьекта
		writeInt(buffer, objectId);
		writeInt(buffer, subId);

		//начальная точка
		writeFloat(buffer, x);
		writeFloat(buffer, y);
		writeFloat(buffer, z);
		//поворот
		writeShort(buffer, heading);
		//скорость
		writeShort(buffer, speed);

		//конечная точка
		writeFloat(buffer, targetX);
		writeFloat(buffer, targetY);
		writeFloat(buffer, targetZ);

		//тип движения
		writeByte(buffer, type.ordinal());
		writeInt(buffer, 0);
	}
}