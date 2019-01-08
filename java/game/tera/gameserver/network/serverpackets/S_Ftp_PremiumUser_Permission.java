package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.network.ServerPacketType;

public class S_Ftp_PremiumUser_Permission extends ServerConstPacket
{
	private static final S_Ftp_PremiumUser_Permission instance = new S_Ftp_PremiumUser_Permission();

	public static S_Ftp_PremiumUser_Permission getInstance()
	{
		return instance;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_F2P_PremiumUser_Permission;
	}

	@Override
	protected void writeImpl(ByteBuffer buffer)
	{
		writeOpcode(buffer);
        writeInt(buffer, 0x00140001);
        writeInt(buffer, 0x00000005);
        writeInt(buffer, 0x3F800000);
        writeInt(buffer, 0x3F800000);
        writeInt(buffer, 0x00000014);
        writeInt(buffer, 0x00000001);
	}
}