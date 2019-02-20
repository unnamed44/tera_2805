//new
package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import tera.gameserver.model.equipment.Equipment;
import tera.gameserver.model.equipment.Slot;
import tera.gameserver.model.inventory.Cell;
import tera.gameserver.model.inventory.Inventory;
import tera.gameserver.model.items.BindType;
import tera.gameserver.model.items.CrystalInstance;
import tera.gameserver.model.items.CrystalList;
import tera.gameserver.model.items.ItemInstance;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, описывающий инвентарь игрока.
 *
 * @author Ronn
 */
public class S_Inven extends ServerPacket
{
	private static final ServerPacket instance = new S_Inven();

	public static S_Inven getInstance(Player player) {
		return getInstance(player, false, true);
	}

	public static S_Inven getInstance(Player player, boolean first, boolean show)
	{
		S_Inven packet = (S_Inven) instance.newInstance();

		// получаем промежуточный буффер
		ByteBuffer buffer = packet.getPrepare();

		try
		{
			// получаем инвентарь
			Inventory inventory = player.getInventory();

			// получаем экиперовку
			Equipment equipment = player.getEquipment();

			// если чего-то нет, выходим
			if(inventory == null || equipment == null)
				return packet;

			inventory.lock();
			try
			{
				equipment.lock();
				try
				{
					// определяем кол-во всех ячеяк
					int total = inventory.getEngagedCells() + equipment.getEngagedSlots();
					int equipmentSize = equipment.size();
					int inventorySize = inventory.getMaxCells();

					packet.writeShort(buffer, total);
					packet.writeShort(buffer, 51);
					packet.writeInt(buffer, player.getObjectId());
					packet.writeInt(buffer, player.getSubId());
					packet.writeLong(buffer, inventory.getMoney());

					packet.writeByte(buffer, (show) ? 1 : 0);
					packet.writeByte(buffer, (first) ? 1 : 0);

					packet.writeByte(buffer, 0);
					packet.writeInt(buffer, inventorySize); // equipmentSize
					packet.writeInt(buffer, 0);//itemlevelInventory
					packet.writeInt(buffer, 0);//itemLevel
					packet.writeLong(buffer,0);
					packet.writeInt(buffer, 0);

					Cell[] cells = inventory.getCells();

					Slot[] slots = equipment.getSlots();

					int n = 51;

					for(int i = 0; i < equipmentSize; i++)
					{
						// получаем итем
						ItemInstance item = slots[i].getItem();

						// если его нет, пропускаем
						if(item == null)
							continue;

						packet.writeShort(buffer, n);

						n += 138;

						packet.writeShort(buffer, n);

						packet.writeInt(buffer, 0);
						packet.writeShort(buffer, n - 2);

						packet.writeInt(buffer, item.getItemId());
						packet.writeInt(buffer, item.getObjectId());
						packet.writeInt(buffer, item.getSubId());
						packet.writeInt(buffer, player.getObjectId());
						packet.writeInt(buffer, player.getSubId());
						packet.writeInt(buffer, i + 1);
						packet.writeInt(buffer, 0);
						packet.writeInt(buffer, (int)item.getItemCount());
						packet.writeInt(buffer, item.getEnchantLevel());
						packet.writeInt(buffer,0);
						packet.writeByte(buffer, (item.isBinded()) ? 1 : 0);//soulbind

						// получаем кристалы итема
						CrystalList crystals = item.getCrystals();

						// если кристалов нет
						if(crystals == null || crystals.isEmpty())
						{
							packet.writeInt(buffer, 0); // 1 ячейка итема
							packet.writeInt(buffer, 0); // 2 ячейка итема
							packet.writeInt(buffer, 0); // 3 ячейка итема
							packet.writeInt(buffer, 0); // 4 ячейка итема
						}
						else
						{
							int diff = 4 - crystals.size();

							CrystalInstance[] array = crystals.getArray();

							// вносим вставленные кристалы
							for(int g = 0, length = crystals.size(); g < length; g++)
								packet.writeInt(buffer, array[g].getItemId());

							// если есть пустые слоты
							if(diff > 0)
								// указываем кол-во пустых слотов
								for(int g = 0; g < diff; g++)
									packet.writeInt(buffer, 0);
						}
//5 long 1 int
						packet.writeInt(buffer, 0);//crystal5
						packet.writeInt(buffer, 0);//remodel
						packet.writeInt(buffer, 0);//dye
						packet.writeInt(buffer, 0);//dye sec remaining
						packet.writeLong(buffer, 0);//dye date
						packet.writeLong(buffer, 0);//dye expiry date
						packet.writeByte(buffer, item.getMasterworked());
						packet.writeInt(buffer, item.isEnigma());//enigma
						packet.writeInt(buffer, 0);//masterwork bonus
						packet.writeInt(buffer,item.getItemLevel());
						packet.writeInt(buffer, 0);//times brokered
						packet.writeInt(buffer, 0);//enchant advantage
						packet.writeInt(buffer, 0);//enchant bonus
						packet.writeInt(buffer, 0);//enchant bonus max plus
						if(item.isBinded()) {
							packet.writeInt(buffer, player.getObjectId());
							packet.writeInt(buffer, player.getSubId());
						}
						else
							packet.writeLong(buffer, 0);
						packet.writeShort(buffer,0);
						/*packet.writeLong(buffer, 0);
						packet.writeLong(buffer, 0);
						packet.writeByte(buffer, item.getMasterworked());
						packet.writeInt(buffer, 0);//enigma
						packet.writeInt(buffer, 0);
						packet.writeInt(buffer, item.getItemLevel());
						packet.writeInt(buffer, 0);
						packet.writeInt(buffer, 0);
						packet.writeInt(buffer, 0);
						packet.writeInt(buffer, 0);
						packet.writeInt(buffer, player.getObjectId());
						//packet.writeByte(buffer, 0);

						packet.writeShort(buffer, 0);
						packet.writeShort(buffer, 0);
						packet.writeShort(buffer, 3); // need to have Combat item type added like 3 etc...
						packet.writeInt(buffer, 0);
						packet.writeShort(buffer, 0);
						packet.writeInt(buffer, 0xFFFFFFFF);
						packet.writeInt(buffer, 0);
						packet.writeShort(buffer, 0);*/

					}

					// получаем индекс последняй занятой ячейки
					int last = inventory.getLastIndex();

					// перебираем ячейки инвенторя
					for(int i = 0; i < inventorySize; i++)
					{
						// получаем итем
						ItemInstance item = cells[i].getItem();

						// если его нет, пропускаем
						if(item == null)
							continue;

						packet.writeShort(buffer, n);

						n += 138;

						if(last == i)
							n = 0;

						packet.writeShort(buffer, n);
						packet.writeInt(buffer, 0);
						if(n !=0)
							packet.writeShort(buffer, n - 2);
						else
							packet.writeShort(buffer,0);
						packet.writeInt(buffer, item.getItemId());
						packet.writeInt(buffer, item.getObjectId());
						packet.writeInt(buffer, item.getSubId());
						packet.writeInt(buffer, player.getObjectId());
						packet.writeInt(buffer, player.getSubId());
						packet.writeInt(buffer, i + 40);
						packet.writeInt(buffer, 0);
						packet.writeInt(buffer, (int)item.getItemCount());
						packet.writeInt(buffer, item.getEnchantLevel());
						packet.writeInt(buffer,0);
						packet.writeByte(buffer, (item.isBinded()) ? 1 : 0);//soulbind

						// получаем кристалы итема
						CrystalList crystals = item.getCrystals();

						// если кристалов нет
						if(crystals == null || crystals.isEmpty())
						{
							packet.writeInt(buffer, 0); // 1 ячейка итема
							packet.writeInt(buffer, 0); // 2 ячейка итема
							packet.writeInt(buffer, 0); // 3 ячейка итема
							packet.writeInt(buffer, 0); // 4 ячейка итема
						}
						else
						{
							int diff = 4 - crystals.size();

							CrystalInstance[] array = crystals.getArray();

							// вносим вставленные кристалы
							for(int g = 0, length = crystals.size(); g < length; g++)
								packet.writeInt(buffer, array[g].getItemId());

							// если есть пустые слоты
							if(diff > 0)
								// указываем кол-во пустых слотов
								for(int g = 0; g < diff; g++)
									packet.writeInt(buffer, 0);
						}
//5 long 1 int
						packet.writeInt(buffer, 0);//crystal5
						packet.writeInt(buffer, 0);//remodel
						packet.writeInt(buffer, 0);//dye
						packet.writeInt(buffer, 0);//dye sec remaining
						packet.writeLong(buffer, 0);//dye date
						packet.writeLong(buffer, 0);//dye expiry date
						packet.writeByte(buffer, item.getMasterworked());
						packet.writeInt(buffer, item.isEnigma());//enigma
						packet.writeInt(buffer, 0);//masterwork bonus
						packet.writeInt(buffer,item.getItemLevel());
						packet.writeInt(buffer, 0);//times brokered
						packet.writeInt(buffer, 0);//enchant advantage
						packet.writeInt(buffer, 0);//enchant bonus
						packet.writeInt(buffer, 0);//enchant bonus max plus
						if(item.isBinded()) {
							packet.writeInt(buffer, player.getObjectId());
							packet.writeInt(buffer, player.getSubId());
						}
						else
							packet.writeLong(buffer, 0);
						packet.writeShort(buffer,0);
					}
				}
				finally
				{
					equipment.unlock();
				}
			}
			finally
			{
				inventory.unlock();
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

	public S_Inven()
	{
		this.prepare = ByteBuffer.allocate(1024000).order(ByteOrder.LITTLE_ENDIAN);
	}

	@Override
	public void finalyze()
	{
		prepare.clear();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_INVEN;
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