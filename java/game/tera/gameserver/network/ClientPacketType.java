package tera.gameserver.network;

import java.util.HashSet;

import rlib.logging.Logger;
import rlib.logging.Loggers;
import rlib.util.pools.FoldablePool;
import rlib.util.pools.Pools;
import tera.gameserver.network.clientpackets.*;

/**
 * Перечисление типов клиентских пакетов.
 * 
 * @author Ronn
 */
public enum ClientPacketType
{
	C_HARDWARE_INFO(0xB54D, new C_Hardware_Info()),
	C_LOGIN_ARBITER(0xB43D, new C_Login_Arbiter()),
	C_CHECK_VERSION(0x4DBC, new C_Check_Version()),
	C_GET_USER_LIST(0xFC33, new C_Get_User_List()),
	C_CAN_CREATE_USER(0xE025, new C_Can_Create_User()),
	C_CHECK_USERNAME(0xB041, new C_Check_Username()),
	C_CREATE_USER(0xB682, new C_Create_User()),
	C_DELETE_USER(0xBE59, new C_Delete_User()),
	C_SELECT_USER(0x68E8, new C_Select_User()),
	C_LOAD_TOPO_FIN(0xFFE1, new C_Load_Topo_Fin()),
	C_CHAT(0xA31B, new C_Chat()),
	C_RETURN_TO_LOBBY(0x70BF, new C_Return_To_Lobby()),
	C_EXIT(0xADDF, new C_Exit()),
	C_CANCEL_EXIT(0xA4B5, new C_Cancel_Exit()),
	C_CANCEL_RETURN_TO_LOBBY(0x95CD, new C_Cancel_Return_To_Lobby()),
	C_PLAYER_LOCATION(0x6466, new C_Player_Location()),
	C_SIMPLE_TIP_REPEAT_CHECK(0x94CF, new C_Simple_Tip_Repeat_Check()),
	C_TRADE_BROKER_HIGHEST_ITEM_LEVEL(0xEAD8, new C_Trade_Broker_Highest_Item_Level()),
	C_UNION_SUMMARY(0xD533, new C_Union_Summary()),
	C_UPDATE_CONTENTS_PLAYTIME(0x93F3, new C_Update_Content_Playtime()),
	C_GUARD_PK_POLICY(0xF020, new C_Guard_Pk_Policy()),
	C_VISIT_NEW_SECTION(0x7ADB, new C_Visit_New_Section()),
	C_REIGN_INFO(0xD48F, new C_Reign_Info()),
	C_DIALOG_EVENT(0xF9B1, new C_Dialog_Event()),
	C_SHOW_INVEN(0xBBE1, new C_Show_Inven()),
	C_SAVE_CLIENT_USER_SETTING(0xE7AD, new C_Save_Client_User_Settings()),
	C_UPDATE_FRIEND_INFO(0x7E9E, new C_Update_Friend_Info()),
	C_ADD_FRIEND(0xEBCC, new C_Add_Friend()),
	C_DELETE_FRIEND(0x83B8, new C_Delete_Friend()),
	C_WHISPER(0x5BC3, new C_Whisper()),





	/** перемещения игрока, версия 172 */
	/** запрос на перемещение во время лазания */
	PLAYER_CLIMB(0xEDC9, new PlayerClimb()),
	/** запрос на лазание */
	REQUESTPLAYER_CLIMB(0xBB06, new RequestStartClimb()),
	/** пакет с оправкой движения, версия */
	PLAYER_SKILL_MOVE(0x91A5, new PlayerMoveOnSkill()),
	/** приватный месседж от игрока, версия 172 */
	/** обновление титула игрока */
	UPDATE_TITLE(0x9CD4, new UpdateTitle()),

	/** запрос ид зоны клиентом, верия 172 */
	REQUEST_WORLD_ZONE(0x5E43, new RequestWorldZone()),
	/** запрос на возможность войти в состояние */
	REQUEST_STATE(0xAB5E, new RequestState()),

