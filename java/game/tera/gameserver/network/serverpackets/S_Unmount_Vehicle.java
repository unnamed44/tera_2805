package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Пакет слезания с маунта.
 * 
 * @author Ronn
 */
public class S_Unmount_Vehicle extends ServerPacket
{
	private static final ServerPacket instance = new S_Unmount_Vehicle();
	
	public static final S_Unmount_Vehicle getInstance(Player player, int skillId)
	{
		S_Unmount_Vehicle packet = (S_Unmount_Vehicle) instance.newInstance();
		
		packet.objectId = player.getObjectId();
		packet.subId = player.getSubId();
		packet.skillId = skillId;
		
		return packet;
	}
	
	/** уникальный ид игрока */
	private int objectId;
	/** саб ид игрока */
	private int subId;
	/** ид скила, которым он сел */
	private int skillId;
    
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_UNMOUNT_VEHICLE;
	}

	@Override
	protected final void writeImpl()
	{
        writeOpcode();
        writeInt(objectId);//91 0B 00 10 обжект ид наш
        writeInt(subId);//00 80 00 13 саб ид наш
        writeInt(skillId);//07 B2 01 00 ид скила
	}
}