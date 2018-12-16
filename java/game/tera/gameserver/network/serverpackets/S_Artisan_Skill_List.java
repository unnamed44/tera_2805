package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.network.ServerPacketType;

public class S_Artisan_Skill_List extends ServerConstPacket
{
	private static final S_Artisan_Skill_List instance = new S_Artisan_Skill_List();

	public static S_Artisan_Skill_List getInstance()
	{
		return instance;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_ARTISAN_SKILL_LIST;
	}

	@Override
	protected void writeImpl(ByteBuffer buffer)
	{
		writeOpcode(buffer);

		writeShort(buffer, 5);

		writeShort(buffer, 25);
		writeInt(buffer, 0);
		writeInt(buffer, 0);
		writeInt(buffer, 0x48000000);
		writeInt(buffer, 0x00000E44);
		writeByte(buffer, 0);

		writeShort(buffer, 25);
		writeShort(buffer, 57);
		writeInt(buffer, 6);
		writeInt(buffer, 6);
		writeInt(buffer, 0x3F800000);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 1);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 0);

		writeShort(buffer, 57);
		writeShort(buffer, 89);
		writeInt(buffer, 7);
		writeInt(buffer, 7);
		writeInt(buffer, 0x3F800000);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 1);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 0);

		writeShort(buffer, 89);
		writeShort(buffer, 121);
		writeInt(buffer, 21);
		writeInt(buffer, 21);
		writeInt(buffer, 0x3F800000);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 1);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 0);

		writeShort(buffer, 121);
		writeShort(buffer, 153);
		writeInt(buffer, 22);
		writeInt(buffer, 22);
		writeInt(buffer, 0x3F800000);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 1);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 0);

		writeShort(buffer, 153);
		writeShort(buffer, 0);
		writeInt(buffer, 23);
		writeInt(buffer, 23);
		writeInt(buffer, 0x3F800000);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 1);
		writeInt(buffer, 0xFFFFFFFF);
		writeInt(buffer, 0);


	}
}