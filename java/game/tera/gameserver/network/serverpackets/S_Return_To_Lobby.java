package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

/**
 * Окно выхода на выбор перса.
 * 
 * @author Ronn
 */
public class S_Return_To_Lobby extends ServerPacket
{
	private static final ServerPacket instance = new S_Return_To_Lobby();
	
	public static S_Return_To_Lobby getInstance(int seconds)
	{
		S_Return_To_Lobby packet = (S_Return_To_Lobby) instance.newInstance();
		
		packet.seconds = seconds;
		
		return packet;
	}

	/** кол-во ожидания секунд */
	private int seconds;
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_RETURN_TO_LOBBY;
	}

	@Override
	protected final void writeImpl()
	{
		switch(seconds)
		{
			case 1:
			{
				writeOpcode();
				writeInt(10); // 10 seconds before logout
			}
			default :
			{
				writeOpcode();
			}
		}
	}
}