	/** запрос содержания инвенторя, версия 172 */
	/** запрос на одевание придмета, версия 172 */
	REQUEST_DRESSING_ITEM(0xC7B5, new RequestDressingItem()),
	/** запрос на снятие предмета, версия 172 */
	REQUEST_TAKING_ITEM(0xB983, new RequestDressingItem()),
	/** запрос на перемещение итема, версия 172 */
	REQUEST_INVENTORY_MOVING_ITEM(0x9BDA, new RequestInventoryMovingItem()),
	/** запрос на перемещение итема, версия 172 */
	REQUEST_BANK_MOVING_ITEM(0xEFC9, new RequestBankMovingItem()),
	/** запрос на смену вкладки в банке */
	REQUEST_BANK_CHANGE_TAB(0xDBB0, new RequestBankChangeTab()),
	/** запрос на сортировку инвенторя, версия 172 */
	REQUEST_SORT_INVENTORY(0xA92D, new RequestSortInventory()), //
	/** запрос на удаление итема с инвенторя, версия 172 */
	REQUEST_DELETE_ITEM(0xD9EC, new RequestDeleteItem()), // 14 00 35 F2 96 5F 00 00 00 00 00 00 04 00 00 00
	/** пакет, запрашивающий информацию о итеме, версия 172 */
	REQUEST_INVENTORY_ITEM_INFO(0xBC87, new RequestInventoryInfoItem()), // 28 00 F4 C4 1E 00 13 00 00 00 C4 71 00 00 00 00
	/** запрос на поднятие итема, версия 172 */
	REQUEST_PICK_UP_ITEM(0x4E2D, new RequestPickUpItem()),
	/** заппрос на использование итема, версия 172 */
	REQUEST_USE_ITEM(0xE307, new RequestUseItem()), // 3A 00 F6 B0 AB 76 00 00 00 00 00 00 47 1F 00 00
	/** запрос на использование свитка */
	REQUEST_USE_SCROLL(0x8386, new RequestUseScroll()),
	/** запрос на отображение инфы об темплейте итема, версия 172 */
	REQUEST_ITEM_TEMPLATE_INFO(0xA9DA, new RequestItemTemplateInfo()),

	/** запрос на запуск эмоции, версия 172 */
	REQUEST_START_EMOTION(0xA2F1, new RequestStartEmotion()),

	/** запрос инфы о клане, версия 172 */
	REQUEST_GUILD_INFO(0x81A4, new RequestGuildInfo()),
	/** запрос выхода из клана, версия 172 */
	REQUEST_GUILD_LEAVE(0x9EBD, new RequestGuildLeave()),
	/** апрос на исключение участника гильдии */
	REQUEST_GUILD_EXLUDE(0x8EB7, new RequestGuildExclude()),
	/** запрос на изменение ранга участника гильдии */
	REQUEST_GUILD_UPDATE_RANK(0xFF13, new RequestGuildUpdateRank()),
	/** запрос на удаление ранга гильдии */
	REQUEST_GUILD_REMOVE_RANK(0x81BD, new RequestGuildRemoveRank()),
	/** запрос на изменение прав у ранга */
	REQUEST_GUILD_CHANGE_RANK(0xD3D0, new RequestGuildChangeRank()),
	/** запрос на создание низкоправного ранга */
	REQUEST_GUILD_CREATE_RANK(0x7A56, new RequestGuildCreateRank()),
	/** запрос на загрузку иконки */
	REQUEST_GUIL_LOAD_ICON(0xF883, new RequestGuildLoadIcon()),
	/** запрос на иконку гильдии */
	REQUEST_GUILD_ICON_INFO(0xAC93, new RequestGuildIcon()),
	/** запрос на передачу мастера гильдии */
	REQUEST_GUILD_MAKE_LEADER(0xAD5E, new RequestGuildMakeLeader()),
	/** запрос на обновление титула гильдии */
	REQUEST_GUILD_UPDATE_TITLE(0xBACE, new RequestGuildUpdateTitle()),
	/** запрос на обновление титула гильдии */
	REQUEST_GUILD_UPDATE_MESSAGE(0xFDC7, new RequestGuildUpdateMessage()),
	/** запрос на обновление заметки игрока гильдии */
	REQUEST_GUILD_UPDATE_NOTE(0x4FE9, new RequestGuildUpdateNote()),

	/** запрос на юз мили скила, версия 172 */
	REQUEST_USE_SHORT_SKILL(0xCF34, new RequestUseShortSkill()),
	/** запрос на юз серийного скила, версия 172 */
	REQUEST_USE_QUEUE_SKILL(0xCED8, new RequestUseQueueSkill()),
	/** запрос на юз рендж скила, версия 172 */
	REQUEST_USE_RANGE_SKILL(0x7017, new RequestUseRangeSkill()),
	/** запрос на юз догоняющего скила, версия 172 */
	REQUEST_USE_RUSH_SKILL(0xBF14, new RequestUseRushSkill()),
	/** запрос на юз деф скила, версия 172 */
	REQUEST_USE_DEFENSE_SKILL(0x57E6, new RequestUseDefenseSkill()),
	/** запрос на добавление цели к лок он скилу */
	REQUEST_LOCK_ON_TARGET(0xD1F2, new RequestLockOnTarget()),
	/** запрос на действие над скилом */
	REQUEST_SKILL_ACTION(0xA6B8, new RequestSkillAction()),

