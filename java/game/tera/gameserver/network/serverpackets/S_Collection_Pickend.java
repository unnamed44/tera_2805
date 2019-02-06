package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.Character;
import tera.gameserver.model.playable.Player;
import tera.gameserver.model.resourse.ResourseInstance;
import tera.gameserver.network.ServerPacketType;


/**
 * Серверный пакет со стартом сбора ресурсов.
 * 
 * @author Ronn
 */
public class S_Collection_Pickend extends ServerPacket
{
	private static final ServerPacket instance = new S_Collection_Pickend();
	
	/** сбор был успешен */
	public static final int SUCCESSFUL = 3;
	/** сбор был просран */
	public static final int FAILED = 2;
	/** сбор был прерван */
	public static final int INTERRUPTED = 0;
	
	public static S_Collection_Pickend getInstance(Character collector, ResourseInstance resourse, int result)
	{
		S_Collection_Pickend packet = (S_Collection_Pickend) instance.newInstance();
		
		packet.collectorId = collector.getObjectId();
		packet.collectorSubId = collector.getSubId();
		packet.resourseId = resourse.getObjectId();
		packet.resourseSubId = resourse.getSubId();
		packet.result = result;
		packet.fatigability = ((Player) collector).getAccount().getFatigability();
		
		return packet;
	}
	
	/** обджект ид сборщика */
	private int collectorId;
	/** саб ид сборщика */
	private int collectorSubId;
	/** обджект ид ресурса */
	private int resourseId;
	/** саб ид ресурса */
	private int resourseSubId;
	/** результат */
	private int result;

	private int fatigability;
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_COLLECTION_PICKEND;
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
		writeInt(buffer, collectorId);//AA 6C 0D 00 //обжект ид кто собирает
		writeInt(buffer, collectorSubId);//00 80 00 01 //саб ид того кто собирает
		writeInt(buffer, resourseId);//3B 95 07 00 //обжект ид растения
		writeInt(buffer, resourseSubId);//00 80 04 00 //саб ид растение
		writeInt(buffer, result);//03 00 00 00
		writeInt(buffer, fatigability);
	}
}
