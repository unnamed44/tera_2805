package tera.gameserver.model;

import rlib.util.pools.Foldable;
import rlib.util.pools.FoldablePool;
import rlib.util.pools.Pools;

public class GuildApply implements Foldable {
    private static final FoldablePool<GuildApply> pool = Pools.newConcurrentFoldablePool(GuildApply.class);

    private int playerId;
    private int playerClass;
    private int playerLevel;
    private String playerName;
    private String message;

    public static GuildApply newInstance(int playerId, int playerClass, int playerLevel, String playerName, String message) {
        GuildApply apply = pool.take();

        if(apply == null)
            apply = new GuildApply();
        apply.playerId = playerId;
        apply.playerClass = playerClass;
        apply.playerLevel = playerLevel;
        apply.playerName = playerName;
        apply.message = message;

        return apply;
    }

    @Override
    public void finalyze() {

    }

    @Override
    public void reinit() {

    }

    public void fold()
    {
        pool.put(this);
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPlayerClass() {
        return playerClass;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getMessage() {
        return message;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerClass(int playerClass) {
        this.playerClass = playerClass;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
