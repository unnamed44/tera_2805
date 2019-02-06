package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.resourse.ResourseType;
import tera.gameserver.network.ServerPacketType;


/**
 * Серверный пакет с увеличением уровня сбора ресурсов.
 * 
 * @author Ronn
 */
public class S_Player_Change_Prof extends ServerPacket
{
	private static final ServerPacket instance = new S_Player_Change_Prof();
	
	public static S_Player_Change_Prof getInstance(ResourseType type, int level)
	{
		S_Player_Change_Prof packet = (S_Player_Change_Prof) instance.newInstance();
		
		packet.level = level;
		packet.type = type;
		
		return packet;
	}
	
	/** тип ресурса */
	private ResourseType type;
	
	/** уровень навыка */
	private int level;
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_PLAYER_CHANGE_PROF;
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
		writeInt(buffer, type.ordinal());
		writeShort(buffer, level); //06 00 кол-во
	}
}
