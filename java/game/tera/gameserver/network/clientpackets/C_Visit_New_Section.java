package tera.gameserver.network.clientpackets;

public class C_Visit_New_Section extends ClientPacket {
    private int worldMapWorldId;
    private int worldMapGuardId;
    private int areaId;

    @Override
    protected void readImpl() {
        worldMapWorldId = readInt();
        worldMapGuardId = readInt();
        areaId = readInt();
    }

    @Override
    protected void runImpl() {
        /**
         * todo: Send S_VISIT_NEW_SECTION packet
         */
    }
}
