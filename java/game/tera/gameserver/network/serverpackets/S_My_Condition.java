package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Обновление стамины у игрока.
 *
 * @author Ronn
 */
public class S_My_Condition extends ServerPacket
{
	private static final ServerPacket instance = new S_My_Condition();

	public static S_My_Condition getInstance(Player player)
	{
		S_My_Condition packet = (S_My_Condition) instance.newInstance();

		packet.currentHeart = player.getStamina();
		packet.maxHeart = player.getMaxStamina();

		return packet;
	}

	/** текущее кол-во стамины */
	private int currentHeart;
	/** максимальное кол-во стамины */
	private int maxHeart;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_MY_CONDITION;
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
		writeInt(buffer, currentHeart);//Текущая стамина 71 00 00 00
		writeInt(buffer, maxHeart);//Размер стамины 78 00 00 00
		writeShort(buffer, 1);//03 00 на сколько повысилась
	}
}

