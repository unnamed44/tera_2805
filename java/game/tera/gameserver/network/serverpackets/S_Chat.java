package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import rlib.util.Strings;
import tera.gameserver.model.SayType;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет для передачи текста в чат.
 *
 * @author Ronn
 */
public class S_Chat extends ServerPacket
{
	private static final ServerPacket instance = new S_Chat();

	public static S_Chat getInstance(String name, String text, SayType type, int objectId, int subId){
		return  getInstance(name, text, type, objectId, subId, false);
	}
	public static S_Chat getInstance(String name, String text, SayType type, int objectId, int subId, boolean isGM)
	{
		S_Chat packet = (S_Chat) instance.newInstance();

		packet.name = name;
		packet.text = text;
		packet.type = type;
		packet.objectId = objectId;
		packet.subId = subId;
		packet.isGmAccount = (isGM) ? 1 : 0;

		return packet;
	}

	/** текст */
	private String text;
	/** имя того, от кого текст */
	private String name;
	/** тип чата */
	private SayType type;

	/** обджект ид персонажа */
	private int objectId;
	/** саб ид персонажа */
	private int subId;

	private int isGmAccount;

	@Override
	public void finalyze()
	{
		text = null;
		name = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CHAT;
	}

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	public void write(ByteBuffer buffer)
	{
		writeOpcode(buffer);
		writeShort(buffer, 22);
		writeShort(buffer, 24 + Strings.length(name));//длинна имени
		writeInt(buffer, type.ordinal());
		writeInt(buffer, objectId);
		writeInt(buffer, subId);
		writeByte(buffer, 0);
		writeByte(buffer, isGmAccount);
		writeString(buffer, name);//имя
		writeByte(buffer, 0);

		if(name == null || name.isEmpty())
			writeShort(buffer, 0x2000);

		writeByte(buffer, 0);
		writeString(buffer, text);//текст
		writeByte(buffer, 0);
	}
}