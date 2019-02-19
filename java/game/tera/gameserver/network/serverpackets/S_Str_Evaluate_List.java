package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет с подтверждением корректности имени.
 *
 * @author Ronn
 */
public class S_Str_Evaluate_List extends ServerPacket
{
	private static final ServerPacket instance = new S_Str_Evaluate_List();

	public static S_Str_Evaluate_List getInstance(String name, int type)
	{
		S_Str_Evaluate_List packet = (S_Str_Evaluate_List) instance.newInstance();

		packet.name = name;
		packet.type = type;

		return packet;
	}

	/** проверяемое имя */
	private String name;

	/** тип сообщения */
	private int type;

	@Override
	public void finalyze()
	{
		name = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_STR_EVALUATE_LIST;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeShort(1);
		writeShort(8);
		writeInt(8);
		writeShort(22);
		writeInt(type);//индекс
		writeInt(0); //18
		writeString(name);//текст
	}
}
