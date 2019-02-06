package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.network.ServerPacketType;


/**
 * Серверный пакет с прогрессом сбора ресурсов.
 * 
 * @author Ronn
 */
public class S_Collection_Progress extends ServerPacket
{
	private static final ServerPacket instance = new S_Collection_Progress();
	
	public static S_Collection_Progress getInstance(int progress)
	{
		S_Collection_Progress packet = (S_Collection_Progress) instance.newInstance();
		
		packet.progress = progress;
		
		return packet;
	}
	
	/** сколько % уже выполнено */
	private int progress;
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_COLLECTION_PROGRESS;
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
		writeInt(buffer, progress);//18 00 00 00 ПРогресс сбора в процентах
		writeLong(buffer,0);
	}
}
