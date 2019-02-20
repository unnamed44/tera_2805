package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, указывающий на кого смотреть нпс.
 * 
 * @author Ronn
 */
public class S_Npc_Status extends ServerPacket
{
	private static final ServerPacket instance = new S_Npc_Status();
	
	public static S_Npc_Status getInstance(Character character, int targetId, int targetSubId)
	{
		S_Npc_Status packet = (S_Npc_Status) instance.newInstance();
		
		packet.character = character;
		packet.targetId = targetId;
		packet.targetSubId = targetSubId;
		
		return packet;
	}
	
	/** нпс, который сомтрит */
	private Character character;
	
	/** персонаж, на которого он смотрит */
	private int targetId;
	private int targetSubId;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_NPC_STATUS;
	}

	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		writeInt(character.getObjectId());
		writeInt(character.getSubId());
		writeByte(0);//enraged
		writeInt(5);
		writeInt(targetId);
		writeInt(targetSubId);
		writeInt(targetId == 0 ? 0 : 1);
	}
}