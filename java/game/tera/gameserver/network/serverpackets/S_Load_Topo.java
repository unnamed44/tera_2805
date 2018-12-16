package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет, отправляющий ид территории для загрузки клиентом.
 *
 * @author Ronn
 */
public class S_Load_Topo extends ServerPacket
{
	private static final ServerPacket instance = new S_Load_Topo();

	public static S_Load_Topo getInstance(Player player)
	{
		S_Load_Topo packet = (S_Load_Topo) instance.newInstance();

		packet.player = player;
		packet.zoneId = player.getZoneId();

		return packet;
	}

	public static S_Load_Topo getInstance(Player player, int zoneId)
	{
		S_Load_Topo packet = (S_Load_Topo) instance.newInstance();

		packet.player = player;
		packet.zoneId = zoneId;

		return packet;
	}

	/** игрок для которого нужно определить территорию */
	private Player player;

	/** ид зоны */
	private int zoneId;

	@Override
	public void finalyze()
	{
		player = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_LOAD_TOPO;
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
		writeInt(buffer, zoneId);
		writeFloat(buffer, player.getX());
		writeFloat(buffer, player.getY());
		writeFloat(buffer, player.getZ());
		writeByte(buffer, 0);
	}
}
