package tera.gameserver.network.serverpackets;

import rlib.util.Strings;
import tera.gameserver.manager.AllianceManager;
import tera.gameserver.model.Alliance;
import tera.gameserver.network.ServerPacketType;

public class S_Union_My_Union extends ServerPacket {
    private static final ServerPacket instance = new S_Union_My_Union();

    public static S_Union_My_Union getInstance(int allianceId)
    {
        S_Union_My_Union packet = (S_Union_My_Union) instance.newInstance();

        packet.allianceId = allianceId;
        packet.alliance = AllianceManager.getInstance().getAlliance(allianceId);

        return packet;
    }

    /** кол-во ожидания секунд */
    private int allianceId;
    private Alliance alliance;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_UNION_MY_UNION;
    }

    @Override
    protected final void writeImpl()
    {
        int[] skills = {63000100, 63000200, 63000300};
        int n = 40;
        int offset = n + 2 + Strings.length(alliance.getLeaderName()) + Strings.length(alliance.getLeaderGuildName()) + Strings.length(alliance.getMessage());
        writeOpcode();
        writeShort(0);//count list
        writeShort(0);//offset list
        writeShort(3);//count ?
        writeShort(offset);//offset (need to write actual alliance list before)
        writeShort(n);//offset exarch name
        writeShort(n += Strings.length(alliance.getLeaderName()));//offset exarch guild name
        writeShort(n += Strings.length(alliance.getLeaderGuildName()));//guild logo ?
        writeShort(n += 2);//message
        n += Strings.length(alliance.getMessage());
        writeInt(alliance.getAllianceId());
        writeInt(alliance.getTaxRate());
        writeInt(4);
        writeInt(0);
        writeInt(1);
        writeString(alliance.getLeaderName());
        writeString(alliance.getLeaderGuildName());
        writeShort(0);
        writeString(alliance.getMessage());

        //unk
        for(int i = 0; i < 3; i++){
            writeShort(n);
            if(i == 2)
                writeShort(0);
            else
                writeShort(n += 20);

            writeLong(skills[i]);
            writeInt(-1);
            writeInt(-1);
        }
        //list of current users classed with guild & position
        /*int n = 48;
        writeOpcode();
        writeInt(0);
        writeShort(3);//count
        writeShort(n);
        writeShort(40);
        writeShort(42);
        writeShort(44);
        writeShort(46);
        writeInt(3);
        writeLong(0);
        writeInt(0);
        writeInt(2);
        writeLong(0);

        for(int i = 0; i < 3; i++){
            writeShort(n);
            if(i == 2)
                writeShort(0);
            else
                writeShort(n += 20);

            writeLong(63000100);
            writeInt(-1);
            writeInt(-1);
        }*/
    }
}
