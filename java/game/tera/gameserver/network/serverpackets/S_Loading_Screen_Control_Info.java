package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn
 */
public class S_Loading_Screen_Control_Info extends ServerPacket
{
	private static final ServerPacket instance = new S_Loading_Screen_Control_Info();
	
	public static ServerPacket getInstance()
	{
		return instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_LOADING_SCREEN_CONTROL_INFO;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeLong(0x0000000200000001L);
		/*writeShort(0);
		writeByte(1); */
	}
}