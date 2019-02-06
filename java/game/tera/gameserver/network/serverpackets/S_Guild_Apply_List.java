package tera.gameserver.network.serverpackets;

import rlib.util.Strings;
import rlib.util.table.IntKey;
import rlib.util.table.Table;
import tera.gameserver.model.Guild;
import tera.gameserver.model.GuildApply;
import tera.gameserver.model.GuildRank;
import tera.gameserver.network.ServerPacketType;

import java.util.Iterator;

public class S_Guild_Apply_List extends ServerPacket {
    private static final ServerPacket instance = new S_Guild_Apply_List();
    private Guild guild;

    public static S_Guild_Apply_List getInstance(Guild guild)
    {
        S_Guild_Apply_List packet = (S_Guild_Apply_List) instance.newInstance();

        packet.guild = guild;

        return packet;
    }
    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_GUILD_APPLY_LIST;
    }

    @Override
    protected final void writeImpl(){
        Table<IntKey, GuildApply> applies = guild.getApplies();
        writeOpcode();
        writeShort(applies.size());
        int n = 17;
        writeShort(17);//apply pos
        writeByte(1);
        writeInt(1);
        writeInt(1);

        synchronized(guild)
        {
            int k = 0;

            for(Iterator<GuildApply> iterator = applies.iterator(); iterator.hasNext();)
            {
                GuildApply apply = iterator.next();

                writeShort(n);
                int nameLength = Strings.length(apply.getPlayerName());
                int messageLength = Strings.length(apply.getMessage());

                k = nameLength + messageLength;

                n += (28 + k);

                if(iterator.hasNext())
                    writeShort(n); // A8 00
                else
                    writeShort(0);

                writeShort(n - k);// namepos
                writeShort(n - k + nameLength);//messagepos

                writeInt(apply.getPlayerId());
                writeInt(apply.getPlayerClass());
                writeInt(apply.getPlayerLevel());
                writeLong(0);
                writeString(apply.getPlayerName());
                writeString(apply.getMessage());
            }
        }
    }
}
