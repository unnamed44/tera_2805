package tera.gameserver.network.serverpackets;

import tera.gameserver.model.equipment.Equipment;
import tera.gameserver.model.equipment.SlotType;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

public class S_User_External_Change extends ServerPacket {
    private static final ServerPacket instance = new S_User_External_Change();

    public static S_User_External_Change getInstance(Player player)
    {
        S_User_External_Change packet = (S_User_External_Change) instance.newInstance();

        packet.player = player;

        return packet;
    }

    /** кол-во ожидания секунд */
    private Player player;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_USER_EXTERNAL_CHANGE;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeInt(player.getObjectId());
        writeInt(player.getSubId());
        Equipment equipment = player.getEquipment();

        writeInt(equipment.getItemId(SlotType.SLOT_WEAPON));
        writeInt(equipment.getItemId(SlotType.SLOT_ARMOR));
        writeInt(equipment.getItemId(SlotType.SLOT_GLOVES));
        writeInt(equipment.getItemId(SlotType.SLOT_BOOTS));
        writeInt(equipment.getItemId(SlotType.SLOT_SHIRT));
        writeInt(equipment.getItemId(SlotType.SLOT_HAT));
        writeInt(equipment.getItemId(SlotType.SLOT_MASK));
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(equipment.getItem(SlotType.SLOT_WEAPON).getEnchantLevel());
        writeInt(equipment.getItemId(SlotType.SLOT_COSTUME_HEAD));
        writeInt(equipment.getItemId(SlotType.SLOT_COSTUME_FACE));
        writeInt(equipment.getItemId(SlotType.SLOT_COSTUME_BACK));
        writeInt(equipment.getItemId(SlotType.SLOT_COSTUME_WEAPON));
        writeInt(equipment.getItemId(SlotType.SLOT_COSTUME_BODY));
        writeInt(0);
        writeByte(1);
    }
}
