package tera.gameserver.network.serverpackets;

import rlib.util.Strings;
import tera.gameserver.config.MissingConfig;
import tera.gameserver.model.Alliance;
import tera.gameserver.model.base.Experience;
import tera.gameserver.model.equipment.Equipment;
import tera.gameserver.model.equipment.Slot;
import tera.gameserver.model.equipment.SlotType;
import tera.gameserver.model.items.CrystalInstance;
import tera.gameserver.model.items.CrystalList;
import tera.gameserver.model.items.ItemInstance;
import tera.gameserver.model.playable.Player;
import tera.gameserver.model.skillengine.StatType;
import tera.gameserver.network.ServerPacketType;
import tera.gameserver.templates.PlayerTemplate;

public class S_User_Paperdoll_info extends ServerPacket {
    private static final ServerPacket instance = new S_User_Paperdoll_info();

    public static S_User_Paperdoll_info getInstance(Player player)
    {
        S_User_Paperdoll_info packet = (S_User_Paperdoll_info) instance.newInstance();

        packet.player = player;
        packet.model = 10000 + ((player.getRaceId() *2 + 1 + player.getSexId()) * 100) + player.getClassId() + 1;
        packet.attack = player.getAttack(null, null);
        packet.defense = player.getDefense(null, null);
        packet.impact = player.getImpact(null, null);
        packet.balance = player.getBalance(null, null);
        packet.weakResist = player.calcStat(StatType.WEAK_RECEPTIVE, 0, 0x20, null, null);
        packet.stunResist = player.calcStat(StatType.STUN_RECEPTIVE, 0, 0x20, null, null);
        packet.dmgResist = player.calcStat(StatType.DAMAGE_RECEPTIVE, 0, 0x20, null, null);

        return packet;
    }

