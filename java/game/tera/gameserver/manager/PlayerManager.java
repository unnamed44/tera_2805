package tera.gameserver.manager;

import rlib.logging.Logger;
import rlib.logging.Loggers;
import rlib.util.Strings;
import rlib.util.array.Array;
import rlib.util.array.Arrays;

import tera.Config;
import tera.gameserver.IdFactory;
import tera.gameserver.model.Account;
import tera.gameserver.model.Guild;
import tera.gameserver.model.World;
import tera.gameserver.model.base.Experience;
import tera.gameserver.model.base.PlayerClass;
import tera.gameserver.model.base.Race;
import tera.gameserver.model.base.Sex;
import tera.gameserver.model.equipment.Equipment;
import tera.gameserver.model.equipment.PlayerEquipment;
import tera.gameserver.model.inventory.Cell;
import tera.gameserver.model.inventory.Inventory;
import tera.gameserver.model.inventory.PlayerInventory;
import tera.gameserver.model.playable.Player;
import tera.gameserver.model.playable.PlayerAppearance;
import tera.gameserver.model.playable.PlayerDetails2;
import tera.gameserver.model.quests.QuestList;
import tera.gameserver.network.model.UserClient;
import tera.gameserver.network.serverpackets.*;
import tera.gameserver.tables.PlayerTable;
import tera.gameserver.taskmanager.RegenTaskManager;
import tera.gameserver.templates.PlayerTemplate;
import tera.util.Location;

import java.sql.DatabaseMetaData;

/**
 * Менеджер игроков.
 *
 * @author Ronn
 */
public final class PlayerManager
{
	private static final Logger log = Loggers.getLogger(PlayerManager.class);

	private static final Location BASE_POSITION = new Location(93545, -88207, -4524, 0, 0);

	private static final int BASE_WORLD_ID = 13;

	/**
	 * @return стартовая позиция игрока.
	 */
	public static Location getBasePosition()
	{
		return BASE_POSITION;
	}

	private static PlayerManager instance;

	public static PlayerManager getInstance()
	{
		if(instance == null)
			instance = new PlayerManager();

		return instance;
	}

	/** массив имен игроков */
	private final Array<String> playerNames;

	private PlayerManager()
	{
		this.playerNames = Arrays.toArray(String.class);

		log.info("initialized.");
	}

