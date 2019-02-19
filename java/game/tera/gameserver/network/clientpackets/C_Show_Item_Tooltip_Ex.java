package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.model.items.ItemInstance;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.ItemTemplateInfo;
import tera.gameserver.network.serverpackets.S_Inven;
import tera.gameserver.tables.ItemTable;
import tera.gameserver.templates.ItemTemplate;

import javax.xml.crypto.Data;

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
    private int type;
    private int serverId;
    private int playerId;
    private String ownerName;

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
        type = readInt();
        itemId = readInt();
        readInt();
        readInt();
        readInt();
        readInt();
        serverId = readInt();
        playerId = readInt();
        ownerName= readString();
    }

    @Override
    public void runImpl()
    {
        if(player == null)
            return;

        ItemInstance itemInstance = DataBaseManager.getInstance().getItem(itemId);
        // получаем таблицу итемов

        if(itemInstance == null)
            return;

        //player.sendMessage("itemId = " + itemId + ", itemLevel " + template.getItemLevel());

        player.sendPacket(ItemTemplateInfo.getInstance(itemInstance, type), true);
    }
}