    /** кол-во ожидания секунд */
    private Player player;
    private int model;
    private int attack;
    private int defense;
    private int impact;
    private int balance;
    private float weakResist;
    private float stunResist;
    private float dmgResist;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_USER_PAPERDOLL_INFO;
    }

    @Override
    protected final void writeImpl()
    {
        String guildName = (player.hasGuild()) ? player.getGuildName() : Strings.EMPTY;
        String guildRank = (player.hasGuild()) ? player.getGuildRank().getName() : Strings.EMPTY;

        Equipment equipment = player.getEquipment();

        int n = 298;
        int m = 298 + Strings.length(player.getName()) + Strings.length(player.getDescription()) + Strings.length(guildName) + Strings.length(guildRank);
        writeOpcode();
        writeShort(equipment.size());//count
        writeShort(m);//pos
        writeShort(0);//dungeon count
        writeShort(m + (equipment.size() * 142));//dungeon pos
        writeShort(0);
        writeShort(0);
        writeShort(n);//name pos (2A 01)
        writeShort(n += Strings.length(player.getName()));//description
        writeShort(n += Strings.length(player.getDescription()));//guild name
        writeShort(n + Strings.length(guildName));//guild rank
        writeInt(model);
        writeInt(player.getObjectId());
        writeInt(player.getSubId());
        writeInt(MissingConfig.SERVER_ID);
        writeInt(0);//player id -> 0 if same server
        writeInt(130);
        writeShort(player.getLevel());
        writeShort(player.getMiningLevel());
        writeShort(0);//?
        writeShort(player.getPlantLevel());
        writeShort(player.getEnergyLevel());
        writeShort(1);
        writeLong(player.getExp());
        writeLong(player.getExp());
        writeLong(Experience.LEVEL[player.getLevel() + 1]);
        writeInt(0);//?

        writeInt(equipment.getItemId(SlotType.SLOT_WEAPON));
        writeInt(equipment.getItemId(SlotType.SLOT_ARMOR));
        writeInt(equipment.getItemId(SlotType.SLOT_GLOVES));
        writeInt(equipment.getItemId(SlotType.SLOT_BOOTS));
        writeInt(0);//underwear
        writeInt(equipment.getItemId(SlotType.SLOT_HAT));
        writeInt(equipment.getItemId(SlotType.SLOT_MASK));

        PlayerTemplate template = player.getTemplate();
        writeInt(template.getPowerFactor());
        writeInt(template.getDefenseFactor());
        writeInt(template.getImpactFactor());
        writeInt(template.getBalanceFactor());
        writeShort(template.getRunSpd());
        writeShort(template.getAtkSpd());
        writeFloat(template.getCritRate());
        writeFloat(template.getCritRcpt());
        writeFloat(2); //crit power
        writeInt(player.getBaseAttack());
        writeInt(player.getBaseAttack());
        writeInt(player.getBaseDefense());
        writeInt(player.getBaseImpact());
        writeInt(player.getBaseBalance());
        writeFloat(weakResist);
        writeFloat(dmgResist);
        writeFloat(stunResist);
        writeInt(player.getPowerFactor() - template.getPowerFactor());
        writeInt(player.getDefenseFactor() - template.getDefenseFactor());
        writeInt(player.getImpactFactor() - template.getImpactFactor());
        writeInt(player.getBalanceFactor() - template.getBalanceFactor());
        writeShort(player.getRunSpeed() - template.getRunSpd());
        writeShort(player.getAtkSpd() - template.getAtkSpd());
        writeFloat(player.getCritRate(null, null) - template.getCritRate());
        writeFloat(player.getCritRateRcpt(null, null) - template.getCritRcpt());
        writeFloat(player.getCritDamage(null, null) - 2);
        writeInt(attack - player.getBaseAttack());
        writeInt(attack - player.getBaseAttack());
        writeInt(defense - player.getBaseDefense());
        writeInt(impact - player.getBaseImpact());
        writeInt(balance - player.getBaseBalance());
        writeFloat(player.calcStat(StatType.WEAK_RECEPTIVE, 0, null, null) - weakResist);
        writeFloat(player.calcStat(StatType.DAMAGE_RECEPTIVE, 0, null, null) - dmgResist);
        writeFloat(player.calcStat(StatType.STUN_RECEPTIVE, 0, null, null) - stunResist);
        writeShort(4);//vitality ?
        writeInt(player.getStamina());
        writeInt(player.getMaxStamina());
        writeInt(0);//itemLevel inventory
        writeInt(0);//itemlevel
        writeInt(player.getKarma());
        writeInt(0);//style head
        writeInt(0);//style face
        writeInt(0);//style back
        writeInt(0);//style weapon
        writeInt(0);//style body
        if(player.hasGuild()) {
            writeInt(player.getGuild().getAllianceId());
            if(player.getGuild().getAllianceId() != 0){
                int allianceClass = player.getAllianceClass();
                writeInt(allianceClass < Alliance.ADJUNCT_COMMANDER_ID ? Alliance.ECHELON_ID : allianceClass);//union class 100-200-301-302-303-400
                writeInt(allianceClass < Alliance.ADJUNCT_COMMANDER_ID ? allianceClass : 0);//union echelon
            }
            else
                writeLong(0);
        }
        else{
            writeInt(0);
            writeLong(0);
        }

        writeString(player.getName());
        writeString(player.getDescription());
        writeString(guildName);
        writeString(guildRank);

        equipment.lock();
        try {
            Slot[] slots = equipment.getSlots();

            for(int i = 0; i < equipment.size(); i++)
            {
                // получаем итем
                ItemInstance item = slots[i].getItem();

                // если его нет, пропускаем
                if(item == null)
                    continue;

                writeShort(m);

                m += 142;

                writeShort(m);//check last

                writeInt(item.getItemId());
                writeInt(item.getObjectId());
                writeInt(item.getSubId());
                writeInt(player.getObjectId());
                writeInt(player.getSubId());
                writeInt(i + 1);
                writeInt(0);
                writeInt((int)item.getItemCount());
                writeInt(item.getEnchantLevel());
                writeInt(buffer,0);
                writeByte((item.isBinded()) ? 1 : 0);//soulbind

                // получаем кристалы итема
                CrystalList crystals = item.getCrystals();

                // если кристалов нет
                if(crystals == null || crystals.isEmpty())
                {
                    writeInt(0); // 1 ячейка итема
                    writeInt(0); // 2 ячейка итема
                    writeInt(0); // 3 ячейка итема
                    writeInt(0); // 4 ячейка итема
                }
                else
                {
                    int diff = 4 - crystals.size();

                    CrystalInstance[] array = crystals.getArray();

                    // вносим вставленные кристалы
                    for(int g = 0, length = crystals.size(); g < length; g++)
                        writeInt(array[g].getItemId());

                    // если есть пустые слоты
                    if(diff > 0)
                        // указываем кол-во пустых слотов
                        for(int g = 0; g < diff; g++)
                            writeInt(0);
                }
//5 long 1 int
                writeInt(0);//crystal5
                writeInt(0);//+3 enchant
                writeInt(0);//+6 enchant
                writeInt(0);//+9 enchant
                writeInt(0);//+10 masterwork enchant
                writeInt(0);//+11 masterwork enchant
                writeInt(0);//+12 masterwork enchant



                writeInt(0);//remodel
                writeInt(0);//dye
                writeInt(0);//dye sec remaining
                writeLong(0);//dye date
                writeLong(0);//dye expiry date
                writeInt(0);
                writeInt(0);
                writeByte(item.getMasterworked());
                writeInt(item.isEnigma());//enigma
                writeInt(0);//masterwork bonus
                writeInt(0);//times brokered
                writeInt(0);//enchant advantage
            }
        }
        finally
        {
            equipment.unlock();
        }
        writeShort(0);
    }
}
