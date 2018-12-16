package tera.gameserver.network.serverpackets;

import tera.gameserver.network.ServerPacketType;

public class S_Check_Version extends ServerPacket
{
	public static final int SUCCESSFUL = 1;
	public static final int INCORRECT = 2;

	private static final ServerPacket instance = new S_Check_Version();

	public static S_Check_Version getInstance(int result)
	{
		S_Check_Version packet = (S_Check_Version) instance.newInstance();

		packet.result = result;

		return packet;
	}

	/** результат */
	private int result;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_CHECK_VERSION;
	}

	@Override
	protected void writeImpl()
	{
		switch(result)
		{
			case SUCCESSFUL:
			{
				writeOpcode();
				writeByte(1);

				owner.sendPacket(S_Auth_Successful.getInstance(), true);
				owner.sendPacket(S_Loading_Screen_Control_Info.getInstance(), true);
				owner.sendPacket(S_Remain_Play_Time.getInstance(), true);
				owner.sendPacket(S_Login_Arbiter.getInstance(), true);

				break;
			}
			case INCORRECT:	owner.sendPacket(S_Auth_Failed.getInstance(), true);
		}
	}
}