	/** обновление параметров раскладки, версия 172 */
	UPDATE_HOT_KEY(0xB6BA, new UpdateHotKey()),
	/** обновление настроек клиента, версия 172 */

	/** запрос на воскрешение в город, версия 172 */
	REQUEST_RESSURECT(0x50D1, new RequestRessurect()), // 0C 00 20 E0 00 00 00 00 FF FF FF FF
	/** запрос на проверку сервера, версия 172 */
	REQUEST_CONFIRM_SERVER(0x9FCA, new RequestConfirmServer()),
	REQUEST_CHECK_SERVER(0xD6C0, new RequestServerCheck()),

	REQUEST_LOCAL_TELEPORT(0xCADC, new RequestLocalTeleport()),

	REQUEST_DUEL_CANCEL(0xF0AE, new RequestDuelCancel()),

	/** запрос на взаимодействие с нпс, версия 172 */
	REQUEST_NPC_INTERACTION(0xB6AE, new RequestNpcInteraction()), // 10 00 66 C3 1D F0 D4 1E 00 00 00 00 00 00 00 00
	/** запрос на обработку кнопки в диалоге, версия 172 */
	REQUEST_NPC_LINK(0xBF7B, new RequestNpcLink()), // 14 00 20 D9 40 7B D5 1E 01 00 00 00 FF FF FF FF
	/** клиентский пакет о пожении итема в магазин покупки, версия 172 */
	REQUEST_NPC_ADD_BUY_SHOP(0xF406, new RequestNpcAddBuyShop()), // 1C 00 CC EB F5 5F 00 00 00 00 00 00 65 B1 B6 48
	/** клиентский пакет о удаление итема из трейд покупки, версия 172 */
	REQUEST_NPC_SUB_BUY_SHOP(0xA087, new RequestNpcSubBuyShop()), // 20 00 12 CC F5 5F 00 00 00 00 00 00 65 B1 B6 48
	/** клиентский пакет о пожении итема в трейд покупки, версия 172 */
	REQUEST_NPC_ADD_SELL_SHOP(0x8EC1, new RequestNpcAddSellShop()), // 18 00 5E F5 F5 5F 00 00 00 00 00 00 65 B1 B6 48
	/** клиентский пакет о удаление итема из трейд покупки, версия 172 */
	REQUEST_NPC_SUB_SELL_SHOP(0x51D3, new RequestNpcSubSellShop()), // 18 00 74 D1 F5 5F 00 00 00 00 00 00 65 B1 B6 48
	/** клиентский пакет о подтверждении сделки в магазине, версия 172 */
	REQUEST_NPC_CONFIRM_SHOP(0x8082, new RequestNpcConfirmShop()), // 10 00 77 65 F5 5F 00 00 00 00 00 00 65 B1 B6 48
	/** запрос на изучение скила, версия 172 */
	REQUEST_NPC_CONFIRM_SKILL_SHOP(0xC456, new RequestNpcConfirmSkillShop()),
	/** запрос на добавление в банк */
	REQUEST_NPC_BANK_ADD(0xF25D, new RequestBankAdd()),
	/** запрос на получение итема из банка */
	REQUEST_NPC_BANK_SUB(0xEF1D, new RequestBankSub()),

	/** запрос на обновлении квеста на панели */
	REQUEST_QUEST_PANEL(0xB1B2, new RequestUpdateQuestPanel()),
	/** запрос на отмену квеста */
	REQUEST_QUEST_CANCEL(0x59AE, new RequestCancelQuest()),

	// TODO
	/** клиентский пакет с выбором скила для изучения */
	CLIENT_SELECT_SKILL_LEARN(0xE7E0, new SelectSkillLearn()),

	/** запрос на запуск полета на пегасе по указанному маршруту, версия 172 */
	REQUEST_NPC_START_PEGAS_FLY(0xC762, new RequestNpcStartPegasFly()), // 08 00 62 C7 08 00 00 00

	/** запрос на закрытие диалогов, версия 172 */
	REQUEST_DIALOG_CANCEL(0xBFB9, new RequestDialogCancel()), // 0C 00 31 C5 09 00 00 00 65 B1 B6 48

	/** запрос на блокировку трейда, версия 172 */
	REQUEST_TRADE_LOCK(0x637C, new RequestTradeLock()),
	/** запрос на добавление итема в трейд, версия 172 */
	REQUEST_TRADE_ADD_ITEM(0x683A, new RequestTradeAddItem()),
	/** согласие на начало трейда, версия 172 */
	ASSENT_TRADE(0x65A6, new AssentTrade()), // A6 65 03 00
	/** отмена трейда, версия 172 0x72D6 */
	CANCEL_TRADE(0x72D6, new CancelTrade()), // D6 72 03 00 00 00 00 00 00 00

