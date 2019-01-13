package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Fatigability_Point extends ServerPacket {
    private static final ServerPacket instance = new S_Fatigability_Point();

    public static ServerPacket getInstance(int points)
    {
       S_Fatigability_Point packet = (S_Fatigability_Point) instance.newInstance();
       packet.points = points;

       return packet;
    }

    private int points;
    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_FATIGABILITY_POINT;
    }

    @Override
    protected void writeImpl()
    {
        writeOpcode();
        writeInt(1);
        writeInt(buffer, points);

    }
}
