package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn
 */
public class S_Auth_Failed extends ServerPacket
{
	private static final ServerPacket instance = new S_Auth_Failed();
	
	public static S_Auth_Failed getInstance()
	{
		return (S_Auth_Failed) instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_AUTH_FAILED;
	}

	@Override
	protected void writeImpl() 
	{
        writeOpcode();
        writeInt(0x01010000);
        writeInt(0x00008000);
		writeShort(0);
		writeByte(0);
	}
}