package tera.gameserver.model;

import rlib.logging.Logger;
import rlib.logging.Loggers;
import rlib.util.table.IntKey;
import rlib.util.table.Table;
import rlib.util.table.Tables;

public class Alliance {
    private static final Logger log = Loggers.getLogger(Alliance.class);


    private int allianceId;
    private int leaderId;
    private String leaderName;
    private String leaderGuildName;
    private int taxRate;
    private int strength;
    private int bonus;
    private String message;

    private final Table<IntKey, Guild> guilds;

    public Alliance(int allianceId, int leaderId, String leaderName, String leaderGuildName, int taxRate, int strength, int bonus, String message) {
        this.allianceId = allianceId;
        this.leaderId = leaderId;
        this.leaderName = (leaderName == null) ? "" : leaderName;
        this.leaderGuildName = leaderGuildName;
        this.taxRate = taxRate;
        this.strength = strength;
        this.bonus = bonus;
        this.message = message;

        this.guilds = Tables.newIntegerTable();

    }

    public int getAllianceId() {
        return allianceId;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public String getLeaderGuildName() {
        return leaderGuildName;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public int getStrength() {
        return strength;
    }

    public int getBonus() {
        return bonus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public Table<IntKey, Guild> getGuilds() {
        return guilds;
    }

    public void addGuild(Guild guild) {
        Table<IntKey, Guild> guilds = getGuilds();

        if(guilds.containsKey(guild.getObjectId())){
            log.warning("found duplicate " + guild + " for alliance " + getAllianceId());
            return;
        }

        guilds.put(guild.getObjectId(), guild);
    }
}
