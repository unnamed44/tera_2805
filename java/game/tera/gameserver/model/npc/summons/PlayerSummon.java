package tera.gameserver.model.npc.summons;

import tera.gameserver.network.serverpackets.S_Change_Relation;
import tera.gameserver.templates.NpcTemplate;

/**
 * Модель сумона игрока на основе игрокоподобных суммонов.
 *
 * @author Ronn
 */
public class PlayerSummon extends PlayableSummon
{
	public PlayerSummon(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}

	@Override
	public int getNameColor()
	{
		return S_Change_Relation.COLOR_LIGHT_BLUE;
	}
}
