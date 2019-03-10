package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import tera.gameserver.model.playable.Player;
import tera.gameserver.model.quests.QuestState;
import tera.gameserver.network.ServerPacketType;


/**
 * @author Ronn
 */
public class S_Quest_Info extends ServerPacket
{
	private static final ServerPacket instance = new S_Quest_Info();

	public static S_Quest_Info getInstance(QuestState quest, int npcType, int npcId, float x, float y, float z)
	{
		S_Quest_Info packet = (S_Quest_Info) instance.newInstance();

		packet.buffer = packet.prepare;

		Player player = quest.getPlayer();

		packet.writeShort(1);//01 00
		packet.writeShort(14);//0E 00
		packet.writeShort(12);//0C 00 00 00
		packet.writeByte(0);//00 00
		packet.writeShort(1);//00 00
		packet.writeByte(0);//00 00
		packet.writeInt(14);//0E 00 00 00
		packet.writeShort(1);//01 00
		packet.writeShort(64);//39 00
		packet.writeShort(1);//01 00
		packet.writeShort(97);//55 00
		packet.writeInt(quest.getQuestId());//29 05 00 00 //NPC ID
		packet.writeInt(quest.getObjectId());//C4 E0 89 00 //Обжект ид
		packet.writeInt(quest.getState());//01 00 00 00 //номер квеста уданного нпц
		packet.writeInt(1);//01 00 00 00 00 00 00 00 // если 0, то квест красным висит, 1 обычный показ
		packet.writeInt(0);
		packet.writeInt(quest.getPanelStateId());//00 00 00 00 quest.getPanelStateId()
		packet.writeInt(0x20010100);//00 01 01 00
		packet.writeInt(0);
		packet.writeInt(0);
		packet.writeInt(0);
		packet.writeShort(0);//00 00
		packet.writeByte(0);//00
		packet.writeInt(64);//39 00 00 00 //след сектор
		packet.writeFloat(x);//0B D1 A9 47 //видимо где след этап квеста X
		packet.writeFloat(y);//1F 3D A6 C7 //видимо где след этап квеста Y
		packet.writeFloat(z);//88 11 90 C5 //видимо где след этап квеста Z
		packet.writeInt(npcType);//D5 00 00 00 //213
		packet.writeInt(npcId);//EB 03 00 00 //1003 видимо ид след НПЦ
		packet.writeInt(player != null? player.getZoneId() : 13);//0D 00 00 00 //13
		packet.writeLong(97);//55 00 00 00 00 00 00 00  //85

		return packet;
	}

	/** промежуточный буффер */
	private ByteBuffer prepare;

	public S_Quest_Info()
	{
		super();

		this.prepare = ByteBuffer.allocate(4096).order(ByteOrder.LITTLE_ENDIAN);
	}

	@Override
	public void finalyze()
	{
		prepare.clear();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_QUEST_INFO;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();

		prepare.flip();

		buffer.put(prepare);
	}
}
