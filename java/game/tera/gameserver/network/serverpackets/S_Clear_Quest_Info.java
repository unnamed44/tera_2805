package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.network.ServerPacketType;

public class S_Clear_Quest_Info extends ServerConstPacket
{
	private static final S_Clear_Quest_Info instance = new S_Clear_Quest_Info();

	public static S_Clear_Quest_Info getInstance()
	{
		return instance;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CLEAR_QUEST_INFO;
	}

	@Override
	protected void writeImpl(ByteBuffer buffer)
	{
		writeOpcode(buffer);
	}
}