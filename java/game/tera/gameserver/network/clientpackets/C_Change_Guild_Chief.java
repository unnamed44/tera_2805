package tera.gameserver.network.clientpackets;

import tera.gameserver.model.Guild;
import tera.gameserver.model.GuildRank;
import tera.gameserver.model.playable.Player;

/**
 * Передача мастера гильдии.
 *
 * @author Ronn
 * @created 26.04.2012
 */
public class C_Change_Guild_Chief extends ClientPacket
{
	/** имя передоваемого */
	private String name;
	/** мастер гильдии */
	private Player player;

	@Override
	public void finalyze()
	{
		player = null;
		name = null;
	}

	@Override
	protected void readImpl()
	{
		player = owner.getOwner();

		readShort();

		name = readString();
	}

	@Override
	protected void runImpl()
	{
		Guild guild = player.getGuild();

		if(guild == null)
			return;

		GuildRank rank = player.getGuildRank();

		if(rank == null || !rank.isGuildMaster())
			return;

		guild.makeGuildMaster(player, name);
	}
}
