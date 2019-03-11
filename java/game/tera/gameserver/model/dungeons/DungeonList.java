package tera.gameserver.model.dungeons;

import rlib.data.DocumentXML;
import rlib.logging.Logger;
import rlib.logging.Loggers;
import tera.Config;
import tera.gameserver.config.MissingConfig;
import tera.gameserver.document.DocumentDungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DungeonList implements Dungeon {

    private static final Logger log = Loggers.getLogger(DungeonList.class);
    private static List<DungeonList> list = new ArrayList<>();
    private int dungeonId;
    private int dungeonMaxLevel;
    private int dungeonMinLevel;
    private int minItemLevel;
    private String name;

    public DungeonList() {
        list.add(this);
    }

    public static void init() {
        DocumentXML<Void> document = new DocumentDungeon(new File(Config.SERVER_DIR + "/data/dungeons.xml"));

        document.parse();

        log.info("Dungeon list initialized.");
    }

    public void setDungeonId(int dungeonId) {
        this.dungeonId = dungeonId;
    }

    public void setDungeonMaxLevel(int dungeonMaxLevel) {
        this.dungeonMaxLevel = dungeonMaxLevel;
    }

    public void setDungeonMinLevel(int dungeonMinLevel) {
        this.dungeonMinLevel = dungeonMinLevel;
    }

    public void setMinItemLevel(int minItemLevel) {
        this.minItemLevel = minItemLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDungeonId() {
        return dungeonId;
    }

    public int getDungeonMaxLevel() {
        return dungeonMaxLevel;
    }

    public int getDungeonMinLevel() {
        return dungeonMinLevel;
    }

    public int getMinItemLevel() {
        return minItemLevel;
    }

    public String getName() {
        return name;
    }

    public static List<DungeonList> getDungeonAvailableTroughtLevel(int level) {
        List<DungeonList> dungeons = new ArrayList<>();
        for(DungeonList dungeon : list) {
            if(level + MissingConfig.LEVEL_RANGE_DUNGEON >= dungeon.getDungeonMinLevel() && level < dungeon.getDungeonMaxLevel())
                dungeons.add(dungeon);
        }
        return dungeons;
    }

    public static DungeonList getDungeon(int dungeonId) {
        DungeonList dungeon = null;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getDungeonId() == dungeonId) {
                dungeon = list.get(i);
                break;
            }
        }
        return dungeon;
    }
}
