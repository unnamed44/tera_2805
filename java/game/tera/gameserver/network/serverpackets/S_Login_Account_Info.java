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
		writeLong(0x00000000005A9A4DL);
		writeLong(0x006E0061006C0050L);
		writeShort(0x0065);
		writeShort(0x0074);
		writeShort(0x0041);
		writeShort(0x0056);
		writeShort(0);
	}
}