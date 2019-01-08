package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, обновляющий инфу об изменении текущего состояния ьп игрока
 * 
 * @author Ronn
 */
public class S_Player_Change_Mp extends ServerPacket
{
	/** просто прибавка */
	public static final int INCREASE = 0;
	/** прибавка с плюсом */
	public static final int INCREASE_PLUS = 1;

	private static final ServerPacket instance = new S_Player_Change_Mp();
	
	public static S_Player_Change_Mp getInstance(Character player, Character attacked, int countChange, int type)
	{
		S_Player_Change_Mp packet = (S_Player_Change_Mp) instance.newInstance();
		
		packet.player = player;
		packet.countChange = countChange;
		packet.type = type;
		packet.attacked = attacked;
		
		return packet;
	}
	
	/** игрок */
	private Character player;
	/** тот кто влиял */
	private Character attacked;

	/** насколько хп изменилось */
	private int countChange;
	/** тип изменения */
	private int type;
	
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.PLAYER_CURRENT_MP;
	}

	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		writeInt(player.getCurrentMp()); // сколько сейчас
		writeInt(player.getMaxMp()); // сколько размер полоски
		writeInt(countChange); // на сколько увеличилось хп
		writeInt(type);// если 01 будет зелёный плюсик
		writeInt(player.getObjectId()); // наш ид
		writeInt(player.getSubId()); // наш сабид

		if(attacked != null)
		{
			writeInt(attacked.getObjectId()); // ИД того кто вас хильнул
			writeInt(attacked.getSubId()); // САБ ИД того кто вас хильнул
		}
		else
		{
			writeInt(0); // ИД того кто вас хильнул
			writeInt(0); // САБ ИД того кто вас хильнул
		}
	}
}