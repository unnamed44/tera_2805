package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет с настройками клиента.
 *
 * @author Ronn
 */
public class S_Load_Client_User_Setting extends ServerPacket
{
	private static final ServerPacket instance = new S_Load_Client_User_Setting();

	public static S_Load_Client_User_Setting getInstance(Player player)
	{
		S_Load_Client_User_Setting packet = (S_Load_Client_User_Setting) instance.newInstance();

		packet.setting = player.getSettings();

		return packet;
	}

	/** настройки клиента */
	private byte[] setting;

	@Override
	public void finalyze()
	{
		setting = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_LOAD_CLIENT_USER_SETTING;
	}

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	protected void writeImpl(ByteBuffer buffer)
	{
		if(setting != null)
		{
			writeOpcode(buffer);
			buffer.put(setting);
		}
	}
}
