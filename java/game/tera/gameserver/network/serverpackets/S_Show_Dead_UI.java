package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, запускающий окно смерти.
 *
 * @author Ronn
 */
public class S_Show_Dead_UI extends ServerConstPacket
{
	private static final S_Show_Dead_UI instance = new S_Show_Dead_UI();
	private int zone;

	public static S_Show_Dead_UI getInstance(int zone)
	{
		S_Show_Dead_UI packet = (S_Show_Dead_UI) instance.newInstance();
		packet.zone = zone;
		return packet;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SHOW_DEAD_UI;
	}

	@Override
	protected void writeImpl(ByteBuffer buffer)
	{
		writeOpcode(buffer);
		writeInt(buffer, 30);
		writeInt(buffer,78001);//zone
		writeInt(buffer, 0);
        writeShort(buffer, 0);
        writeByte(buffer, 0);
	}
}