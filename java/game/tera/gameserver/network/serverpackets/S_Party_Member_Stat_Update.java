package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.config.MissingConfig;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Пакет с информацией о члене группы.
 *
 * @author Ronn
 * @created 26.04.2012
 */
public class S_Party_Member_Stat_Update extends ServerPacket
{
	private static final ServerPacket instance = new S_Party_Member_Stat_Update();

	public static S_Party_Member_Stat_Update getInstance(Player member)
	{
		member.stopBattleStance();
		S_Party_Member_Stat_Update packet = (S_Party_Member_Stat_Update) instance.newInstance();

		packet.objectId = member.getObjectId();
		packet.currentHp = member.getCurrentHp();
		packet.currentMp = member.getCurrentMp();
		packet.maxHp = member.getMaxHp();
		packet.maxMp = member.getMaxMp();
		packet.level = member.getLevel();
		packet.stamina = member.getStamina();
		packet.dead = member.isDead() ? 0 : 1;
		packet.inBattle = member.isBattleStanced() ? 0 : 1;
		packet.vitality = packet.stamina/30;

		return packet;
	}

	/** уникальный ид игрока */
	private int objectId;
	/** текущее состояние хп */
	private int currentHp;
	/** текущее состояние мп */
	private int currentMp;
	/** максимальное кол-во хп */
	private int maxHp;
	/** максимальное кол-во мп */
	private int maxMp;
	/** текущий уровень */
	private int level;
	/** стамина */
	private int stamina;
	/** мертв ли */
	private int dead;

	private int inBattle;

	private int vitality;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_PARTY_MEMBER_STAT_UPDATE;
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
		writeInt(buffer, MissingConfig.SERVER_ID); // SERVER ID
		writeInt(buffer, objectId);//DE 2C 0B 00 //айди используемого в пати
		writeInt(buffer, currentHp);//2D 08 00 00 //хп сколько было
		writeInt(buffer, currentMp);//3C 05 00 00 /мп сколько было
		writeInt(buffer, maxHp);//2D 08 00 00 //хп сколько всего
		writeInt(buffer, maxMp);//3C 05 00 00 /мп сколько всего
		writeShort(buffer, level);//02 00 //уровень
		writeShort(buffer, inBattle);//02 00 //уровень
		writeShort(buffer, vitality);//04 00 лвл стамины
		writeByte(buffer, dead);//01
		writeInt(buffer, stamina);//78 00 00 00
		writeInt(buffer, 0);//current RE
		writeInt(buffer, 0);//Max RE
		writeInt(buffer, 0);
	}
}
