package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.network.ServerPacketType;

/**
 * Пакет системного сообщения.
 *
 * @author Ronn
 */
public class S_Sytem_Message_Loot_Item extends ServerPacket
{
	private static final S_Sytem_Message_Loot_Item instance = new S_Sytem_Message_Loot_Item();

	private static final char split = 0x0B;

	public static S_Sytem_Message_Loot_Item getInstance(String name, int itemId, int itemCount)
	{
		S_Sytem_Message_Loot_Item packet = (S_Sytem_Message_Loot_Item) instance.newInstance();

		StringBuilder builder = new StringBuilder();

		builder.append("@379");
		builder.append(split);
		builder.append("UserName");
		builder.append(split);
		builder.append(name);
		builder.append(split);
		builder.append("ItemAmount");
		builder.append(split);
		builder.append(itemCount);
		builder.append(split);
		builder.append("ItemName");
		builder.append(split);
		builder.append("@item:").append(itemId);

		packet.itemId = itemId;
		packet.builder = builder;

		return packet;
	}

	/** подготовленная строка */
	private StringBuilder builder;

	/** ид итема */
	private int itemId;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SYSTEM_MESSAGE_LOOT_ITEM;
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
		writeShort(buffer, 32);
		writeInt(buffer, itemId);//ид итема
		writeInt(buffer, 0);
		writeLong(buffer, 1);
		writeLong(buffer, 0);
		writeShort(buffer, 0);
		writeString(buffer, builder.toString());
	}
}
