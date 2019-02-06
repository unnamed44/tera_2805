package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.GuildManager;
import tera.gameserver.model.MessageType;
import tera.gameserver.network.serverpackets.S_Reply_Guild_List;
import tera.gameserver.network.serverpackets.S_Sytem_Message;

public class C_Recommend_Guild extends ClientPacket {

    private String guildName;

    @Override
    protected void readImpl() {
        readShort();
        readByte();
        guildName = readString();
    }

    @Override
    protected void runImpl() {
        GuildManager.getInstance().getGuildByName(guildName).addPraise();

        S_Sytem_Message packet = S_Sytem_Message.getInstance(MessageType.YOU_PRAISED_GUILD);
        packet.add("GuildName", guildName);
        packet.add("RemainCount", Integer.toString(0));
        getOwner().sendPacket(packet);

        getOwner().sendPacket(S_Reply_Guild_List.getInstance(1));
    }
}
