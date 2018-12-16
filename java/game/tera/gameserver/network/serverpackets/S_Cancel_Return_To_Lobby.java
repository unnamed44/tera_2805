package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Cancel_Return_To_Lobby extends ServerPacket
{
	private static final ServerPacket instance = new S_Cancel_Return_To_Lobby();
	
	public static S_Cancel_Return_To_Lobby getInstance()
	{
		return (S_Cancel_Return_To_Lobby) instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CANCEL_RETURN_TO_LOBBY;
	}
	
	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeByte(0x00); // 00 - Successful.
	}
}