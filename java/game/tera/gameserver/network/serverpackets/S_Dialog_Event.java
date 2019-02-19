package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.model.npc.Npc;
import tera.gameserver.network.ServerPacketType;

/**
 * Серверный пакет для начало звукавого разговора нпс.
 *
 * @author Ronn
 */
public class S_Dialog_Event extends ServerPacket
{
	private static final ServerPacket instance = new S_Dialog_Event();

	public static S_Dialog_Event getInstance(Character target, Npc npc)
	{
		S_Dialog_Event packet = (S_Dialog_Event) instance.newInstance();

		packet.target = target;
		packet.npc = npc;

		return packet;
	}

	/** игрок, который заговорил с нпс */
	private Character target;

	/** нпс который говорит */
	private Character npc;

	@Override
	public void finalyze()
	{
		target = null;
		npc = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_DIALOG_EVENT;
	}

	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		writeInt(npc.getObjectId());
		writeInt(npc.getSubId());
		writeInt(target != null? target.getObjectId() : 0);
		writeInt(target != null? target.getSubId() : 0);
		writeInt(0);
		writeInt(0x00000001);
		writeInt(0);
	}
}