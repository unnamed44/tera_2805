package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import rlib.util.array.Array;
import tera.gameserver.model.npc.Npc;
import tera.gameserver.model.npc.interaction.Link;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;
import tera.gameserver.templates.NpcTemplate;

/**
 * Серверный пакет содержащий информацию об диалогово окне нпс
 *
 * @author Ronn
 */
public class S_Dialog extends ServerPacket
{
	private static final ServerPacket instance = new S_Dialog();

	public static S_Dialog getInstance(Npc npc, Player player, Array<Link> links)
	{
		S_Dialog packet = (S_Dialog) instance.newInstance();

		ByteBuffer buffer = packet.getPrepare();

		try
		{
			// получаем шаблон НПС
			NpcTemplate template = npc == null? null : npc.getTemplate();

			int startLink = 66;
			int startNameLink = 0;

			packet.writeShort(buffer, links.size());//nbr answer
			packet.writeShort(buffer, startLink);
			packet.writeInt(buffer, 0);
			packet.writeInt(buffer, npc == null? 0 : npc.getObjectId()); // Обжэект ид нпц
			packet.writeInt(buffer, npc == null? 0 : npc.getSubId()); // саб ид нпц

			packet.writeInt(buffer, 1);
			packet.writeInt(buffer, npc == null? 0 : template.getIconId());// 66 00 00 00 нпц темплейт ид
			packet.writeInt(buffer, npc == null? 0 : npc.getTemplateType());// 0D 00 класс моба
			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, 2);// 01 00 00 00

			packet.writeInt(buffer, 0x31C9479E); // 0xBCB8A17B
			packet.writeByte(buffer, 1);
			packet.writeInt(buffer, 1);
			packet.writeInt(buffer, 1);
			packet.writeInt(buffer, 0);
			packet.writeInt(buffer, 0);
			packet.writeInt(buffer, -1);
			packet.writeByte(buffer, 0);// 00

			// если ссылки есть
			if(links != null && !links.isEmpty())
			{
				// получаем последнюю
				Link last = links.last();

				// получаем массив всех
				Link[] array = links.array();

				// перебираем ссылки
				for(int i = 0, length = links.size(); i < length; i++)
				{
					// получаем ссылку
					Link link = array[i];

					// запоминаем игроком
					player.addLink(link);

					// хаписываем
					packet.writeShort(buffer, startLink);

					startNameLink = startLink + 14;

					String name = link.getName();

					startLink += (name.length() * 2 + 16);

					if(link == last)
						packet.writeShort(buffer, 0);
					else
						packet.writeShort(buffer, startLink);

					packet.writeShort(buffer, startNameLink); // (первый байт описания данной ссылки в пакете) + 8
					packet.writeInt(buffer, link.getIconId());// номер ссылки
					packet.writeInt(buffer, i + 1); // 19 квест взять, 9 квест след этап или окончание, 26 трейд, 28 магазины,
					packet.writeString(buffer, name);
				}
			}

			return packet;
		}
		finally
		{
			buffer.flip();
		}
	}

	/** промежуточный буффер */
	private ByteBuffer prepare;

	public S_Dialog()
	{
		this.prepare = ByteBuffer.allocate(4096).order(ByteOrder.LITTLE_ENDIAN);
	}

	@Override
	public void finalyze()
	{
		prepare.clear();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_DIALOG;
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

		// получаем промежуточный буффер
		ByteBuffer prepare = getPrepare();

		// переносим данные
		buffer.put(prepare.array(), 0, prepare.limit());
	}

	/**
	 * @return подготовленный буфер.
	 */
	public ByteBuffer getPrepare()
	{
		return prepare;
	}
}