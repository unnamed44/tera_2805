package tera.gameserver.model.worldobject;

import tera.Config;
import tera.gameserver.model.TObject;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Despawn_Bonfire;
import tera.gameserver.network.serverpackets.S_Spawn_Bonfire;

/**
 * Модель объектов  которые находятся в мире со спицефическими функциями.
 * 
 * @author Ronn
 */
public abstract class WorldObject extends TObject
{
	/**
	 * @param objectId уникальный ид объекта.
	 */
	public WorldObject(int objectId)
	{
		super(objectId);
	}

	@Override
	public void addMe(Player player)
	{
		player.sendPacket(S_Spawn_Bonfire.getInstance(this), true);
	}

	@Override
	public int getSubId()
	{
		return Config.SERVER_OBJECT_SUB_ID;
	}

	@Override
	public final boolean isWorldObject()
	{
		return true;
	}

	@Override
	public void removeMe(Player player, int type)
	{
		player.sendPacket(S_Despawn_Bonfire.getInstance(this), true);
	}
}
