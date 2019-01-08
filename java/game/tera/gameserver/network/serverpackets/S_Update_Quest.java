package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import tera.gameserver.model.quests.QuestState;
import tera.gameserver.network.ServerPacketType;


/**
 * @author Ronn
 */
public class S_Update_Quest extends ServerPacket
{
	private static final ServerPacket instance = new S_Update_Quest();

	public static S_Update_Quest getInstance(QuestState state, int[] counts, boolean complete)
	{
		S_Update_Quest packet = (S_Update_Quest) instance.newInstance();

		packet.buffer = packet.prepare;

		int n = 23;

		packet.writeInt(0);// 00 00 00 00
		packet.writeShort(counts.length);// 02 00
		packet.writeShort(n);// 17 00
		packet.writeInt(state.getQuestId());// 45 05 00 00 '.»?........E...
		packet.writeInt(state.getObjectId());// CF 33 68 01
		packet.writeShort(0);// 00 00
		packet.writeByte(complete? 1 : 0);// 00

		for(int i = 0, length = counts.length; i < length; i++)
		{
			packet.writeShort(n);// 17 00

			if(i == length - 1)
				n = 0;
			else
				n += 8;

			packet.writeShort(n);// 1F 00
			packet.writeInt(counts[i]);// 05 00 00 00
		}

		return packet;
	}

	/** промежуточный буффер */
	private ByteBuffer prepare;

	public S_Update_Quest()
	{
		super();

		this.prepare = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
	}

	@Override
	public void finalyze()
	{
		prepare.clear();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_UPDATE_QUEST;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();

		prepare.flip();

		buffer.put(prepare);
	}
}
