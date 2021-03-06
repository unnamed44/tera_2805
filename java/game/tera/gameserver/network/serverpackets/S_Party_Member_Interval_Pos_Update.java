package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.config.MissingConfig;
import tera.gameserver.model.playable.Playable;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет с координатами члена пати.
 *
 * @author Ronn
 */
public class S_Party_Member_Interval_Pos_Update extends ServerPacket
{
	private static final ServerPacket instance = new S_Party_Member_Interval_Pos_Update();

	public static S_Party_Member_Interval_Pos_Update getInstance(Playable member)
	{
		S_Party_Member_Interval_Pos_Update packet = (S_Party_Member_Interval_Pos_Update) instance.newInstance();

		packet.objectId = member.getObjectId();
		packet.zoneId = member.getZoneId();
		packet.x = member.getX();
		packet.y = member.getY();
		packet.z = member.getZ();

		return packet;
	}

	/** ид члена пати */
	private int objectId;
	/** ид зоны, в которойо н */
	private int zoneId;

	/** его координаты */
	private float x;
	private float y;
	private float z;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_PARTY_MEMBER_INTERVAL_POS_UPDATE;
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
		writeInt(buffer, MissingConfig.SERVER_ID);// SERVER ID
		writeInt(buffer, objectId);// 5F 78 00 00
		writeFloat(buffer, x);// 66 3D B0 47
		writeFloat(buffer, y);// 86 8B AD C7
		writeFloat(buffer, z);// 00 30 92 C5
		writeInt(buffer, zoneId);// 0D 00 00 00 помойму тоже
		writeInt(buffer, 6);// 04 00 00 00
	}
}
