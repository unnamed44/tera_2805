package tera.gameserver.model.regenerations;

import tera.gameserver.manager.ObjectEventManager;
import tera.gameserver.model.Account;
import tera.gameserver.model.playable.Player;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PlayerRegenFatigability extends AbstractRegen<Player> {

    public PlayerRegenFatigability(Player actor) {
        super(actor);
    }

    private Account account;
    @Override
    public boolean checkCondition() {
        account = getActor().getAccount();
        if(account == null) {
            return false;
        }

        long diff = Math.abs((new Date()).getTime() - account.getLastFatigabilityCheck().getTime());
        return (account != null && account.getFatigability() < 4000 && TimeUnit.MILLISECONDS.toMinutes(diff) >= 5);
    }

    @Override
    public void doRegen() {
        account.setFatigability(account.getFatigability() + 5);
        account.setLastFatigabilityCheck(new Date());

        ObjectEventManager eventManager = ObjectEventManager.getInstance();

        // обновляем отображение хп
        eventManager.notifyFatigabilityChanged(getActor());
    }
}
