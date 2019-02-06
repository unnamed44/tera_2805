package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Уведомление о мыслях персонажа.
 *
 * @author Ronn
 */
public class S_User_Status extends ServerPacket
{
	public static enum NotifyType
	{
		/** плохая связь */
		BAD_NETWORK, // 0
		/** электричество на желтом фоне */
		BAD_ENERGY, // 1
		/** красный полный глаз */
		RED_FULL_EYE, // 2
		/** красный полу-глаз */
		RED_HALF_EYE, // 3
		/** восклицательныз знак на сером фоне */
		NOTICE, // 4
		/** белая хрень на фиолетовом фоне */
		PREPARE_SPELL, // 5
		/** восклицательный знак на красном фоне */
		NOTICE_AGRRESION, // 6
		/** восклицательный знак на желтом фоне */
		NOTICE_SUB_AGRRESSION, // 7
		/** 3 точки на желтом фоне */
		NOTICE_THINK, // 8
		/** полу глаз */
		NONE9, // 9
		NONE10, // 10
		NONE11, // 11
		NONE12, // 12
		/** злая морда на красном фоне */
		READ_REAR, // 13
		/** вопрос на желтом фоне */
		YELLOW_QUESTION, // 14
		NONE15, // 15
		/** хрень на красном фоне */
		NONE16, // 16
		/** хрень на темно-красном фоне */
		NONE17; // 17
	}

	private static final ServerPacket instance = new S_User_Status();

	public static S_User_Status getInstance(Character character, NotifyType type)
	{
		S_User_Status packet = (S_User_Status) instance.newInstance();

		packet.objectId = character.getObjectId();
		packet.subId = character.getSubId();
		packet.type = type;

		return packet;
	}

	/** тип мыслей */
	private NotifyType type;

	/** уникальный ид игрока */
	private int objectId;
	/** под ид игрока */
	private int subId;

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_USER_STATUS;
	}

	@Override
	protected final void writeImpl()
	{
        writeOpcode();
        writeInt(objectId);
        writeInt(subId);
        writeInt(type.ordinal());
        writeByte(0);
	}
}