	/**
	 * Создание нового игрока.
	 *
	 * @param client клиент, который создает.
	 * @param appearance внешность игрока.
	 * @param name название игрока.
	 * @param playerClass класс игрока.
	 * @param race раса игрока.
	 * @param sex пол игрока.
	 */
	public synchronized void createPlayer(UserClient client, PlayerAppearance appearance, PlayerDetails2 details2, String name, PlayerClass playerClass, Race race, Sex sex)
	{
		// получаем аккаунт клиента
		Account account = client.getAccount();

		// если аккаунта нету
		if(account == null)
		{
			log.warning("not found account.");

			// отправляем результат
			client.sendPacket(CreatePlayerResult.getInstance(), true);

			// выходим
			return;
		}

		// получаем менеджера БД
		DataBaseManager dbManager = DataBaseManager.getInstance();

		if(!dbManager.isFreeName(name))
			return;

		// получаем фабрику ид
		IdFactory idFactory = IdFactory.getInstance();

		// получаем новый уникальный ид для игрока
		int objectId = idFactory.getNextPlayerId();

		// проверяем на занятость, и если занят, генерируем новый
		for(int i = 0; i < 10 && !dbManager.isFreePlayerId(objectId); i++)
			objectId = idFactory.getNextPlayerId();

		// если нормального ИД так и не нашли
		if(!dbManager.isFreePlayerId(objectId))
		{
			log.warning("incorrect player id.");

			// отправляем результат
			client.sendPacket(CreatePlayerResult.getInstance(), true);

			// выходим
			return;
		}

		// вносим уникальный ид во внешность
		appearance.setObjectId(objectId);
		details2.setObjectId(objectId);

		// получаем таблицу игроков
		PlayerTable playerTable = PlayerTable.getInstance();

		// получаем нужный нам шаблон
		PlayerTemplate template = playerTable.getTemplate(playerClass, race, sex);

		// если шаблон не нашли
		if(template == null)
		{
			log.warning("not found template.");

			// отправляем результат
    		client.sendPacket(CreatePlayerResult.getInstance(), true);

    		// выходим
    		return;
		}

		// создаем экземпляр игрока
		Player player = new Player(objectId, template);

		// вносим стартовый разворот
		player.setHeading(31912);

		// вносим время онлайна
		player.setOnlineTime(0);

		// вносим дату создания
		player.setCreateTime(System.currentTimeMillis());

		// вносим титул
		player.setTitle(Strings.EMPTY);

		// вносим гильдию
		player.setGuildId(0);

		// вносим уровень прав
		player.setAccessLevel(0);

		// вносим уровень персонажа
		if(player.getPlayerClass() == PlayerClass.REAPER){
			player.setLevel(40);
			player.setExp(Experience.LEVEL[player.getLevel() + 1]);
		}
		else{
			player.setLevel(1);
			player.setExp(0);
		}

		// вносим текущее состояние хп
		player.setCurrentHp(player.getMaxHp());
		// вносим текущее состояние мп
		player.setCurrentMp(player.getMaxMp());

		// указываем координаты
		player.setX(93545);
		// указываем координаты
		player.setY(-88207);
		// указываем координаты
		player.setZ(-4524);

		// указываем состояние усталости
		player.setStamina(120);

		// указываем имя
		player.setName(name);

		// указываем навыки сбора
		player.setPlantLevel(1);
		player.setEnergyLevel(1);
		player.setMiningLevel(1);

		// указываем ид зоны стартовой
		player.setZoneId(13);

		// устанавливаем внешнсоть
		player.setAppearance(appearance, false);
		player.setdetails2(details2, false);

		// если не удалось создать запись о игроке
		if(!dbManager.createPlayer(player, account.getName()))
		{
			log.warning("not ctreated player.");

			// отправляем результат
    		client.sendPacket(CreatePlayerResult.getInstance(), true);

    		// выходим
    		return;
		}

		// создаем в БД запись внешности
		dbManager.insertPlayerAppearance(appearance);

		dbManager.insertPlayerPresets2(details2);

		// создаем инвентарь
		Inventory inventory = PlayerInventory.newInstance(player, 1);

		// создаем экиперовку
		Equipment equipment = PlayerEquipment.newInstance(player);

		// вносим инвентарь игроку
		player.setInventory(inventory);

		// вносим экиперовку игроку
		player.setEquipment(equipment);

		// выдаем итемы в инвентарь
		template.giveItems(inventory);

		// получаем ячейки инвенторя
		Cell[] cells = inventory.getCells();

		// перебираем их
		for(Cell cell : cells)
			// если ячейка не пуста
			if(!cell.isEmpty())
				// пробуем одеть итем
				equipment.dressItem(inventory, cell);

		// выдаем базовые скилы
		template.giveSkills(player);

		// отправляем результат
		client.sendPacket(CreatePlayerResult.getInstance(), true);

		// обновляем зону игрока
		dbManager.updatePlayerZoneId(player);

		// удаляем игрока
		player.deleteMe();
	}

