package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет для удаления объекта.
 * 
 * @author Ronn
 */
public class S_Despawn_Npc extends ServerPacket
{
	/** гибель */
	public static final int DEAD = 5;
	/** исчезает с пылью под ногами */
	public static final int DISAPPEARS_DUST = 4;
	/** просто исчезает */
	public static final int DISAPPEARS = 1;
	
	private static final ServerPacket instance = new S_Despawn_Npc();
	
	public static S_Despawn_Npc getInstance(Character character, int type)
	{
		S_Despawn_Npc packet = (S_Despawn_Npc) instance.newInstance();
		
		packet.type = type;
		packet.objectId = character.getObjectId();
		packet.subId = character.getSubId();
		packet.x = character.getX();
		packet.y = character.getY();
		packet.z = character.getZ();
		
		return packet;
	}
	
	/** тип удаления */
	private int type;
	/** обджект ид */
	private int objectId;
	/** саб ид */
	private int subId;
	
	/** координаты персонажа */
	private float x;
	private float y;
	private float z;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_DESPAWN_NPC;
	}

	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		writeInt(objectId);
		writeInt(subId);
		writeFloat(x);
		writeFloat(y);
		writeFloat(z);
		writeByte(type);
		writeInt(0);
		writeShort(0);
		writeByte(0);
	}
}