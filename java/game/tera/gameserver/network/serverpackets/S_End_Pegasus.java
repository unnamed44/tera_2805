package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакт для того, чтобы персонаж мог слезть с пегаса
 * 
 * @author Ronn
 *
 */
public class S_End_Pegasus extends ServerPacket
{
	private static final ServerPacket instance = new S_End_Pegasus();
	
	public static S_End_Pegasus getInstance(Character actor)
	{
		S_End_Pegasus packet = (S_End_Pegasus) instance.newInstance();
		
		packet.actor = actor;
		
		return packet;
	}
	
	/** персонаж */
	private Character actor;
	
	@Override
	public void finalyze()
	{
		actor = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_END_PEGASUS;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeInt(actor.getObjectId());//обжект айди наш
		writeInt(actor.getSubId()); // саб айди нашь	
	}
}
