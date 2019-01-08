package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn Mod Evestu
 */
public class S_Notify_Change_Class_And_Elite extends ServerPacket
{
	private static final ServerPacket instance = new S_Notify_Change_Class_And_Elite();
	
	public static ServerPacket getInstance()
	{
		return instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_NOTIFY_CHANGE_CLASS_AND_ELITE;
	}

	@Override
	protected void writeImpl()
	{
		
		writeOpcode();
		writeInt(0);
		writeInt(0);
	}
}