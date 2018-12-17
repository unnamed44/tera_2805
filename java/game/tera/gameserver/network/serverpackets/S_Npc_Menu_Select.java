package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Npc_Menu_Select extends ServerPacket
{
	/** успешное начало диалога */
	public static final byte SUCCEESS = 1;
	/** не удачное начало диалоги */
	public static final byte NOT_SUCCESS = 0;
	
	private static final ServerPacket instance = new S_Npc_Menu_Select();
	
	public static S_Npc_Menu_Select getInstance(int result)
	{
		S_Npc_Menu_Select packet = (S_Npc_Menu_Select) instance.newInstance();
		
		packet.result = result;
		
		return packet;
	}
	
	/** разрешен ли диалог */
	private int result;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_NPC_MENU_SELECT;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeByte(result);
	}
}