	/**
	 * Обработка входа в мир игроком.
	 *
	 * @param client клиент вошедшего игрока.
	 */
	public void enterInWorld(UserClient client)
	{
		// если клиента нету
		if(client == null)
		{
			log.warning("not found client.");
			return;
		}

		// получаем вошедшего игрока
		Player player = client.getOwner();

		// если игрока нету
		if(player == null)
		{
			log.warning("not found player.");
			return;
		}

		//player.sendPacket(Test29.getInstance(), false);
		//player.sendPacket(Test24.getInstance(), false);
		//player.sendPacket(Test25.getInstance(), false);

		//if(player.hasSettings())
		//	player.sendPacket(S_Load_Client_User_Setting.getInstance(player), true);

		//if(player.hasHotKey())
		//	player.sendPacket(HotKey.getInstance(player), true);

		//player.sendPacket(Test30.getInstance(), false);
		//player.sendPacket(Test27.getInstance(), false);
		if(player.hasGuild()) {
			player.updateGuild();
			player.sendPacket(S_Guild_Name.getInstance(player), true);
			player.sendPacket(S_Total_Guild_War_Data.getInstance(),true);
			player.sendPacket(S_Union_State_Info.getInstance(), true);
		}
		player.sendPacket(S_Spawn_Me.getInstance(player), true);

		player.spawnMe();

		//player.sendPacket(Test35.getInstance(player), true);
		player.sendPacket(S_Player_Stat_Update.getInstance(player), true);

		player.sendReuseSkills();
		player.sendReuseItems();
		player.sendEffects();

		if(player.isDead())
		{
			player.broadcastPacket(S_Creature_Life.getInstance(player, true));
			player.sendPacket(S_Show_Dead_UI.getInstance(World.getRegion(player).getZoneId(player)), true);
			player.sendPacket(S_Hide_HP.getInstance(player), true);
		}

		//if(player.isPK())
		//	player.sendPacket(S_Change_Relation.getInstance(S_Change_Relation.COLOR_ORANGE, player), true);
		//else 
		if(player.isPvPMode())
			player.sendPacket(S_Change_Relation.getInstance(S_Change_Relation.COLOR_RED, player), true);
	}

	/**
	 * Удаление персонажа с аккаунта.
	 *
	 * @param client клиент, который удаляет.
	 * @param objectId уникальный ид игрока.
	 */
	public synchronized void removePlayer(UserClient client, int objectId)
	{
		if(client == null)
		{
			log.warning(this, "not found client.");
			return;
		}

		// получаем аккаунт игрока
		Account account = client.getAccount();

		if(account == null)
		{
			log.warning(this, "not found account.");
			return;
		}

		// получаем менеджера БД
		DataBaseManager dbManager = DataBaseManager.getInstance();

		// пробуем удалить персонажа
		int result = dbManager.deletePlayer(objectId, account.getName());

		// отправляем результат
		client.sendPacket(S_Delete_User.getInstance(result), true);
	}

