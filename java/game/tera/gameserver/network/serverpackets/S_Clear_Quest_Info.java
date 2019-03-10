package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn
 */
public class S_Clear_Quest_Info extends ServerPacket
{
	private static final ServerPacket instance = new S_Clear_Quest_Info();
	
	public static S_Clear_Quest_Info getInstance()
	{
		return (S_Clear_Quest_Info) instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CLEAR_QUEST_INFO;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
	}
}
