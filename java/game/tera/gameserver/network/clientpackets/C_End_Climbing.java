package tera.gameserver.network.clientpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.S_Instant_Move;

/**
 * ÐšÐ»Ð¸ÐµÐ½Ñ‚Ñ�ÐºÐ¸Ð¹ Ð¿Ð°ÐºÐµÑ‚ Ð´Ð»Ñ� ÑƒÐºÐ°Ð·Ð°Ð½Ð¸Ñ� Ð¿Ð¾Ð»Ð¾Ð¶ÐµÐ½Ð¸Ñ� Ð²Ð¾ Ð²Ñ€ÐµÐ¼Ñ� Ð»Ð°Ð·Ð°Ð½Ð¸Ñ�.
 *
 * @author Ronn Mod Evestu
 */
public class C_End_Climbing extends ClientPacket
{
    /** Ð¸Ð³Ñ€Ð¾Ðº */
    private Player player;

    /** Ñ†ÐµÐ»ÐµÐ²Ð°Ñ� Ñ‚Ð¾Ñ‡ÐºÐ° */
    private float targetX;
    private float targetY;
    private float targetZ;
    private int heading;
    @Override
    public void finalyze()
    {
        player = null;
    }

    @Override
    public void readImpl()
    {
        player = owner.getOwner();

        if(player == null)
            return;

        // Ñ†ÐµÐ»ÐµÐ²Ð°Ñ� Ñ‚Ð¾Ñ‡ÐºÐ°
        targetX = readFloat();
        targetY = readFloat();
        targetZ = readFloat();
        heading = readShort();
    }

    @Override
    public void runImpl()
    {
        if(player == null)
            return;

        player.setXYZ(targetX, targetY, targetZ);
        player.setHeading(heading);
        owner.sendPacket(S_Instant_Move.getInstance(player,targetX,targetY,targetZ,heading), true);
    }

    @Override
    public String toString()
    {
        return "PlayerClimbEnd  targetX = " + targetX + ", targetY = " + targetY + ", targetZ = " + targetZ + ", heading = "  + heading ;
    }
}