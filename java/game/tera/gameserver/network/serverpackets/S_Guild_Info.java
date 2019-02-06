package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;

import rlib.util.Strings;
import rlib.util.table.IntKey;
import rlib.util.table.Table;
import tera.gameserver.model.Guild;
import tera.gameserver.model.GuildMember;
import tera.gameserver.model.GuildRank;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет с информацией о клане.
 *
 * @author Ronn
 */
public class S_Guild_Info extends ServerPacket
{
	private static final ServerPacket instance = new S_Guild_Info();

	public static S_Guild_Info getInstance(Player player)
	{
		S_Guild_Info packet = (S_Guild_Info) instance.newInstance();

		// получаем гильдию игрока
		Guild guild = player.getGuild();

		ByteBuffer buffer = packet.getPrepare();

		try
		{
			if(guild == null)
				return packet;

			GuildMember leader = guild.getLeader();

			if(leader == null)
				return packet;

			GuildRank myrank = player.getGuildRank();
			Table<IntKey, GuildRank> ranks = guild.getRanks();
			String advertisment = ""; // обьявление для гильдии

			int guildNameLength = Strings.length(guild.getName());
			int guildTitleLength = Strings.length(guild.getTitle());
			int leaderNameLength = Strings.length(leader.getName());
			int leaderTitleLength = Strings.length(guild.getMessage());
			int advertismentLength = Strings.length(advertisment);
			int myRankLenght = Strings.length(myrank.getName());

			int fb = 107;
			int n = fb + guildNameLength + guildTitleLength + leaderNameLength + leaderTitleLength + myRankLenght + advertismentLength;

			packet.writeShort(buffer, ranks.size());// 03 00
			packet.writeShort(buffer, n);//rank pos
			packet.writeShort(buffer, fb);// guildname
			packet.writeShort(buffer, fb + guildNameLength);//guildTitle
			packet.writeShort(buffer, fb + guildNameLength + guildTitleLength);//GuildMasterName
			packet.writeShort(buffer, fb + guildNameLength + guildTitleLength + leaderNameLength);// MOTD
			packet.writeShort(buffer, fb + guildNameLength + guildTitleLength + leaderNameLength + 2);// MyRank
			packet.writeShort(buffer, fb + guildNameLength + guildTitleLength + leaderNameLength + 2 + myRankLenght);// adv

			packet.writeInt(buffer, guild.getObjectId()); // B2 00 00 00
			packet.writeInt(buffer, 0); // 00 00 00 00
			packet.writeInt(buffer, 0); // 00 00 00 00
			packet.writeInt(buffer, leader.getObjectId()); // 1D 34 00 00 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			packet.writeInt(buffer, (int) (System.currentTimeMillis() / 1000)); // FE C4 9A 4F дата созадния гильдии
			packet.writeInt(buffer, 0); // 00 00 00 00
			packet.writeInt(buffer, guild.getLevel()); // 01 00 00 00

			packet.writeInt(buffer, 0); // 00 00 00 00 Level duration
			packet.writeInt(buffer, guild.getPraiseNumber()); // 05 00 00 00 Praise
			packet.writeInt(buffer, -1);// FF FF FF FF Polyci Points

			packet.writeByte(buffer, 1);// 01
			packet.writeByte(buffer, 0);
			/*
			 * 01 0F ........ÿÿÿÿ.... 0E 00 00 00 00 00 00
			 */

			packet.writeInt(buffer, 0);
			packet.writeInt(buffer, 0);
			packet.writeInt(buffer, 1);
			packet.writeLong(buffer, 341);//?
			packet.writeLong(buffer, 341);//?
			packet.writeByte(buffer, 1);
			packet.writeInt(buffer, 32399);
			packet.writeInt(buffer, 0);
			packet.writeInt(buffer, 0);
			packet.writeInt(buffer, 0);

			packet.writeString(buffer, guild.getName());
			packet.writeString(buffer, guild.getTitle());
			packet.writeString(buffer, leader.getName());
			packet.writeString(buffer, guild.getMessage());
			packet.writeString(buffer, myrank.getName());
			packet.writeString(buffer, advertisment);

			synchronized(guild)
			{
				int k = 0;
				for(Iterator<GuildRank> iterator = ranks.iterator(); iterator.hasNext();)
				{
					GuildRank rank = iterator.next();

					packet.writeShort(buffer, n);// 82 00

					k = Strings.length(rank.getName());

					n += (14 + k);

					if(iterator.hasNext())
						packet.writeShort(buffer, n); // A8 00
					else
						packet.writeShort(buffer, 0);

					packet.writeShort(buffer, n - k);// 90 00

					packet.writeInt(buffer, rank.getIndex());// 01 00 00 00
					packet.writeInt(buffer, rank.getLawId());// 00 00 00 00
					packet.writeString(buffer, rank.getName()); // 00 47 00 75 00 69 00 6C 00 64 00 6D 00 //суда ранг
				}
			}

			return packet;
		}
		finally
		{
			buffer.flip();
		}
	}

	/** подготовленный буффер для отправки данных */
	private ByteBuffer prepare;

	public S_Guild_Info()
	{
		this.prepare = ByteBuffer.allocate(204800).order(ByteOrder.LITTLE_ENDIAN);
	}

	@Override
	public void finalyze()
	{
		prepare.clear();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_GUILD_INFO;
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
	 * @return подготавливаемый буффер.
	 */
	public ByteBuffer getPrepare()
	{
		return prepare;
	}
}
