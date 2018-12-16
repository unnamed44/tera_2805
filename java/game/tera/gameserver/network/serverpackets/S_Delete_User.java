package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет с результатом попытки удаления персонажа.
 *
 * @author Ronn
 * @created 31.03.2012
 */
public class S_Delete_User extends ServerPacket
{
	/** успешно удален */
	public static final int SUCCESSFUL = 1;
	/** не успешно удален */
	public static final int FAILED = 0;

	private static final ServerPacket instance = new S_Delete_User();

	public static S_Delete_User getInstance(int result)
	{
		S_Delete_User packet = (S_Delete_User) instance.newInstance();

		packet.result = result;

		return packet;
	}

	/** результат удаления */
	private int result;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_DELETE_PLAYER;
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
		writeByte(buffer, result);
	}
}