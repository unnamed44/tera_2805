package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.model.Guild;
import tera.gameserver.model.GuildApply;
import tera.gameserver.model.GuildRank;
import tera.gameserver.model.playable.PlayerPreview;
import tera.gameserver.network.serverpackets.S_Guild_Apply_List;

import java.util.ArrayList;
import java.util.List;

public class C_Accept_Guild_Apply extends ClientPacket {
    private int count;
    private int accept;
    private List<GuildApply> applies = new ArrayList<>();
    private Guild guild;

    @Override
    protected void readImpl() {
        guild = owner.getOwner().getGuild();
        count = readShort();
        readShort();//pos
        accept = readByte();

        for(int i = 0; i < count; i++) {
            readShort();//current pos
            readShort();//next pos
            GuildApply apply = guild.getApplyByPlayerId(readInt());
            if(apply != null)
                applies.add(apply);
        }
    }

    @Override
    protected void runImpl() {
        for(GuildApply apply : applies) {
            DataBaseManager dataBaseManager = DataBaseManager.getInstance();
            if(accept == 1){
                dataBaseManager.removeAllGuildApply(apply);
                dataBaseManager.updatePlayerGuild(guild.getId(), GuildRank.GUILD_MEMBER, apply.getPlayerId());
                guild.resetMembers();
            }
            else
                dataBaseManager.removeGuildApply(apply, guild);
        }
        owner.getOwner().updateGuild();
        owner.getOwner().sendPacket(S_Guild_Apply_List.getInstance(guild),true);
    }
}
