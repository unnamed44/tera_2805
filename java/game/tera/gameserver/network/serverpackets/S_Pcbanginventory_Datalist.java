package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn Mod Evestu
 */
public class S_Pcbanginventory_Datalist extends ServerPacket
{
	private static final ServerPacket instance = new S_Pcbanginventory_Datalist();
	
	public static ServerPacket getInstance()
	{
		return instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_PCBANGINVENTORY_DATALIST;
	}

	@Override
	protected void writeImpl()
	{
		
		writeOpcode();
		writeInt(0);
		writeInt(0);
	}
}