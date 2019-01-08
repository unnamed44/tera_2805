package tera.gameserver.network.serverpackets;

import tera.gameserver.model.npc.Npc;
import tera.gameserver.model.quests.NpcIconType;
import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn
 */
public class S_Quest_Villager_Info extends ServerPacket
{
	private static final ServerPacket instance = new S_Quest_Villager_Info();
	
	public static S_Quest_Villager_Info getInstance(Npc npc, NpcIconType type)
	{
		S_Quest_Villager_Info packet = (S_Quest_Villager_Info) instance.newInstance();
		
		packet.id = npc.getObjectId();
		packet.subId = npc.getSubId();
		packet.type = type;
		
		return packet;
	}
	
	/** тип подсветки */
	private NpcIconType type;
	
	/** ид нпс */
	private int id;
	/** саб ид нпс */
	private int subId;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_QUEST_VILLAGER_INFO;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeInt(id);//40 A9 00 00 
		writeInt(subId);//00 80 0B 00 
		writeInt(type.ordinal());//02 00 00 00 
		writeByte(1);//01 
	}
}
