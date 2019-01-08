package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;
import tera.util.Location;

/**
 * Серверный пакет об окончании каста скила.
 *
 * @author Ronn
 */
public class S_Action_End extends ServerPacket
{
	private static final ServerPacket instance = new S_Action_End();

	public static S_Action_End getInstance(Character caster, int castId, int skillId)
	{
		S_Action_End packet = (S_Action_End) instance.newInstance();

		if(caster == null)
			log.warning(packet, new Exception("not found caster"));

		packet.casterId = caster.getObjectId();
		packet.casterSubId = caster.getSubId();
		packet.modelId = caster.getModelId();
		packet.skillId = skillId;
		packet.castId = castId;

		caster.getLoc(packet.loc);

		return packet;
	}

	/** позиция кастующего */
	private final Location loc;

	/** ид кастующего */
	private int casterId;
	/** под ид кастующего */
	private int casterSubId;
	/** ид модели кастующего */
	private int modelId;

	/** ид скила */
	private int skillId;
	/** ид каста скила */
	private int castId;

	public S_Action_End()
	{
		this.loc = new Location();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_ACTION_END;
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
        writeInt(buffer, casterId);
        writeInt(buffer, casterSubId);
        writeFloat(buffer, loc.getX());
        writeFloat(buffer, loc.getY());
        writeFloat(buffer, loc.getZ());
        writeShort(buffer, loc.getHeading());
        writeInt(buffer, modelId);
        writeInt(buffer, skillId);
        writeInt(buffer, 0);
        writeInt(buffer, castId);
	}
}