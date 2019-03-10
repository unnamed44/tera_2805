package tera.gameserver.network.serverpackets;

import tera.gameserver.model.quests.QuestState;
import tera.gameserver.network.ServerPacketType;

/**
 * Пакет выполнения квеста.
 *
 * @author Ronn
 */
public class S_Delete_Quest extends ServerPacket
{
	private static final ServerPacket instance = new S_Delete_Quest();

	public static S_Delete_Quest getInstance(QuestState quest, boolean canceled)
	{
		S_Delete_Quest packet = (S_Delete_Quest) instance.newInstance();

		packet.questId = quest.getQuestId();
		packet.objectId = quest.getObjectId();
		packet.canceled = canceled? 1 : 0;

		return packet;
	}

	/** ид квеста */
	private int questId;
	/** ид состояния квеста */
	private int objectId;
	/** отменен ли квест */
	private int canceled;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_DELETE_QUEST;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();

		writeInt(questId);//16 05 00 00
		writeInt(objectId);//FE DF 89 00
		writeShort(canceled);//00 00  0 - выполнен, 1 - отменен
		writeFloat(1);
		writeFloat(1);
		writeInt(-1);
		writeByte(0);
	}
}
