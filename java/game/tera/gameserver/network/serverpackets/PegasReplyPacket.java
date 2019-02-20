package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет для открытия карты, в которой нужно выбирать пункт назначения для полета
 *
 * @author Ronn
 * @created 25.02.2012
 */
public class PegasReplyPacket extends ServerPacket
{
	private static final ServerPacket instance = new PegasReplyPacket();
	
	public static PegasReplyPacket getInstance(Player player)
	{
		PegasReplyPacket packet = (PegasReplyPacket) instance.newInstance();
		
		packet.player = player;
		
		return packet;
	}
	
	/** игрок */
	private Player player;

	@Override
	public void finalyze()
	{
		player = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_REQUEST_CONTRACT;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeShort(44);
		writeShort(58);
		writeInt(60);
		writeInt(player.getObjectId());
		writeInt(player.getSubId());
		writeLong(0);//00 00 00 00  00 00 00 00
		writeInt(15);
		writeInt(0x989b0300);
		writeLong(0);//00 00 00 00  00 00 00 00
		writeS(player.getName());
		writeShort(0);
		
	}
}
