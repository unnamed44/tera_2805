package tera.gameserver.network.serverpackets;

import tera.gameserver.model.Character;
import tera.gameserver.network.ServerPacketType;

/**
 * Пакет завершения эффекта на персонаже.
 * 
 * @author Ronn
 */
public class S_Abnormality_End extends ServerPacket
{
	private static final ServerPacket instance = new S_Abnormality_End();
	
	public static S_Abnormality_End getInstance(Character effected, int effectId)
	{
		S_Abnormality_End packet = (S_Abnormality_End) instance.newInstance();
		
		packet.effected = effected;
		packet.effectId = effectId;
		
		return packet;
	}
	
	/** персонаж, с которого спадает эффект */
	private Character effected;
	
	/** эффект, который спадает */
	private int effectId;
	
	@Override
	public void finalyze()
	{
		effected = null;
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_ABNORMALITY_END;
	}

	@Override
	protected void writeImpl()
	{
		writeOpcode();
		writeInt(effected.getObjectId());//обжект ид с кого спадает баф
		writeInt(effected.getSubId());//саб ид с кого спадает баф
		writeInt(effectId);//ид бафа который спал только что
	}
}