	QUEST_MOVIE_ENDED(0x920B, new QuestMovieEnded()),

	/** запрос на приглашение игрока в акшен, версия 172 */
	REQUEST_ACTION_INVITE(0xADE2, new RequestActionInvite()),
	/** согласие на участие в акшене, версия 172 */
	REQUEST_ACTION_AGREE(0xA05D, new RequestActionAgree()),
	/** отмена акшена игроком, версия 172 */
	REQUEST_ACTION_CANCEL(0xE58F, new RequestActionCancel()),

	/** запрос на приглашение человека в пати, версия 172 */
	REQUEST_PARTY_INVITE(0x0111, new RequestPartyInvite()),
	/** запрос на выход из пати, версия 172 */
	REQUEST_PARTY_LEAVE(0x8002, new RequestPartyLeave()),
	/** запрос на изменение группе */
	REQUEST_PARTY_CHANGE(0x784F, new RequestPartyChange()),
	/** запрос на смену лидера в группе */
	REQUEST_PARTY_MAKE_LEADER(0xB14B, new RequestPartyMakeLeader()),
	/** запрос на кик из группы */
	REQUEST_PARTY_KICK(0xFD15, new RequestPartyKick()),
	/** запрос на расформирование группы */
	REQUEST_PARTY_DISBAND(0xC041, new RequestPartyDisband()),

	/** клиентский запрос на сбор ресурса */
	REQUEST_COLLECT_RESOURSE(0x94FD, new RequestCollectResourse()),

	/** запрос на список друзей */



	/** проверка на корректность имени, версия 172 */
	CAN_BE_USED_NAME(0x56E3, new CanBeUsedName()),
	/** пакет, запрашивающий возможность применить новое имя игроку, версия 172 */
	NAME_CHANGED(0xBB7A, new NameChange()),

	/** пакет с клиентским ключем */
	CLIENT_KEY(0xFFFF, new ClientKey()),

	/** запрос на добавление предмета в диалог заточки */
	REQUEST_ADD_ENCHANT_ITEM(0x5E4E, new RequestAddEnchantItem()),
	/** уведомление о завершении анимации заточки */
	ENCHANT_FINISH(0xDDB4, new EnchantFinish()),

	/** пакет входа в мир персонажа, версия */

	PLAYER_REQUEST_UNSTUCK(0x86E2, new RequestPlayerUnstuck());


	private static final Logger log = Loggers.getLogger(ClientPacketType.class);

	/** массив пакетов */
	private static ClientPacket[] packets;

	/**
	 * Возвращает новый экземпляр пакета в соответствии с опкодом
	 * 
	 * @param opcode опкод пакета.
	 * @return экземпляр нужного пакета.
	 */
	public static ClientPacket createPacket(int opcode)
	{
		ClientPacket packet = packets[opcode];
		return packet == null ? null : packet.newInstance();
	}

	/**
	 * Инициализация клиентских пакетов.
	 */
	public static void init()
	{
		HashSet<Integer> set = new HashSet<Integer>();

		ClientPacketType[] elements = values();

		for (ClientPacketType packet : elements)
		{
			int index = packet.getOpcode();

			if (set.contains(index))
				log.warning("found duplicate opcode " + index + " or " + Integer.toHexString(packet.getOpcode()) + " for " + packet + "!");

			set.add(index);
		}

		set.clear();

		packets = new ClientPacket[Short.MAX_VALUE * 2 + 2];

		for (ClientPacketType element : elements)
			packets[element.getOpcode()] = element.getPacket();

		log.info("client packets prepared.");
	}

	/** пул клиенстких пакетов */
	private final FoldablePool<ClientPacket> pool;

	/** экземпляр пакета */
	private final ClientPacket packet;

	/** опкод пакета */
	private int opcode;

	/**
	 * @param opcode опкод пакета.
	 * @param packet экземпляр пакета.
	 */
	private ClientPacketType(int opcode, ClientPacket packet)
	{
		this.opcode = opcode;
		this.packet = packet;
		this.packet.setPacketType(this);
		this.pool = Pools.newConcurrentFoldablePool(ClientPacket.class);
	}

	/**
	 * @return опкод пакета.
	 */
	public final int getOpcode()
	{
		return opcode;
	}

	/**
	 * @return экземпляр пакета.
	 */
	public final ClientPacket getPacket()
	{
		return packet;
	}

	/**
	 * @return получить пул пакетов соотвествующего типа.
	 */
	public final FoldablePool<ClientPacket> getPool()
	{
		return pool;
	}

	/**
	 * @param opcode опкод пакета.
	 */
	public final void setOpcode(int opcode)
	{
		this.opcode = opcode;
	}
}