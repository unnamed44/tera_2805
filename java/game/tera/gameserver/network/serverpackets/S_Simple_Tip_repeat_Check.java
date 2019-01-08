package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn Mod Evestu
 */
public class S_Simple_Tip_repeat_Check extends ServerPacket
{
	private static final ServerPacket instance = new S_Simple_Tip_repeat_Check();
	private static int Va;
	
	public static ServerPacket getInstance(int va)
	{
		Va = va;
		return instance.newInstance();
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SIMPLE_TIP_REPEAT_CHECK;
	}

	@Override
	protected void writeImpl()
	{
		
		writeOpcode();
		writeInt(Va);
		writeByte(0);
	}
}