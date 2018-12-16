package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import rlib.util.Strings;
import tera.gameserver.model.base.Experience;
import tera.gameserver.model.equipment.Equipment;
import tera.gameserver.model.equipment.SlotType;
import tera.gameserver.model.items.ItemInstance;
import tera.gameserver.model.playable.Player;
import tera.gameserver.model.playable.PlayerAppearance;
import tera.gameserver.model.playable.PlayerDetails2;
import tera.gameserver.network.ServerPacketType;

/**
 * Пакет с информаций о игроке, который входит в мир.
 * 
 * @author Ronn
 */
public class S_Login extends ServerPacket
{
	private static final ServerPacket instance = new S_Login();

	public static S_Login getInstance(Player player)
	{
		S_Login packet = (S_Login) instance.newInstance();

		Equipment equipment = player.getEquipment();

		PlayerAppearance appearance = player.getAppearance();
		PlayerDetails2 details2 = player.getdetails2();

		ByteBuffer buffer = packet.getPrepare();

		try
		{
			int n = 294;
			packet.writeShort(buffer, n);//Write name position
			packet.writeShort(buffer, n + Strings.length(player.getName()));
			packet.writeShort(buffer, 32);
			packet.writeShort(buffer, n + 32 + Strings.length(player.getName()));
			packet.writeShort(buffer, 64);

			packet.writeInt(buffer, player.getTemplateId());
			packet.writeInt(buffer, player.getObjectId());
			packet.writeInt(buffer, player.getSubId());

			packet.writeInt(buffer, 12);//server id
			packet.writeInt(buffer, player.getObjectId());

			packet.writeInt(buffer, 0);
			packet.writeByte(buffer, player.isDead() ? 0 : 1);// живой или мёртвый
			packet.writeInt(buffer, 0);//status
			packet.writeInt(buffer, 0);//walk speed ?
			packet.writeInt(buffer, player.getRunSpeed());//movement speed

			packet.writeByte(buffer, 101);// temp[9]
			packet.writeByte(buffer, appearance.getFaceColor());
			packet.writeByte(buffer, appearance.getFaceSkin());
			packet.writeByte(buffer, appearance.getAdormentsSkin());
			packet.writeByte(buffer, appearance.getFeaturesSkin());
			packet.writeByte(buffer, appearance.getFeaturesColor());
			packet.writeByte(buffer, appearance.getVoice());
			packet.writeByte(buffer, 0); // temp[14]
			packet.writeByte(buffer, 1);
			packet.writeByte(buffer, 0);
			packet.writeShort(buffer, player.getLevel());// уровень игрока

			packet.writeShort(buffer, player.getEnergyLevel());
			packet.writeShort(buffer, 0);
			packet.writeShort(buffer, player.getPlantLevel());
			packet.writeShort(buffer, player.getMiningLevel());
			packet.writeInt(buffer, 1);
			packet.writeShort(buffer, 0);

			packet.writeInt(buffer, 0);//playerkills

			if (player.getExp() != 0) {
				packet.writeLong(buffer, player.getExp()); // if first time on new chat should be set as 1
				packet.writeLong(buffer, player.getExp());
			} else {
				packet.writeLong(buffer, 1); // if first time on new chat should be set as 1
				packet.writeLong(buffer, 1);
			}
			packet.writeLong(buffer, Experience.LEVEL[player.getLevel() + 1]);// кол-во опыто необходимого для следующего лвла
			packet.writeInt(buffer, 0);

			packet.writeInt(buffer, 0);//rested current
			packet.writeInt(buffer, 0);//rested max
			packet.writeFloat(buffer, 1);
			packet.writeFloat(buffer, 1);

			ItemInstance weapon = equipment.getItem(SlotType.SLOT_WEAPON);

			equipment.lock();
			try {
				packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_WEAPON));
				packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_ARMOR));
				packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_BOOTS));
				packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_GLOVES));
				packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_MASK));
				packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_HAT));
			} finally {
				equipment.unlock();
			}

			packet.writeInt(buffer, 0);// бан чата // player.getObjectId());


			packet.writeInt(buffer, 0x0FB39ADB); // new
			packet.writeInt(buffer, 0);// new

			packet.writeLong(buffer, 1);
			packet.writeByte(buffer, 0);
			packet.writeInt(buffer, 0);// 03 00 00 00 если 3, то надпись "Ангел смерти"
			packet.writeInt(buffer, 0);// 00 00 00 00

			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, weapon == null ? 0 : weapon.getEnchantLevel());// точка

			packet.writeByte(buffer, 0);// 00

			packet.writeInt(buffer, player.getKarma());// 78 00 00 00 .//карма
			packet.writeInt(buffer, 1);// 01 00 00 00

			packet.writeInt(buffer, 0);// 00 00 00 00//00 00 00 00

			packet.writeInt(buffer, 0);// 00 00 00 00
			packet.writeInt(buffer, 0);// 00 00 00 00

			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeByte(buffer, 0);// 00
			packet.writeInt(buffer, 1);// 01 00 00 00 // new
			packet.writeInt(buffer, 0);// 00 00 00 00 // new
			packet.writeByte(buffer, 1);// 00 // new
			packet.writeInt(buffer, 0x00000064);// 64000000 // new
			packet.writeInt(buffer, 0x3F800000);// 0000803F // new
			packet.writeByte(buffer, 1);// 00 // new
			packet.writeInt(buffer, 1);// 00000000 // new
			packet.writeInt(buffer, 1);// 00000000 // new

			packet.writeString(buffer, player.getName());

			packet.writeByte(buffer, appearance.getBoneStructureBrow());
			packet.writeByte(buffer, appearance.getBoneStructureCheekbones());
			packet.writeByte(buffer, appearance.getBoneStructureJaw());
			packet.writeByte(buffer, appearance.getBoneStructureJawJut());
			packet.writeByte(buffer, appearance.getEarsRotation());
			packet.writeByte(buffer, appearance.getEarsExtension());
			packet.writeByte(buffer, appearance.getEarsTrim());
			packet.writeByte(buffer, appearance.getEarsSize());
			packet.writeByte(buffer, appearance.getEyesWidth());
			packet.writeByte(buffer, appearance.getEyesHeight());
			packet.writeByte(buffer, appearance.getEyesSeparation());
			packet.writeByte(buffer, 0); // temp[17]
			packet.writeByte(buffer, appearance.getEyesAngle());
			packet.writeByte(buffer, appearance.getEyesInnerBrow());
			packet.writeByte(buffer, appearance.getEyesOuterBrow());
			packet.writeByte(buffer, 0); // temp[18]
			packet.writeByte(buffer, appearance.getNoseExtension());
			packet.writeByte(buffer, appearance.getNoseSize());
			packet.writeByte(buffer, appearance.getNoseBridge());
			packet.writeByte(buffer, appearance.getNoseNostrilWidth());
			packet.writeByte(buffer, appearance.getNoseTipWidth());
			packet.writeByte(buffer, appearance.getNoseTip());
			packet.writeByte(buffer, appearance.getNoseNostrilFlare());
			packet.writeByte(buffer, appearance.getMouthPucker());
			packet.writeByte(buffer, appearance.getMouthPosition());
			packet.writeByte(buffer, appearance.getMouthWidth());
			packet.writeByte(buffer, appearance.getMouthLipThickness());
			packet.writeByte(buffer, appearance.getMouthCorners());
			packet.writeByte(buffer, appearance.getEyesShape());
			packet.writeByte(buffer, appearance.getNoseBend());
			packet.writeByte(buffer, appearance.getBoneStructureJawWidth());
			packet.writeByte(buffer, appearance.getMothGape());
			int[] jp = details2.getDetails2();
			for (int r = 0; r < 64; ++r) {
				packet.writeByte(buffer, jp[r]);
			}

			return packet;
		}
		finally
		{
			buffer.flip();
		}
	}

	/** промежуточный буффер */
	private final ByteBuffer prepare;

	public S_Login()
	{
		this.prepare = ByteBuffer.allocate(4096).order(ByteOrder.LITTLE_ENDIAN);
	}

	@Override
	public void finalyze()
	{
		prepare.clear();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_LOGIN;
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

		// получаем промежуточный буффер
		ByteBuffer prepare = getPrepare();

		// переносим данные
		buffer.put(prepare.array(), 0, prepare.limit());
	}

	/**
	 * @return подготовленный буфер.
	 */
	public ByteBuffer getPrepare()
	{
		return prepare;
	}
}