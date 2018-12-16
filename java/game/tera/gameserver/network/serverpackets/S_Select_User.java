package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет подтверждающий выбор персонажа для входа.
 *
 * @author Ronn
 * @created 31.03.2012
 */
public class S_Select_User extends ServerConstPacket
{
	private static final S_Select_User instance = new S_Select_User();

	public static S_Select_User getInstance()
	{
		return instance;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SELECT_USER;
	}

	@Override
	protected void writeImpl(ByteBuffer buffer)
	{
		writeOpcode(buffer);
		writeByte(buffer, 1);
		writeInt(buffer, 0);
		writeInt(buffer, 0);
	}
}