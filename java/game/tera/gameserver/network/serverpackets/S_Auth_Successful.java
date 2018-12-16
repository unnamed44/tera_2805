package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Auth_Successful extends ServerPacket
{
	private static final ServerPacket instance = new S_Auth_Successful();
	
	public static ServerPacket getInstance()
	{
		return instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_AUTH_SUCCESSFUL;
	}

	@Override
	protected void writeImpl() 
	{
		writeOpcode();
		writeByte(1);
	}
}

