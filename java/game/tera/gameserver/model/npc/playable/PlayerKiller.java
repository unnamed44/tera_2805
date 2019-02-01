package tera.gameserver.model.npc.playable;

import tera.gameserver.model.Character;
import tera.gameserver.network.serverpackets.S_Change_Relation;
import tera.gameserver.templates.NpcTemplate;

/**
 * Модель агриссивного НПС.
 *
 * @author Ronn
 *
 */
public class PlayerKiller extends PlayableNpc
{
	public PlayerKiller(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public boolean checkTarget(Character target)
	{
		return target.isPlayer() || target.isSummon();
	}

	@Override
	public int getNameColor()
	{
		return S_Change_Relation.COLOR_RED_PVP;
	}
}
