package tera.gameserver.model.battlefields;

import rlib.data.DocumentXML;
import rlib.logging.Logger;
import rlib.logging.Loggers;
import tera.Config;
import tera.gameserver.document.DocumentBattlefield;
import tera.gameserver.document.DocumentDungeon;
import tera.gameserver.model.dungeons.DungeonList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BattlefieldList {
    private static final Logger log = Loggers.getLogger(BattlefieldList.class);
    private static List<BattlefieldList> list = new ArrayList<>();

    private int battleFieldId;
    private int maxLevel;
    private int minLevel;
    private String name;

    public BattlefieldList() {
        list.add(this);
    }

    public static void init() {
        DocumentXML<Void> document = new DocumentBattlefield(new File(Config.SERVER_DIR + "/data/battlefields.xml"));

        document.parse();

        log.info("Battlefield list initialized.");
    }

    public void setBattleFieldId(int battleFieldId) {
        this.battleFieldId = battleFieldId;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBattleFieldId() {
        return battleFieldId;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getName() {
        return name;
    }

    public static List<BattlefieldList> getBattlefieldAvailable(int level) {
        List<BattlefieldList> battlefields = new ArrayList<>();
        for(BattlefieldList battlefield : list) {
            if(level >= battlefield.getMinLevel() && level <= battlefield.getMaxLevel())
                battlefields.add(battlefield);
        }
        return battlefields;
    }

    public static List<BattlefieldList> getBattleFieldList() {
        return list;
    }
}
