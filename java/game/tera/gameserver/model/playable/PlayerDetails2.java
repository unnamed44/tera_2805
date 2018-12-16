package tera.gameserver.model.playable;

import java.lang.reflect.Field;

import rlib.logging.Loggers;
import rlib.util.ReflectionUtils;
import rlib.util.VarTable;
import rlib.util.array.Array;
import rlib.util.pools.Foldable;
import rlib.util.pools.FoldablePool;
import rlib.util.pools.Pools;

/**
 * ÐœÐ¾Ð´ÐµÐ»ÑŒ Ð¾Ð¿Ð¸Ñ�Ð°Ð½Ð¸Ñ� Ð²Ð½ÐµÑˆÐ½Ð¾Ñ�Ñ‚Ð¸ Ð¸Ð³Ñ€Ð¾ÐºÐ°.
 *
 * @author Ronn Mod Evestu
 */
public class PlayerDetails2 implements Foldable, Cloneable
{
    private static final FoldablePool<PlayerDetails2> pool = Pools.newConcurrentFoldablePool(PlayerDetails2.class);

    public static final PlayerDetails2 getInstance(int objectId)
    {
        PlayerDetails2 detailsappearance = pool.take();

        if(detailsappearance == null)
            detailsappearance = new PlayerDetails2();

        detailsappearance.setObjectId(objectId);

        return detailsappearance;
    }

    /** ÑƒÐ½Ð¸ÐºÐ°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¸Ð´ Ð¸Ð³Ñ€Ð¾ÐºÐ° */
    private int objectId;


    private int[] details2;

    public int[] getDetails2() {
        return details2;
    }

    public void setDetails2(int[] details2) {
        this.details2 = details2;
    }

    /**
     * @return objectId
     */
    public final int getObjectId()
    {
        return objectId;
    }

    /**
     * @param objectId Ð·Ð°Ð´Ð°Ð²Ð°ÐµÐ¼Ð¾Ðµ objectId
     */
    public final void setObjectId(int objectId)
    {
        this.objectId = objectId;
    }

    @Override
    public void finalyze(){}

    @Override
    public void reinit(){}

    public void fold()
    {
        pool.put(this);
    }

    public static String toXML(PlayerDetails2 detailsappearance, String id)
    {
        StringBuilder builder = new StringBuilder();

        builder.append("<detailsappearance id=\"").append(id).append("\" >\n");

        Array<Field> fields = ReflectionUtils.getAllFields(detailsappearance.getClass(), Object.class, true, "pool", "objectId");

        try
        {
            for(Field field : fields)
            {
                String name = field.getName();

                boolean old = field.isAccessible();

                field.setAccessible(true);

                String value = String.valueOf(field.get(detailsappearance));

                builder.append("	<set name=\"").append(name).append("\" value=\"")
                        .append(value).append("\" />").append("\n");

                field.setAccessible(old);
            }
        }
        catch(IllegalArgumentException | IllegalAccessException e)
        {
            Loggers.warning(detailsappearance.getClass(), e);
        }

        builder.append("</detailsappearance>");

        return builder.toString();
    }


    public static <T extends PlayerDetails2> T fromXML(T detailsappearance, VarTable vars)
    {
        Array<Field> fields = ReflectionUtils.getAllFields(detailsappearance.getClass(), Object.class, true, "pool", "objectId");

        try
        {
            for(Field field : fields)
            {
                boolean old = field.isAccessible();

                field.setAccessible(true);

                field.setInt(detailsappearance, vars.getInteger(field.getName(), field.getInt(detailsappearance)));

                field.setAccessible(old);
            }
        }
        catch(IllegalArgumentException | IllegalAccessException e)
        {
            Loggers.warning(detailsappearance.getClass(), e);
        }

        return detailsappearance;
    }

    /**
     * @return ÐºÐ¾Ð¿Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ðµ Ð²Ð½ÐµÑˆÐ½Ð¾Ñ�Ñ‚Ð¸.
     */
    public PlayerDetails2 copy()
    {
        try
        {
            return (PlayerDetails2) clone();
        }
        catch(CloneNotSupportedException e)
        {
            return null;
        }
    }


}