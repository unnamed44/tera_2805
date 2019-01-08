package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn Mod Evestu
 */
public class S_Account_Package_List extends ServerPacket
{
	private static final ServerPacket instance = new S_Account_Package_List();
	
	public static ServerPacket getInstance()
	{
		return instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_ACCOUNT_PACKAGE_LIST;
	}

	@Override
	protected void writeImpl()
	{
		
		writeOpcode();
		writeShort(0);
		writeShort(0);
	}
}