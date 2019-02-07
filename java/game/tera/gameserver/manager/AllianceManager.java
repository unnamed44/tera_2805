package tera.gameserver.manager;

import rlib.logging.Logger;
import rlib.logging.Loggers;
import rlib.util.table.IntKey;
import rlib.util.table.Table;
import rlib.util.table.Tables;
import tera.gameserver.model.Alliance;

import java.util.Iterator;

public final class AllianceManager {
    private static final Logger log = Loggers.getLogger(AllianceManager.class);

    private static AllianceManager instance;

    private Table<IntKey, Alliance> alliances;

    public static AllianceManager getInstance()
    {
        if(instance == null)
            instance = new AllianceManager();

        return instance;
    }

    private AllianceManager() {
        alliances = Tables.newConcurrentIntegerTable();

        DataBaseManager dataBaseManager = DataBaseManager.getInstance();
        dataBaseManager.restoreAlliance(alliances);

        for(Iterator<Alliance> iterator = alliances.iterator(); iterator.hasNext();) {
            Alliance alliance = iterator.next();
            dataBaseManager.restoreAllianceGuilds(alliance);
        }

        log.info("loaded " + alliances.size() + " alliance.");
    }

    public Alliance getAlliance(int id) {
        return alliances.get(id);
    }
}
