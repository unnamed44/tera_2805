package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

public class S_Unequip_Item extends ServerPacket {
    private static final ServerPacket instance = new S_Unequip_Item();

    public static S_Unequip_Item getInstance(Player player, int itemId)
    {
        S_Unequip_Item packet = (S_Unequip_Item) instance.newInstance();

        packet.player = player;
        packet.itemId = itemId;

        return packet;
    }

    private Player player;
    private int itemId;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_UNEQUIP_ITEM;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(player.getObjectId());
        writeInt(player.getSubId());
        writeInt(itemId);
        writeInt(0);
    }
}
