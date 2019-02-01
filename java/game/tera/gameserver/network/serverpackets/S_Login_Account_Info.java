package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn Mod Evestu
 */
public class S_Login_Account_Info extends ServerPacket
{
	private static final ServerPacket instance = new S_Login_Account_Info();
	
	public static ServerPacket getInstance()
	{
		return instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_LOGIN_ACCOUNT_INFO;
	}

	@Override
	protected void writeImpl()
	{
		
		writeOpcode();
		writeShort(0x0E00);
		writeShort(3672);
		writeShort(29449);
		writeInt(0);
		writeString("GameDB");
	}
}