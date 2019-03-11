package tera.gameserver.network.serverpackets;

import tera.gameserver.model.battlefields.BattlefieldList;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

import java.util.List;

public class S_View_Battle_Field_Result extends ServerPacket {
    private static final ServerPacket instance = new S_View_Battle_Field_Result();

    public static S_View_Battle_Field_Result getInstance(Player player)
    {
        S_View_Battle_Field_Result packet = (S_View_Battle_Field_Result) instance.newInstance();

        packet.player = player;

        return packet;
    }

    /** кол-во ожидания секунд */
    private Player player;

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_VIEW_BATTLE_FIELD_RESULT;
    }

    @Override
    protected final void writeImpl()
    {
        int n = 16;
        List<BattlefieldList> battlefields = BattlefieldList.getBattleFieldList();
        writeOpcode();
        writeShort(battlefields.size());
        writeShort(n);
        writeInt(player.getObjectId());
        writeInt(28);//reset days

        for(int i = battlefields.size() - 1; i >= 0; i--) {
            writeShort(n);
            writeShort((i == 0) ? 0 : (n += 64));
            writeInt(battlefields.get(i).getBattleFieldId());
            writeInt(3);
            writeInt(0);//wins
            writeInt(0);//loses
            writeInt(0);//draws
            writeInt(0);//kills
            writeInt(0);//deaths
            writeInt(0);//assists
            writeInt(1000);//previous rating
            writeInt(0);
            writeInt(0);
            writeInt(0);//destroyed
            writeInt(1000);//rating
            writeInt(0);//icon -> 0/1/2 bronze/silver/gold
            writeInt(0);//captured
        }

    }
}
