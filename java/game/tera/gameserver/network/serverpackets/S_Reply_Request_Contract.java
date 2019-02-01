package tera.gameserver.network.serverpackets;

import tera.gameserver.model.actions.ActionType;
import tera.gameserver.network.ServerPacketType;

public class S_Reply_Request_Contract extends ServerPacket
{
	private static final ServerPacket instance = new S_Reply_Request_Contract();

	private ActionType type;

	public static S_Reply_Request_Contract getInstance(ActionType type)
	{
		S_Reply_Request_Contract packet = (S_Reply_Request_Contract) instance.newInstance();

		packet.type = type;

		return packet;
	}
	@Override
	public ServerPacketType getPacketType() {
		return ServerPacketType.S_REPLY_REQUEST_CONTRACT;
	}

	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		writeInt(type.ordinal());
	}
}