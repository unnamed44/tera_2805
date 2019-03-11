package tera.gameserver.tasks;

import rlib.util.SafeTask;
import rlib.util.array.Array;
import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.manager.ExecutorManager;
import tera.gameserver.model.World;
import tera.gameserver.model.dungeons.Dungeon;
import tera.gameserver.model.playable.Player;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

public class DungeonTask extends SafeTask {
    private ScheduledFuture<DungeonTask> schedule;
    private static DungeonTask instance = new DungeonTask();

    public static DungeonTask getInstance() { return instance; }

    public DungeonTask() {
        ExecutorManager executor = ExecutorManager.getInstance();
        //every hours
        this.schedule = executor.scheduleGeneralAtFixedRate(this, 3600000 , 3600000);
    }

    @Override
    protected void runImpl() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("CST"));
        calendar.setTime(new Date());
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        if(calendar.get(Calendar.HOUR_OF_DAY) -11 != 7)
            return;
        DataBaseManager.getInstance().resetPlayerDungeons();
        Array<Player> players = World.getPlayers();
        for(int i = 0; i < players.size(); i++) {
            players.get(i).resetDungeons();
        }
    }
}
