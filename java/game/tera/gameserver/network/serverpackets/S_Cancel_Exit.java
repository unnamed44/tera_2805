package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Cancel_Exit extends ServerPacket
{	
	private static final ServerPacket instance = new S_Cancel_Exit();
	
	public static S_Cancel_Exit getInstance()
	{
		return (S_Cancel_Exit) instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CANCEL_EXIT;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeByte(0x00); // 00 - Successful.
	}
}