package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Check_Version extends ServerPacket
{
	public static final int SUCCESSFUL = 1;
	public static final int INCORRECT = 2;

	private static final ServerPacket instance = new S_Check_Version();

	public static S_Check_Version getInstance(int result)
	{
		S_Check_Version packet = (S_Check_Version) instance.newInstance();

		packet.result = result;

		return packet;
	}

	/** результат */
	private int result;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CHECK_VERSION;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeByte((result == SUCCESSFUL) ? 1 : 0);
	}
}