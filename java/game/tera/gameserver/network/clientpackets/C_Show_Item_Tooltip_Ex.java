package tera.gameserver.network.clientpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.ItemTemplateInfo;
import tera.gameserver.network.serverpackets.S_Inven;
import tera.gameserver.tables.ItemTable;
import tera.gameserver.templates.ItemTemplate;

/**
 * Клиентский пакет, запрасывающий пакет инвенторя.
 *
 * @author Ronns
 */
public class C_Show_Item_Tooltip_Ex extends ClientPacket
{
    /** игрок */
    private Player player;
    private int itemId;

    @Override
    public void finalyze()
    {
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
        readShort();
        readInt();
        itemId = readInt();
    }

    @Override
    public void runImpl()
    {
        if(player == null)
            return;

        // получаем таблицу итемов
        ItemTable itemTable = ItemTable.getInstance();

        ItemTemplate template = itemTable.getItem(itemId);

        if(template == null)
            return;

        //player.sendMessage("itemId = " + itemId + ", itemLevel " + template.getItemLevel());

        player.sendPacket(ItemTemplateInfo.getInstance(template.getItemId()), true);
    }
}