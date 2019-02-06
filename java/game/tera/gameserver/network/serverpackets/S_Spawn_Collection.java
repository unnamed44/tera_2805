package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.resourse.ResourseInstance;
import tera.gameserver.network.ServerPacketType;
import tera.util.Location;


/**
 * Серверный пакет с информацией об ресурсе.
 * 
 * @author Ronn
 */
public class S_Spawn_Collection extends ServerPacket
{
	private static final ServerPacket instance = new S_Spawn_Collection();
	
	public static S_Spawn_Collection getInstance(ResourseInstance resourse)
	{
		S_Spawn_Collection packet = (S_Spawn_Collection) instance.newInstance();
		
		packet.objectId = resourse.getObjectId();
		packet.subId = resourse.getSubId();
		packet.templateId = resourse.getTemplateId();
		packet.extractor = resourse.getTemplate().isExtractor();
		packet.disabled = resourse.getTemplate().isDisabled();
		
		resourse.getLoc(packet.loc);
		
		return packet;
	}
	
	/** обджект ид нпс */
	private int objectId;
	/** саб ид нпс */
	private int subId;
	/** ид темплейта */
	private int templateId;

	private int extractor;

	private int disabled;
	
	/** координаты нпс */
	private Location loc;
	
	public S_Spawn_Collection()
	{
		super();
		
		this.loc = new Location();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SPAWN_COLLECTION;
	}
	
	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	protected void writeImpl(ByteBuffer buffer)
	{
		writeOpcode(buffer);
		writeInt(buffer, objectId);//EF F7 04 00 обжект ид
		writeInt(buffer, subId);//00 80 04 00 саб ид
		writeInt(buffer, templateId);//90 01 00 00 ид растения/камня
		writeInt(buffer, 1);//amount
		writeFloat(buffer, loc.getX());//D1 6A 13 C6 
		writeFloat(buffer, loc.getY());//F4 99 05 C6 
		writeFloat(buffer, loc.getZ());//20 97 10 44
		writeByte(buffer, extractor);//is extractor
		writeByte(buffer, disabled);//extractor disabled
		writeInt(buffer, 0);//extractor disabled time remaining
	}
}
