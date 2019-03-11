package tera.gameserver.model.dungeons;

import rlib.util.pools.Foldable;

import java.util.Date;

public class PlayerDungeon implements Foldable {
    private int playerId;
    private int dungeonId;
    private int clearCount;
    private Date lastEntry;
    private int dailyCount;


    public PlayerDungeon(int playerId, int dungeonId, int clearCount, int lastEntry, int dailyCount){
        this.playerId = playerId;
        this.dungeonId = dungeonId;
        this.clearCount = clearCount;
        this.lastEntry = new Date(lastEntry);
        this.dailyCount = dailyCount;
    }

    public void setLastEntry(Date lastEntry) {
        this.lastEntry = lastEntry;
    }

    public void setClearCount(int clearCount) {
        this.clearCount = clearCount;
    }

    public void setDailyCount(int dailyCount) {
        this.dailyCount = dailyCount;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getDungeonId() {
        return dungeonId;
    }

    public int getClearCount() {
        return clearCount;
    }

    public Date getLastEntry() {
        return lastEntry;
    }

    public int getDailyCount() {
        return dailyCount;
    }

    @Override
    public void finalyze() {

    }

    @Override
    public void reinit() {

    }
}
