package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.manager.GuildManager;
import tera.gameserver.model.Guild;
import tera.gameserver.model.GuildApply;
import tera.gameserver.model.MessageType;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Sytem_Message;

public class C_Apply_Guild extends ClientPacket {
    private String guildName;
    private String message;

    @Override
    protected void readImpl() {
        readShort();
        readShort();
        guildName = readString();
        message = readString();
    }

    @Override
    protected void runImpl() {
        Player player = owner.getOwner();
        Guild guild = GuildManager.getInstance().getGuildByName(guildName);

        GuildApply apply = GuildApply.newInstance(player.getObjectId(), player.getClassId(), player.getLevel(), player.getName(), message);
        guild.addApply(apply, true);

        S_Sytem_Message packet = S_Sytem_Message.getInstance(MessageType.YOU_APPLIED_TO_GUILD);
        packet.add("GuildName", guildName);
        getOwner().sendPacket(packet);
    }
}
