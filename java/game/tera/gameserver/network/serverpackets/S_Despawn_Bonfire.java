package tera.gameserver.network.serverpackets;

import tera.gameserver.model.worldobject.WorldObject;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет с инфой об удалении объекта.
 * 
 * @author Ronn
 */
public class S_Despawn_Bonfire extends ServerPacket
{
	private static final ServerPacket instance = new S_Despawn_Bonfire();
	
	public static S_Despawn_Bonfire getInstance(WorldObject object)
	{
		S_Despawn_Bonfire packet = (S_Despawn_Bonfire) instance.newInstance();
		
		packet.objectId = object.getObjectId();
		packet.subId = object.getSubId();
		
		return packet;
	}
	
	/** обджект ид объекта */
	private int objectId;
	/** саб ид объекта */
	private int subId;
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_DESPAWN_BONFIRE;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeInt(objectId);//4A 50 0C 00 обжект ид
		writeInt(subId);//00 80 0E 00 саб ид  
		writeByte(1);//01    
	}
}
