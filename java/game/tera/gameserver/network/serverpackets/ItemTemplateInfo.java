package tera.gameserver.network.serverpackets;

import tera.gameserver.model.items.ItemInstance;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет с общей инфой об итеме.
 *
 * @author Ronn
 */
public class ItemTemplateInfo extends ServerPacket
{
	private static final ServerPacket instance = new ItemTemplateInfo();

	public static ItemTemplateInfo getInstance(ItemInstance itemInstance, int type)
	{
		ItemTemplateInfo packet = (ItemTemplateInfo) instance.newInstance();

		packet.itemInstance = itemInstance;
		packet.type = type;

		return packet;
	}

	/** ид итема */
	private ItemInstance itemInstance;
	private int type;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SHOW_ITEM_TOOLTIP;
	}

	@Override
	protected void writeImpl()
	{
		int n = 487;
		writeOpcode();
		writeShort(0);//count crystals
		writeShort(0);//pos crystals
		writeShort(15);//count passivities
		writeShort(n);//pos passivities
		writeShort(n-4);//crafter name
		writeShort(n-2);//soulbound name
		writeInt(type);//type
		writeInt(itemInstance.getObjectId());
		writeInt(0);
		writeInt(itemInstance.getTemplate().getItemId());//dbid
		writeInt(itemInstance.getObjectId());
		writeInt(0);
		writeLong(itemInstance.getOwnerId());//owner id
		writeInt(3);//slot
		writeInt(0);
		writeInt((int)itemInstance.getItemCount());//amount total
		writeInt(1);//amount
		writeInt(itemInstance.getEnchantLevel());//enchant
		writeInt(0);
		writeByte(0);//soulbound
		writeInt(0);
		writeInt(0);//passivity set
		writeInt(0);//extra passivity sets
		writeByte(0);//compare stats
		writeInt(0);//etching
		writeInt(0);//etching
		writeInt(0);//etching
		writeInt(0);//etching
		writeInt(0);//etching
		writeInt(0);//etching
		writeInt(0);//etching
		writeInt(0);//etching
		writeInt(0);//etching sec remaining

		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeInt(0);
		writeInt(0);
		writeInt(0);
		writeInt(0);
		writeInt(0);
		writeInt(0);
		writeShort(0);
		writeByte(0);
		writeLong(0xFEFEFEFEFEFEFEFEL);
		writeLong(0xFEFEFEFEFEFEFEFEL);
		writeLong(0xFEFEFEFEFEFEFEFEL);
		writeLong(0xFEFEFEFEFEFEFEFEL);
		writeLong(0xFEFEFEFEFEFEFEFEL);
		writeLong(0xFEFEFEFEFEFEFEFEL);
		writeLong(0xFEFEFEFEFEFEFEFEL);
		writeLong(0xFEFEFEFEFEFEFEFEL);
		writeInt(itemInstance.isEnigma());//enigma
		writeByte(itemInstance.getMasterworked());//masterwork
		writeInt(0);
		writeInt(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeInt(0);
		writeInt(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeLong(0);
		writeInt(0);
		writeByte(0);
		writeInt(0);
		writeInt(0);
		writeInt(0);
		writeInt(0);

		writeInt(itemInstance.getTemplate().getItemLevel());//itemLevel
		writeInt(0);
		writeLong(-1);
		writeInt(-1);
		writeInt(-1);//unk27
		writeInt(-1);//setId
		writeInt(-1);//unk28
		writeInt(0);
		writeInt(0);
		writeInt(0);
		writeLong(itemInstance.getTemplate().getSellPrice());//broker price ?
		writeShort(0);//crafterName
		writeShort(0);//soulbound name
		//passivities
		for(int i = 0; i < 15; i++) {
			writeShort(n);//current pos
			if(i < 14)
				writeShort(n+=8);
			else
				writeShort(0);//next pos (+8)
			writeInt(0);//crystal id
		}/*
		writeInt(0x00000000);
		writeInt(0x0121011F);
		writeInt(0x00000013);
		writeInt(0); // E05C9F08
		writeInt(0); // 00000000
		writeInt(id);

		writeInt(0xE05C9F08);// E05C9F08
		writeInt(0); // 00000000

		writeInt(0);// 59AE0B00 Ид сумки
		writeInt(0x00000000);

		writeInt(0); // номер ячейки
		writeInt(0x00000000);
		writeInt(1);
		writeInt(1);
		writeInt(0x00000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeLong(0x0000000000000000);
		writeInt(0x00000000);
		writeInt(3);// вес
		writeInt(0x00000000);
		writeInt(0x00000000);
		writeShort(0x0000);
		writeByte(0x00);*/
	}
}
