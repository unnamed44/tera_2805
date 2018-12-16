package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Account_Benefit_List extends ServerPacket
{
	private static final ServerPacket instance = new S_Account_Benefit_List();
	
	public static S_Account_Benefit_List getInstance()
	{
		return (S_Account_Benefit_List) instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.TEST_26;
	}

	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		writeInt(0);
		writeByte(0);
	}
}