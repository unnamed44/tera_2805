package tera.gameserver.network.clientpackets;

public class C_Get_Guild_History extends ClientPacket {
    private int page;
    @Override
    protected void readImpl() {
        page = readInt();
    }

    @Override
    protected void runImpl() {
        //todo: impl
    }
}
