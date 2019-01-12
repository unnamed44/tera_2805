package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, отображающий откат скилов.
 * 
 * @author Ronn
 */
public class S_Start_Cooltime_Skill extends ServerPacket
{
	private static final ServerPacket instance = new S_Start_Cooltime_Skill();
	
	public static S_Start_Cooltime_Skill getInstance(int skillId, int reuseDelay)
	{
		S_Start_Cooltime_Skill packet = (S_Start_Cooltime_Skill) instance.newInstance();
		
		packet.skillId = skillId;
		packet.reuseDelay = reuseDelay;
		
		return packet;
	}
	
	/** ид скила */
	private int skillId;
	/** время отката */
	private int reuseDelay;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_START_COOLTIME_SKILL;
	}

	@Override
	protected final void writeImpl()
	{
        writeOpcode();
        writeInt(skillId);
        writeInt(reuseDelay);
	}
}