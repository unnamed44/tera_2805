package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.npc.Npc;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;
import tera.gameserver.templates.NpcTemplate;

/**
 * Серверный пакет с информацией об НПС.
 *
 * @author Ronn
 */
public class S_Spawn_Npc extends ServerPacket
{
	private static final ServerPacket instance = new S_Spawn_Npc();

	public static S_Spawn_Npc getInstance(Npc npc, Player player)
	{
		S_Spawn_Npc packet = (S_Spawn_Npc) instance.newInstance();

		packet.objectId = npc.getObjectId();
		packet.subId = npc.getSubId();

		packet.x = npc.getX();
		packet.y = npc.getY();
		packet.z = npc.getZ();

		// получаеим темплейт НПС
		NpcTemplate template = npc.getTemplate();

		packet.heading = npc.getHeading();
		packet.npcId = template.getIconId();
		packet.npcType = npc.getTemplateType();
		packet.spawned = npc.isSpawned()? 1 : 0;
		packet.isFriend = npc.isFriend(player)? 1 : 0;

		return packet;
	}

	/** обджект ид нпс */
	private int objectId;
	/** саб ид нпс */
	private int subId;

	/** координаты нпс */
	private float x;
	private float y;
	private float z;

	/** разворот нпс */
	private int heading;
	/** ид нпс */
	private int npcId;
	/** тип нпс */
	private int npcType;
	/** дружественный ли */
	private int isFriend;
	/** отспавнен ли уже нпс */
	private int spawned;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SPAWN_NPC;
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
		writeInt(buffer, 0);//00 00 00 00 8
		writeShort(buffer, 114);// 6D 00 10
		writeInt(buffer, objectId);// A3 D4 0C 00 обжект ид 14
		writeInt(buffer, subId);// 00 80 0B 00 саб ид 18
		writeLong(buffer, 0);//target
		writeFloat(buffer, x);// FC 90 A6 47 x 30
		writeFloat(buffer, y);// 1F 16 A5 C7 y 34
		writeFloat(buffer, z);// 00 00 94 C5 z 38
		writeShort(buffer, heading);// 10 17 heading 40
		writeInt(buffer, 12);//relation
		writeInt(buffer, npcId);// templateId
		writeShort(buffer, npcType);//Hunting zone id
		writeShort(buffer, 60);// walk speed
		writeShort(buffer, 110);// run speed
		writeShort(buffer, 0);//status
		writeShort(buffer, 0);//mode
		writeShort(buffer, 5);//hp level
		writeByte(buffer, 1);//visible
		writeByte(buffer, isFriend);//is villager
		writeInt(buffer, spawned);
		writeLong(buffer, 0);
		writeInt(buffer, 0);
		writeInt(buffer, 0);
		writeByte(buffer, 0);//aggressive
		writeLong(buffer,0);
		writeLong(buffer,0);
		writeLong(buffer,0);
		writeInt(buffer,0);
		writeByte(buffer,0);
		writeString(buffer, "");//npcname

	}
}
