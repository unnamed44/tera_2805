package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

import java.nio.ByteBuffer;

public class S_Trade_Broker_Highest_Item_Level extends ServerPacket {
    private static final ServerPacket instance = new S_Trade_Broker_Highest_Item_Level();

    private int ilvl;

    public static S_Trade_Broker_Highest_Item_Level getInstance(int ilvl){
        S_Trade_Broker_Highest_Item_Level packet = (S_Trade_Broker_Highest_Item_Level) instance.newInstance();

        packet.ilvl = ilvl;

        return packet;
    }

    @Override
    public boolean isSynchronized()
    {
        return false;
    }

    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_TRADE_BROKER_HIGHEST_ITEM_LEVEL;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer){
        writeOpcode(buffer);
        writeInt(buffer, ilvl);
    }
}
