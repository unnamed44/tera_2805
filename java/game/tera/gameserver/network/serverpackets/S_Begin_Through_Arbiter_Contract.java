package tera.gameserver.network.serverpackets;

import rlib.util.Strings;
import tera.gameserver.network.ServerPacketType;

/**
 * Приглашение в акшен игроку
 *
 * @author Ronn
 * @created 26.04.2012
 */
public class S_Begin_Through_Arbiter_Contract extends ServerPacket
{
	private static final ServerPacket instance = new S_Begin_Through_Arbiter_Contract();
	
	/**
	 * @param actorName имя инициатора.
	 * @param enemyName имя опонента.
	 * @param id ид акшена.
	 * @param objectId уникальный ид акшена.
	 * @return новый пакет.
	 */
	public static S_Begin_Through_Arbiter_Contract getInstance(String actorName, String enemyName, int id, int objectId)
	{
		S_Begin_Through_Arbiter_Contract packet = (S_Begin_Through_Arbiter_Contract) instance.newInstance();
		
		packet.actorName = actorName;
		packet.enemyName = enemyName;
		packet.id = id;
		packet.objectId = objectId;
		
		return packet;
	}
	
	/** имя инициатора */
	private String actorName;
	/** имя опонента */
	private String enemyName;
	
	/** ид акшена */
	private int id;
	/** уникальный ид акшена */
	private int objectId;
	
	@Override
	public void finalyze()
	{
		actorName = null;
		enemyName = null;
	}
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_BEGIN_THROUGH_ARBITER_CONTRACT;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeShort(22);//senderName
		writeShort(22 + Strings.length(actorName));//targetName
		writeShort(Strings.length(actorName));  //длинна первого ника +2
		writeInt(id);
		writeInt(objectId);
		writeInt(13583);
		writeString(actorName);
		writeString(enemyName);
		writeShort(0);
	}
}
