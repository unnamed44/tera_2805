package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn
 */
public class S_Play_Movie extends ServerPacket
{
	private static final ServerPacket instance = new S_Play_Movie();
	
	public static S_Play_Movie getInstance(int id)
	{
		S_Play_Movie packet = (S_Play_Movie) instance.newInstance();
		
		packet.id = id;
		
		return packet;
	}
	
	/** ид ролика */
	private int id;
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_PLAY_MOVIE;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeInt(id);//02 00 00 00     Номер Ролика
	}
}
