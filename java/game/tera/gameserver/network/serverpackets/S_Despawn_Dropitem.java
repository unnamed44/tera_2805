package tera.gameserver.network.serverpackets;

import tera.gameserver.model.items.ItemInstance;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет для удаления итемов.
 * 
 * @author Ronn
 */
public class S_Despawn_Dropitem extends ServerPacket
{
	private static final ServerPacket instance = new S_Despawn_Dropitem();
	
	public static S_Despawn_Dropitem getInstance(ItemInstance item)
	{
		S_Despawn_Dropitem packet = (S_Despawn_Dropitem) instance.newInstance();
		
		packet.objectId = item.getObjectId();
		packet.subId = item.getSubId();
		
		return packet;
	}
	
	/** обджект ид */
	private int objectId;
	/** саб ид */
	private int subId;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_DESPAWN_DROPITEM;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeInt(objectId);
		writeInt(subId);
	}
}
