package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.manager.ObjectEventManager;
import tera.gameserver.model.inventory.Cell;
import tera.gameserver.model.inventory.Inventory;
import tera.gameserver.model.items.ItemInstance;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Inven_Changedslot;

/**
 * Клиентский пакет, указывающий какой итем хотим переместить в инвенторе
 *
 * @author Ronn
 */
public class C_Move_Inven_Pos extends ClientPacket
{
	/** индекс старой ячейки */
	private int oldcell;
	/** индекс новой ячейки */
	private int newcell;

	/** игрок */
	private Player player;

	@Override
	public void finalyze()
	{
		oldcell = 0;
		newcell = 0;
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

		oldcell = readInt() - 40;
		newcell = readInt() - 40;
	}

	@Override
	public void runImpl()
	{
		if(player == null)
			return;

		Inventory inventory = player.getInventory();

		if(inventory == null)
			return;

		// получаем менеджера событий
		ObjectEventManager eventManager = ObjectEventManager.getInstance();

		// получаем менеджера БД
		DataBaseManager dbManager = DataBaseManager.getInstance();

		inventory.lock();
		try
		{
			Cell oldCell = inventory.getCell(oldcell);
			Cell newCell = inventory.getCell(newcell);

			if(oldCell == null || newCell == null)
				return;

			ItemInstance oldItem = newCell.getItem();

			newCell.setItem(oldCell.getItem());
			oldCell.setItem(oldItem);

			dbManager.updateLocationItem(oldCell.getItem());
			dbManager.updateLocationItem(newCell.getItem());
		}
		finally
		{
			inventory.unlock();
		}

		eventManager.notifyInventoryChanged(player);
		player.sendPacket(S_Inven_Changedslot.getInstance(2, oldcell, newcell), true);
	}
}