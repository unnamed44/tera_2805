package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Exit extends ServerPacket
{
	private static final ServerPacket instance = new S_Exit();
	
	public static S_Exit getInstance(int state)
	{
		S_Exit packet = (S_Exit) instance.newInstance();
		
		packet.state = state;
		
		return packet;
	}
	
	private int state;
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_EXIT;
	}

	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		
		if(state == 1)
			writeInt(0x0A000000);
	}
}