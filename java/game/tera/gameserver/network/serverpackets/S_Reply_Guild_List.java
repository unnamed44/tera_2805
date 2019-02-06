package tera.gameserver.network.serverpackets;

import rlib.util.Strings;
import rlib.util.array.Array;
import tera.gameserver.manager.GuildManager;
import tera.gameserver.model.Guild;
import tera.gameserver.network.ServerPacketType;

public class S_Reply_Guild_List extends ServerPacket {
    private static final ServerPacket instance = new S_Reply_Guild_List();

    private Array<Guild> guilds;
    private int page;
    private int totalPage;
    private int nbrGuilds;

    public static S_Reply_Guild_List getInstance(int page)
    {
        S_Reply_Guild_List packet =   (S_Reply_Guild_List) instance.newInstance();
        packet.guilds = GuildManager.getInstance().getGuilds();
        packet.page = page - 5;

        packet.nbrGuilds = packet.guilds.size() > 10 ? 10 : packet.guilds.size();
        packet.totalPage = packet.guilds.size() > 10 ? 10 : packet.guilds.size()/10;
        return packet;
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_REPLY_GUILD_LIST;
    }

    @Override
    protected final void writeImpl() {
        int n = 24;
        writeOpcode();
        writeShort(nbrGuilds);
        writeShort(24);
        writeInt(page);
        writeInt(totalPage);
        writeInt(guilds.size());
        writeInt(1);//praise remaining

        for(int i = 0; i < nbrGuilds; i++) {
            Guild guild = guilds.get(i);
            writeShort(n);
            writeShort(0);
            writeShort(n += 44);//offset name
            writeShort(n+= Strings.length(guild.getName()));//offset GM name
            writeShort(n += Strings.length(guild.getLeader().getName()));//unk
            writeShort(n += 2);//ad ?
            writeShort(n +2);//emblem
            writeInt(guild.getLevel());
            writeLong(0);//creation date
            writeInt(guild.getMembers().size());
            writeInt(0);
            writeInt(guild.getPraiseNumber());//praise
            writeByte(1);//can be praised
            writeInt(3);
            writeByte(0);
            writeString(guild.getName());
            writeString(guild.getLeader().getName());
            writeShort(0);
            writeShort(0);//ad (title ?)
            writeShort(0);//emblem

        }
    }
}
