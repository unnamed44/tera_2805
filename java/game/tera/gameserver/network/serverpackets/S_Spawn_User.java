package tera.gameserver.network.serverpackets;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import rlib.util.Strings;
import tera.gameserver.model.equipment.Equipment;
import tera.gameserver.model.equipment.SlotType;
import tera.gameserver.model.items.ItemInstance;
import tera.gameserver.model.playable.Player;
import tera.gameserver.model.playable.PlayerAppearance;
import tera.gameserver.model.playable.PlayerDetails2;
import tera.gameserver.network.ServerPacketType;

/**
 * Пакет показывает информацию игроку об другом игроке
 *
 * @author Ronn
 */
public class S_Spawn_User extends ServerPacket
{
	private static final ServerPacket instance = new S_Spawn_User();

	public static S_Spawn_User getInstance(Player newPlayer, Player player)
	{
		S_Spawn_User packet = (S_Spawn_User) instance.newInstance();

		ByteBuffer buffer = packet.getPrepare();

		int n = 256;// 194

		String name = newPlayer.getName();
		String guildName = newPlayer.getGuildName();
		String title = newPlayer.getTitle();
		String guildTitle = newPlayer.getGuildTitle();
		String iconName = newPlayer.getGuildIconName();

		// внешность игрока
		PlayerAppearance appearance = newPlayer.getAppearance();

		PlayerDetails2 details2 = newPlayer.getdetails2();

		// экиперовка игрока
		Equipment equipment = newPlayer.getEquipment();
		packet.writeShort(buffer, 0);
		packet.writeShort(buffer, 0);
		packet.writeShort(buffer, n); // name pos
		packet.writeShort(buffer, n += Strings.length(name)); //guild name pos
		packet.writeShort(buffer, n += Strings.length(guildName));//title pos
		packet.writeShort(buffer, n += Strings.length(title));// details 1 pos

		packet.writeShort(buffer, 32);// number of bytes to describe appearence
		packet.writeShort(buffer, (n += Strings.length(guildName)) + 30);//title pos
		packet.writeShort(buffer, n += 32);// Guild title description
		packet.writeShort(buffer, n += Strings.length(guildTitle));//details2 pos
		packet.writeShort(buffer, 64);

		packet.writeInt(buffer, 12);//server id
		packet.writeInt(buffer, newPlayer.getObjectId());
		packet.writeInt(buffer, newPlayer.getObjectId());// game id
		packet.writeInt(buffer, newPlayer.getSubId());
		packet.writeFloat(buffer, newPlayer.getX());
		packet.writeFloat(buffer, newPlayer.getY());
		packet.writeFloat(buffer, newPlayer.getZ());
		packet.writeShort(buffer, newPlayer.getHeading());

		packet.writeInt(buffer, player.getColor(newPlayer)); // relation
		packet.writeInt(buffer, newPlayer.getTemplateId());// template id
		packet.writeShort(buffer, 0);
		packet.writeShort(buffer, 130);
		packet.writeShort(buffer, 240);
		packet.writeShort(buffer, 0); // поза перса
		packet.writeShort(buffer, 2);
		packet.writeByte(buffer, 1);//visible
		packet.writeByte(buffer, newPlayer.isDead() ? 0 : 1); // alive

		// appearance
		packet.writeByte(buffer, 65); // temp[9]
		packet.writeByte(buffer, appearance.getFaceColor());
		packet.writeByte(buffer, appearance.getFaceSkin());
		packet.writeByte(buffer, appearance.getAdormentsSkin());
		packet.writeByte(buffer, appearance.getFeaturesSkin());
		packet.writeByte(buffer, appearance.getFeaturesColor());
		packet.writeByte(buffer, appearance.getVoice());
		packet.writeByte(buffer, 0); // temp[14]

		ItemInstance weapon = equipment.getItem(SlotType.SLOT_WEAPON);

		equipment.lock();
		try
		{
			// экиперованные итемы
			packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_WEAPON));
			packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_ARMOR));
			packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_BOOTS));
			packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_GLOVES));
			packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_MASK));
			packet.writeInt(buffer, equipment.getItemId(SlotType.SLOT_HAT));
		}
		finally
		{
			equipment.unlock();
		}
		packet.writeInt(buffer, 0);// ??? Maybe underwear
		packet.writeInt(buffer, newPlayer.isSpawned() ? 1 : 0); // вспышка
		packet.writeInt(buffer, newPlayer.getMountId()); // mount

		packet.writeInt(buffer, 0);//pose see C_PLAYER_LOCATION
		packet.writeInt(buffer, 0);//title
		packet.writeLong(buffer, 0);//shuttleID
		packet.writeLong(buffer, 0);
		packet.writeLong(buffer, 0);
		packet.writeLong(buffer, 0);
		packet.writeLong(buffer, 0);
		packet.writeInt(buffer, 0);

		packet.writeShort(buffer, 0);
		packet.writeByte(buffer, 0);

		packet.writeInt(buffer, weapon == null ? 0 : weapon.getEnchantLevel()); // Weapon enchant

		packet.writeByte(buffer, 0);//newbie
		packet.writeByte(buffer, newPlayer.isPvPMode() ? 1 : 0); // включен ли пвп режим

		packet.writeInt(buffer, newPlayer.getLevel());
		packet.writeInt(buffer, 0);

		packet.writeInt(buffer, 0);// что-то тоже нательное

		packet.writeByte(buffer, 1);

		packet.writeInt(buffer, 0);// лифчики
		packet.writeInt(buffer, 0);
		packet.writeInt(buffer, 0);
		//packet.writeInt(buffer, 0);
		//packet.writeInt(buffer, 0);
		//packet.writeInt(buffer, 0);
		//packet.writeInt(buffer, 0);
		//packet.writeInt(buffer, 0);

		//packet.writeInt(buffer, 0);
		//packet.writeInt(buffer, 0);
		//packet.writeInt(buffer, 0);

		packet.writeByte(buffer, 0);

		packet.writeLong(buffer, 0);
		packet.writeLong(buffer, 0);
		packet.writeInt(buffer, 0);
		if(newPlayer.hasGuild())
			packet.writeInt(buffer, newPlayer.getGuild().getAllianceId());//alliance
		else
			packet.writeInt(buffer, 0);
		packet.writeLong(buffer, 0);

		packet.writeByte(buffer, 1);
		packet.writeInt(buffer, 0);
		packet.writeInt(buffer, 0);
		packet.writeInt(buffer, 100);
		packet.writeFloat(buffer, 1);

		packet.writeString(buffer, name);// имя
		packet.writeString(buffer, guildName);// название клана
		packet.writeString(buffer, title); // титул

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

		packet.writeString(buffer, guildName);
		packet.writeString(buffer, guildTitle);

		int[] jp = details2.getDetails2();
		for (int r = 0; r < 64; ++r) {
			packet.writeByte(buffer, jp[r]);
		}

		buffer.flip();

		return packet;
	}

	/** подготавливаемый буффер */
	private final ByteBuffer prepare;

	public S_Spawn_User()
	{
		this.prepare = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
	}

	@Override
	public void finalyze()
	{
		prepare.clear();
	}

	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.S_SPAWN_USER;
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
	 * @return подготавливаемый буффер.
	 */
	public ByteBuffer getPrepare()
	{
		return prepare;
	}
}