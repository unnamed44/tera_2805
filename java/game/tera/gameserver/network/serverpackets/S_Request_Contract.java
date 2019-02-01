package tera.gameserver.network.serverpackets;

import rlib.util.Strings;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, описывающий принятие акшена.
 * 
 * @author Ronn
 * @created 07.03.2012
 */
public class S_Request_Contract extends ServerPacket
{
	private static final ServerPacket instance = new S_Request_Contract();

	/**
	 * @param player игрок.
	 * @param enemy опонент.
	 * @param type тип акшена.
	 * @param objectId обджект ид акшена.
	 * @return новый пакет.
	 */
	public static S_Request_Contract newInstance(Player player, Player enemy, int type, int objectId)
	{
		S_Request_Contract packet = (S_Request_Contract) instance.newInstance();
		
		packet.player = player.getName();
		packet.enemy = enemy == null? Strings.EMPTY : enemy.getName();
		packet.actorId = player.getObjectId();
		packet.actorSubId = player.getSubId();
		packet.enemyId = enemy == null? 0 : enemy.getObjectId();
		packet.enemySubId = enemy == null? 0 : enemy.getSubId();
		packet.type = type;
		packet.objectId = objectId;
		
		return packet;
	}
	
	/** игрок */
	private String player;
	/** опонент */
	private String enemy;
	
	/** ид инициатора */
	private int actorId;
	/** саб ид инициатора */
	private int actorSubId;
	/** ид опонента */
	private int enemyId;
	/** саб ид опонента */
	private int enemySubId;
	
	/** тип акшена */
	private int type;
	/** обджект ид акшена */
	private int objectId;
	
	@Override
	public void finalyze()
	{
		player = null;
		enemy = null;
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
		int n = 44;
		writeShort(n);// senderName
		writeShort(n += Strings.length(player));//data
		writeShort(n+2);//target name
		if(this.enemy == null)
			writeShort(0);
		else
			writeShort(Strings.length(enemy) + 2);//data count
		writeInt(actorId);
		writeInt(actorSubId);
		writeInt(enemyId);
		writeInt(enemySubId);
		writeInt(type);
		writeInt(objectId);
		writeInt(0);
		writeInt(0);
		writeString(player);
		writeShort(0);
		writeString(enemy);

		writeShort(0);
		//write data*/
	}
}
