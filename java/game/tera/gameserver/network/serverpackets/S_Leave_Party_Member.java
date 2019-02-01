package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, с уведомлением о том, что игрок вышел из пати.
 * 
 * @author Ronn
 */
public class S_Leave_Party_Member extends ServerPacket
{
	private static final ServerPacket instance = new S_Leave_Party_Member();
	
	public static S_Leave_Party_Member getInstance()
	{
		return (S_Leave_Party_Member) instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_LEAVE_PARTY_MEMBER;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
	}
}
