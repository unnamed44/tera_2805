package tera.gameserver.network.clientpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Inven_Changedslot;
import tera.gameserver.network.serverpackets.S_User_External_Change;

/**
 * Запрос на одевание предмета.
 *
 * @author Ronn
 */
public class C_Equip_Item extends ClientPacket
{
	/** номер слота */
	private int slot;
	/** ид итема снимаемого */
	private int itemId;

	/** игрок */
	private Player player;

	@Override
	public void finalyze()
	{
		itemId = 0;
		slot = 0;
		player = null;
	}

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	public void readImpl()
	{
		player = owner.getOwner();

		readInt();
		readInt();

		//номер слота
		slot = readInt();

		if(slot < 40 && buffer.remaining() > 7)
		{
			readInt();
			itemId = readInt();
		}
	}

	@Override
	public void runImpl()
	{
		if(player != null){
			player.getAI().startDressItem(slot, itemId);
			player.broadcastPacket(S_User_External_Change.getInstance(player));
		}
	}
}
