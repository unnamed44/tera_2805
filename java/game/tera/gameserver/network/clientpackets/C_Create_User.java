package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.PlayerManager;
import tera.gameserver.model.base.PlayerClass;
import tera.gameserver.model.base.Race;
import tera.gameserver.model.base.Sex;
import tera.gameserver.model.playable.PlayerAppearance;
import tera.gameserver.model.playable.PlayerDetails2;
import tera.gameserver.network.serverpackets.CreatePlayerResult;
import tera.gameserver.network.serverpackets.S_Create_User;

/**
 * Клиентский пакет для создания персонажа
 *
 * @author Ronn
 */
public class C_Create_User extends ClientPacket
{
	/** имя игрока */
	private String name;

	/** внешность игрока */
	private PlayerAppearance appearance;

	private PlayerDetails2 detailsappearance;

	/** пол игрока */
	private Sex sex;
	/** раса игрока */
	private Race race;
	/** класс игрока */
	private PlayerClass playerClass;

	@Override
	public void finalyze()
	{
		name = null;
		appearance = null;
	}

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	public void readImpl(){
		int name_pos = readShort();
		int detail_pos = readShort();
		int detail_2_pos = readShort();
		readInt();
		this.sex = Sex.valueOf(readInt());
		this.race = Race.valueOf(readInt(), sex);
		playerClass = PlayerClass.values()[readInt()];

		appearance = PlayerAppearance.getInstance(0);
		detailsappearance = PlayerDetails2.getInstance(0);

		readByte();
		appearance.setFaceColor(readByte());
		appearance.setFaceSkin(readByte());
		appearance.setAdormentsSkin(readByte());
		appearance.setFeaturesSkin(readByte());
		appearance.setFeaturesColor(readByte());
		appearance.setVoice(readByte());

		buffer.position(name_pos);
		name = readString();

		appearance.setBoneStructureBrow(readByte());
		appearance.setBoneStructureCheekbones(readByte());
		appearance.setBoneStructureJaw(readByte());
		appearance.setBoneStructureJawJut(readByte());

		appearance.setEarsRotation(readByte());
		appearance.setEarsExtension(readByte());
		appearance.setEarsTrim(readByte());
		appearance.setEarsSize(readByte());

		appearance.setEyesWidth(readByte());
		appearance.setEyesHeight(readByte());
		appearance.setEyesSeparation(readByte());

		readByte();

		appearance.setEyesAngle(readByte());
		appearance.setEyesInnerBrow(readByte());
		appearance.setEyesOuterBrow(readByte());

		readByte();

		appearance.setNoseExtension(readByte());
		appearance.setNoseSize(readByte());
		appearance.setNoseBridge(readByte());
		appearance.setNoseNostrilWidth(readByte());
		appearance.setNoseTipWidth(readByte());
		appearance.setNoseTip(readByte());
		appearance.setNoseNostrilFlare(readByte());

		appearance.setMouthPucker(readByte());
		appearance.setMouthPosition(readByte());
		appearance.setMouthWidth(readByte());
		appearance.setMouthLipThickness(readByte());
		appearance.setMouthCorners(readByte());

		appearance.setEyesShape(readByte());
		appearance.setNoseBend(readByte());
		appearance.setBoneStructureJawWidth(readByte());
		appearance.setMothGape(readByte());

		int[] Details2 = new int[64];
		for(int i = 0; i < 64;++i){
			Details2[i] = readByte();
		}
		detailsappearance.setDetails2(Details2);
	}

	@Override
	public void runImpl()
	{
		if(name == null)
			return;

		// получаем менеджера игроков
		PlayerManager playerManager = PlayerManager.getInstance();

		// пробуем создать игрока
		playerManager.createPlayer(getOwner(), appearance, detailsappearance, name, playerClass, race, sex);

		//System.out.println("appearance " + appearance.toXML(appearance, ""));
		//owner.sendPacket(CreatePlayerResult.getInstance(), true);
		owner.sendPacket(S_Create_User.getInstance(S_Create_User.SUCCESS), true);
	}
}