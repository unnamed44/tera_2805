package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Inven_Changedslot extends ServerPacket {

    private static final ServerPacket instance = new S_Inven_Changedslot();

    public static S_Inven_Changedslot getInstance(int nbrSlot, int oldCell, int newCell)
    {
        S_Inven_Changedslot packet = (S_Inven_Changedslot) instance.newInstance();

        packet.nbrSlot = nbrSlot;
        packet.oldCell = oldCell;
        packet.newCell = newCell;

        return packet;
    }

    private int nbrSlot;
    private int oldCell;
    private int newCell;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_INVEN_CHANGEDSLOT;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeShort(nbrSlot);
        writeShort(8);
        writeShort(8);

        if(nbrSlot == 1)
            writeShort(0);
        else
            writeShort(17);

        writeByte(0);
        writeInt(oldCell);
        if(nbrSlot == 2) {
            writeShort(17);
            writeShort(0);
            writeByte(0);
            writeInt(newCell);
        }
    }
}
