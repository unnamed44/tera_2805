package tera.gameserver.network.serverpackets;

import rlib.util.Strings;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

public class S_Guild_Name extends ServerPacket {

    private static final ServerPacket instance = new S_Guild_Name();

    public static S_Guild_Name getInstance(Player player)
    {
        S_Guild_Name packet = (S_Guild_Name) instance.newInstance();

        packet.player = player;

        return packet;
    }

    private Player player;

    @Override
    public ServerPacketType getPacketType() {
        return ServerPacketType.S_GUILD_NAME;
    }

    @Override
    protected final void writeImpl() {
        writeOpcode();
        int n = 20;
        writeShort(n);//offset guild name
        writeShort(n += Strings.length(player.getGuildName()));//offset rank
        writeShort(n += Strings.length(player.getGuildRank().getName()));//offset unk, (title ?)
        writeShort(n + Strings.length(player.getGuildTitle()));//offset guildemblem
        writeInt(player.getGuildId());
        writeInt(0);
        writeString(player.getGuildName());
        writeString(player.getGuildRank().getName());
        writeString(player.getGuildTitle());
        //writeShort(0);
        writeShort(0);
    }
}
