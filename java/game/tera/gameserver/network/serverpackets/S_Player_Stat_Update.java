package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.model.skillengine.StatType;
import tera.gameserver.network.ServerPacketType;
import tera.gameserver.network.serverpackets.ServerPacket;
import tera.gameserver.templates.PlayerTemplate;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by Luciole on 25/06/2016.
 */
public class S_Player_Stat_Update extends ServerPacket
{
    private static final ServerPacket instance = new S_Player_Stat_Update();

    public static S_Player_Stat_Update getInstance(Player player)
    {
        S_Player_Stat_Update packet = (S_Player_Stat_Update) instance.newInstance();

        // получаем подготавливаемый буффер
        ByteBuffer buffer = packet.getPrepare();

        try
        {
            // получаем шаблон игрока
            PlayerTemplate template = player.getTemplate();

            int attack = player.getAttack(null, null);
            int baseAttack = player.getBaseAttack();

            int defense = player.getDefense(null, null);
            int baseDefense = player.getBaseDefense();

            int impact = player.getImpact(null, null);
            int baseImpact = player.getBaseImpact();

            int balance = player.getBalance(null, null);
            int baseBalance = player.getBaseBalance();

            float weakResist = player.calcStat(StatType.WEAK_RECEPTIVE, 0, 0x20, null, null);
            float stunResist = player.calcStat(StatType.STUN_RECEPTIVE, 0, 0x20, null, null);
            float dmgResist = player.calcStat(StatType.DAMAGE_RECEPTIVE, 0, 0x20, null, null);

            packet.writeInt(buffer, player.getCurrentHp()); // ХП сколько есть
            packet.writeInt(buffer, player.getCurrentMp()); // МП сколько есть

            packet.writeInt(buffer, 0);

            packet.writeInt(buffer, player.getMaxHp());
            packet.writeInt(buffer, player.getMaxMp());
            packet.writeInt(buffer, template.getPowerFactor());
            packet.writeInt(buffer, template.getDefenseFactor());

            packet.writeInt(buffer, template.getImpactFactor());
            packet.writeInt(buffer, template.getBalanceFactor());
            packet.writeShort(buffer, template.getRunSpd()); // базовая скорость бега
            packet.writeShort(buffer,40);//walk speed
            packet.writeShort(buffer, template.getAtkSpd());

            packet.writeFloat(buffer, template.getCritRate()); // шанс крита
            packet.writeFloat(buffer, template.getCritRcpt()); // защита от крита (пока не ясно кд или шанс режет)
            packet.writeFloat(buffer, 2); //crit power
            packet.writeInt(buffer, baseAttack); // базовая атака мин
            packet.writeInt(buffer, baseAttack); // базовая атака макс
            packet.writeInt(buffer, baseDefense);
            packet.writeInt(buffer, baseImpact);
            packet.writeInt(buffer, baseBalance);
            packet.writeFloat(buffer, weakResist);// (Hex)Сопротивление к Ядам 38
            packet.writeFloat(buffer, dmgResist);// (Hex)Сопротивление к повреждениям 38
            packet.writeFloat(buffer, stunResist);// (Hex)Сопротивление к обиздвиживанию 38
            packet.writeInt(buffer, player.getPowerFactor() - template.getPowerFactor()); // бонус к повер фактору
            packet.writeInt(buffer, player.getDefenseFactor() - template.getDefenseFactor()); // бонус к дефенс фактору

            packet.writeInt(buffer, player.getImpactFactor() - template.getImpactFactor()); // бонус к импакт фактору
            packet.writeInt(buffer, player.getBalanceFactor() - template.getBalanceFactor()); // бонус к баланс фактору
            packet.writeShort(buffer, player.getRunSpeed() - template.getRunSpd()); // Бонус к скорости бега...
            packet.writeShort(buffer,0);//walk speed bonus
            packet.writeShort(buffer, player.getAtkSpd() - template.getAtkSpd()); // бонус к атак спиду

            packet.writeFloat(buffer, player.getCritRate(null, null) - template.getCritRate()); // крит рейт бонус
            packet.writeFloat(buffer, player.getCritRateRcpt(null, null) - template.getCritRcpt()); // крит ресист бонус
            packet.writeFloat(buffer, player.getCritDamage(null, null) - 2); // крит мощность бонус
            packet.writeInt(buffer, attack - baseAttack); // бонус к атаке мин
            packet.writeInt(buffer, attack - baseAttack); // бонус к атаке макс
            packet.writeInt(buffer, defense - baseDefense); // бонус к защите
            packet.writeInt(buffer, impact - baseImpact); // бонус к импакту
            packet.writeInt(buffer, balance - baseBalance); // бонус к балансу

            packet.writeFloat(buffer, player.calcStat(StatType.WEAK_RECEPTIVE, 0, null, null) - weakResist);
            packet.writeFloat(buffer, player.calcStat(StatType.DAMAGE_RECEPTIVE, 0, null, null) - dmgResist);
            packet.writeFloat(buffer, player.calcStat(StatType.STUN_RECEPTIVE, 0, null, null) - stunResist);
            packet.writeShort(buffer, player.getLevel());
            packet.writeByte(buffer, player.isBattleStanced() ? 1 : 0);
            packet.writeInt(buffer, 4);
            packet.writeInt(buffer, player.getMaxHp() - player.getBaseMaxHp());
            packet.writeInt(buffer, player.getMaxMp() - player.getBaseMaxMp());
            packet.writeInt(buffer, player.getStamina());
            packet.writeInt(buffer, player.getMaxStamina());
            packet.writeInt(buffer, 0);//re
            packet.writeInt(buffer, 0);//re max
            packet.writeInt(buffer, 0);//re bonus
            packet.writeInt(buffer, player.getKarma());

            packet.writeInt(buffer, 0);// Уровень брони
            packet.writeInt(buffer, 0);// Уровень брони
            packet.writeLong(buffer, 0);
            packet.writeInt(buffer, 8000);
            packet.writeInt(buffer, 3);//03000000

            return packet;
        }
        finally
        {
            buffer.flip();
        }
    }

    /** промежуточный буффер */
    private ByteBuffer prepare;

    public S_Player_Stat_Update()
    {
        this.prepare = ByteBuffer.allocate(20480).order(ByteOrder.LITTLE_ENDIAN);
    }

    @Override
    public void finalyze()
    {
        prepare.clear();
    }

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_PLAYER_STAT_UPDATE;
    }

    public ByteBuffer getPrepare()
    {
        return prepare;
    }

    @Override
    public boolean isSynchronized()
    {
        return false;
    }

    @Override
    protected void writeImpl(ByteBuffer buffer)
    {
        writeOpcode(buffer);

        // получаем промежуточный буффер
        ByteBuffer prepare = getPrepare();

        // переносим данные
        buffer.put(prepare.array(), 0, prepare.limit());
    }
}
