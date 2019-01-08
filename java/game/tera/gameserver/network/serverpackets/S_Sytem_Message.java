package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.MessageType;
import tera.gameserver.network.ServerPacketType;


/**
 * Пакет системного сообщения.
 *
 * @author Ronn
 */
public class S_Sytem_Message extends ServerPacket
{
	private static final S_Sytem_Message instance = new S_Sytem_Message();

	private static final char split = 0x0B;

	public static S_Sytem_Message getInstance(MessageType type)
	{
		S_Sytem_Message packet = (S_Sytem_Message) instance.newInstance();

		packet.builder = new StringBuilder(type.getName());

		return packet;
	}

	public static S_Sytem_Message getInstance(String message)
	{
		S_Sytem_Message packet = (S_Sytem_Message) instance.newInstance();

		packet.builder = new StringBuilder(message);

		return packet;
	}

	/** подготовленная строка */
	private StringBuilder builder;

	public S_Sytem_Message add(String var, String val)
	{
		builder.append(split);
		builder.append(var);
		builder.append(split);
		builder.append(val);

		return this;
	}

	/**
	 * Добавление атакующего.
	 */
	public S_Sytem_Message addAttacker(String name)
	{
		builder.append(split);
		builder.append("attacker");
		builder.append(split);
		builder.append(name);

		return this;
	}

	/**
	 * Добавить итемы в сообщение.
	 */
	public S_Sytem_Message addItem(int id, int count)
	{
		builder.append(split);
		builder.append("ItemName");
		builder.append(split);
		builder.append("@Item:").append(id);
		builder.append(split);
		builder.append("ItemAmount");
		builder.append(split);
		builder.append(count);

		return this;
	}

	/**
	 * Добавить итемы в сообщение.
	 */
	public S_Sytem_Message addItemName(int id)
	{
		builder.append(split);
		builder.append("ItemName");
		builder.append(split);
		builder.append("@Item:").append(id);

		return this;
	}

	public S_Sytem_Message addLoser(String name)
	{
		builder.append(split);
		builder.append("loser");
		builder.append(split);
		builder.append(name);

		return this;
	}

	/**
	 * Добавить кому сколько денег выдано.
	 */
	public S_Sytem_Message addMoney(String name, int count)
	{
		builder.append(split);
		builder.append("UserName");
		builder.append(split);
		builder.append(name);
		builder.append(split);
		builder.append("Money");
		builder.append(split);
		builder.append(count);

		return this;
	}

	public S_Sytem_Message addOpponent(String name)
	{
		builder.append(split);
		builder.append("Opponent");
		builder.append(split);
		builder.append(name);

		return this;
	}

	/**
	 * Добавить сколько денег потрачено.
	 */
	public S_Sytem_Message addPaidMoney(int count)
	{
		builder.append(split);
		builder.append("amount");
		builder.append(split);
		builder.append(count);

		return this;
	}

	/**
	 * Добавить игрока.
	 */
	public S_Sytem_Message addPlayer(String name)
	{
		builder.append(split);
		builder.append("player");
		builder.append(split);
		builder.append(name);

		return this;
	}

	public S_Sytem_Message addProf(int count)
	{
		builder.append(split);
		builder.append("prof");
		builder.append(split);
		builder.append(count);

		return this;
	}

	public S_Sytem_Message addQuestName(int id)
	{
		builder.append(split);
		builder.append("QuestName");
		builder.append(split);
		builder.append("@quest:");
		builder.append(id).append("001");

		return this;
	}

	public S_Sytem_Message addQuestName(String name)
	{
		builder.append(split);
		builder.append("QuestName");
		builder.append(split);
		builder.append(name);

		return this;
	}

	public S_Sytem_Message addRequestor(String name)
	{
		builder.append(split);
		builder.append("requestor");
		builder.append(split);
		builder.append(name);

		return this;
	}

	public S_Sytem_Message addSkillName(String name)
	{
		builder.append(split);
		builder.append("SkillName");
		builder.append(split);
		builder.append(name);

		return this;
	}

	public S_Sytem_Message addTarget(String name)
	{
		builder.append(split);
		builder.append("target");
		builder.append(split);
		builder.append(name);

		return this;
	}

	public S_Sytem_Message addUserName(String name)
	{
		builder.append(split);
		builder.append("UserName");
		builder.append(split);
		builder.append(name);

		return this;
	}

	public S_Sytem_Message addWinner(String name)
	{
		builder.append(split);
		builder.append("winner");
		builder.append(split);
		builder.append(name);

		return this;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SYSTEM_MESSAGE;
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
		writeShort(buffer, 6);
		writeStringBuilder(buffer, builder);
	}
}
