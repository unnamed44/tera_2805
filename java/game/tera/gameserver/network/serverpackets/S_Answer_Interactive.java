package tera.gameserver.network.serverpackets;

import tera.gameserver.config.MissingConfig;
import tera.gameserver.manager.DataBaseManager;
import tera.gameserver.model.playable.PlayerPreview;
import tera.gameserver.network.ServerPacketType;

public class S_Answer_Interactive extends ServerPacket {
    private static final ServerPacket instance = new S_Answer_Interactive();
    private String name;
    private PlayerPreview playerPreview;
    private int model;

    public static S_Answer_Interactive getInstance(String name)
    {
        S_Answer_Interactive packet = (S_Answer_Interactive) instance.newInstance();
        packet.name = name;
        packet.playerPreview = DataBaseManager.getInstance().getPreviewWithObjectName(name);
        packet.model = 10000 + ((packet.playerPreview.getRaceId() *2 + 1 + packet.playerPreview.getSex()) * 100) + packet.playerPreview.getClassId() + 1;

        return packet;
    }

    /** кол-во ожидания секунд */

    @Override
    public ServerPacketType getPacketType()
    {
        return ServerPacketType.S_ANSWER_INTERACTIVE;
    }

    @Override
    protected final void writeImpl()
    {
        writeOpcode();
        writeShort(24);
        writeInt(1);
        writeInt(model);//model ? Maybe used in chat
        writeInt(playerPreview.getLevel());
        writeByte(0);//has party
        writeByte(0);//has guild
        writeInt(MissingConfig.SERVER_ID);//SERVER ID
        writeString(name);
    }
}