	/**
	 * Обработка выбора игрока для входа в мир.
	 *
	 * @param client клиент, который выбрал игрока.
	 * @param objectId уникальный ид игрока.
	 */
	public void selectPlayer(UserClient client, int objectId)
	{
		// если на сервере уже лимит онлайна, то выходим
		if(World.online() > Config.WORLD_MAXIMUM_ONLINE)
			return;

		// если клиента нету, выходим
		if(client == null)
		{
			log.warning("not found client.");
			return;
		}

		// получаем аккаунт
		Account account = client.getAccount();

		// если его нет, выходим
		if(account == null)
		{
			log.warning(new Exception("not found account."));
			return;
		}

		// получаем менеджера БД
		DataBaseManager dbManager = DataBaseManager.getInstance();

		// пробуем загрузить игрока
		Player player = dbManager.fullRestore(objectId, account);

		// если не загрузился, выходим
		if(player == null)
		{
			log.warning("incorrect restore player " + objectId);
			return;
		}

		// если нет прав на вход в мир, выходим
		if(player.getAccessLevel() < Config.WORLD_MIN_ACCESS_LEVEL)
			return;

		// пробуем получить игрока с таким же ником в мире
		Player old = World.getPlayer(player.getName());

		// если такой есть
		if(old != null)
		{
			// получаем его клиент
			UserClient con = old.getClient();

			// если клиент есть
			if(con != null)
				// закрываем его
				con.close();
		}

		// запоминаем за клиентом нового игрока
		client.setOwner(player);

		// запоминаем за игроком клиент
		player.setClient(client);

		// получаем менеджера регена
		RegenTaskManager regenManager = RegenTaskManager.getInstance();

		// добавляем вреген менеджер
		regenManager.addCharacter(player);

		DataBaseManager.getInstance().getPlayerDungeons(player);

		// добавляем в мир
		World.addNewPlayer(player);

		// пробуем получить гильдию
		Guild guild = player.getGuild();

		// если она есть
		if(guild != null)
			// добавляем его в онлаин
			guild.enterInGame(player);

		player.sendPacket(S_Select_User.getInstance(), true);
		player.sendPacket(S_Current_Election_State.getInstance(), false);
		player.sendPacket(S_Login.getInstance(player), true);
		if(player.isGM()){
			player.sendPacket(S_Qa_Set_Admin_Level.getInstance(),false);
		}
		player.sendPacket(S_Inven.getInstance(player, false, false), true);
		player.sendPacket(S_Skill_List.getInstance(player), true);
		player.sendPacket(S_Crest_Info.getInstance(player), true);
		player.sendPacket(S_Available_Social_List.getInstance(), false);
		player.sendPacket(S_Clear_Quest_Info.getInstance(), false);

		// получаем список квестов
		QuestList questList = player.getQuestList();

		// если квесты есть
		if(questList != null)
			questList.updateQuestList();

		player.sendPacket(S_Daily_Quest_Complete_Count.getInstance(),false);
		player.sendPacket(S_Artisan_Skill_List.getInstance(), false);
		player.sendPacket(S_Artisan_Recipe_List.getInstance(), false);
		player.sendPacket(S_Npcguild_List.getInstance(), false);
		player.sendPacket(S_Pet_Incubator_Info_Change.getInstance(), false);
		player.sendPacket(S_Pet_Info_Clear.getInstance(), false);
		player.sendPacket(S_Virtual_Latency.getInstance(), false);
		player.sendPacket(S_Move_Distance_Delta.getInstance(), false);
		player.sendPacket(S_My_Description.getInstance(player), false);
		player.sendPacket(S_Masstige_Status.getInstance(), false);
		player.sendPacket(S_Festival_List.getInstance(), false);

		int zoneId = player.getZoneId();

		if(zoneId < 1)
			player.setZoneId(player.getContinentId() + 1);

		player.sendPacket(S_Load_Topo.getInstance(player), true);
		player.sendPacket(S_Load_Hint.getInstance(), false);
		player.sendPacket(S_Account_Benefit_List.getInstance(), false);
		player.sendPacket(S_Fatigability_Point.getInstance(player.getAccount().getFatigability()), false);

		player.sendPacket(S_Update_Friend_Info.getInstance(player), true);

		if(player.hasSettings())
			player.sendPacket(S_Load_Client_User_Setting.getInstance(player), true);

		if(player.hasHotKey())
			player.sendPacket(HotKey.getInstance(player), true);

		player.sendPacket(S_Union_State_Info.getInstance(), true);

		// получаем менеджера событий
		ObjectEventManager eventManager = ObjectEventManager.getInstance();

		// уведомляем его
		eventManager.notifyPlayerSelect(player);
	}

	/**
	 * Восстановление персонажей на аккаунте.
	 *
	 * @param player восстанавливающий игрок.
	 */
	public synchronized void restoreCharacters(Player player)
	{
		// получаем аккаунт игрока
		Account account = player.getAccount();

		// если аккаунта нет, выходим
		if(account == null)
			return;

		// получаем контейнер имен
		Array<String> playerNames = getPlayerNames();

		// очищаем
		playerNames.clear();

		// получаем менеджер БД
		DataBaseManager dbManager = DataBaseManager.getInstance();

		// загружаем список имен
		dbManager.restorePlayerNames(playerNames, account.getName());

		// получаем имя этого игрока
		String playerName = player.getName();

		// получаем массив имен
		String[] array = playerNames.array();

		// перебираем имена
		for(int i = 0, length = playerNames.size(); i < length; i++)
		{
			// получаем имя игрока
			String name = array[i];

			// пропускаем этого же игрока
			if(name.equals(playerName))
				continue;

			// обновляем положение игрока в БД
			dbManager.updatePlayerLocation(BASE_POSITION, name, BASE_WORLD_ID);

			// сообщаем об этом
			player.sendMessage("Персонаж \"" + name + "\" был перемещен на стартовую позицию.");
		}
	}

	/**
	 * @return список имен игроков.
	 */
	public Array<String> getPlayerNames()
	{
		return playerNames;
	}
}
