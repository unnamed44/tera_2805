package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.model.Route;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, посылающий пегаса к порталу
 * 
 * @author Ronn
 */
public class S_Sync_Pegasus_Time extends ServerPacket
{
	private static final ServerPacket instance = new S_Sync_Pegasus_Time();
	
	public static S_Sync_Pegasus_Time getInstance(Character actor, Route route, int value)
	{
		S_Sync_Pegasus_Time packet = (S_Sync_Pegasus_Time) instance.newInstance();
		
		packet.actor = actor;
		packet.route = route;
		packet.value = value;
		
		return packet;
	}
	
	/** персонаж, который нужно посадить */
	private Character actor;
	/** маршрут */
	private Route route;
	
	/** значение для последних 4х байт */
	private int value;
	
	@Override
	public void finalyze()
	{
		actor = null;
		route = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SYNC_PEGASUS_TIME;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeInt(actor.getObjectId()); //наш ид
		writeInt(actor.getSubId());//саб ид перса
		writeInt(route.getIndex());
		writeInt(0);
		writeInt(value);//движение коня
	}
}
