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
	C_NPC_CONTACT(0xD2FD, new C_Npc_Contact()),
	C_DIALOG(0xB34E, new C_Dialog()),
	C_NOTIFY_LOCATION_IN_ACTION(0x68EE, new C_Notify_Player_In_Action()),
	C_BROADCAST_CLIMBING(0x9BF4, new C_Broadcast_Climbing()),
	C_START_CLIMBING(0xFBB0, new C_Start_Climbing()),
	C_END_CLIMBING(0x5820, new C_End_Climbing()),
	C_APPLY_TITLE(0x938E, new C_Apply_Title()),
	C_SOCIAL(0xACB6, new C_Social()),
	C_EQUIP_ITEM(0x64F2, new C_Equip_Item()),
	C_INVENTORY_AUTO_SORT(0x9F38, new C_Inventory_Auto_Sort()),
	C_MOVE_INVEN_POS(0x8892, new C_Move_Inven_Pos()),
	C_DEL_ITEM(0xD058, new C_Del_Item()),
	C_USE_ITEM(0x59BA, new C_Use_Item()),
	C_TOGGLE_TASK_INFO_WINDOW(0xB2D0, new C_Toggle_Task_Info_Window()),
	C_CANCEL_QUEST(0xC25F, new C_Cancel_Quest()),
	C_STR_EVALUATE_LIST(0xD81A, new C_Str_Evaluate_List()),
	C_SHOW_ITEM_TOOLTIP_EX(0xAA2A, new C_Show_Item_Tooltip_Ex()),
	C_TRY_LOOT_DROPITEM(0xC016, new C_Try_Loot_Dropitem()),
	C_SHOW_ITEM_TOOLTIP(0xBD00, new C_Show_Item_Tooltip()),
	C_REQUEST_RET_VILLAGE_INFO(0x70c6, new C_Request_Ret_Village_Info()),
	C_START_SKILL(0x7FC6, new C_START_SKILL()),
	C_START_INSTANCE_SKILL(0x729C, new C_Start_Instance_Skill()),
	C_START_COMBO_INSTANT_SKILL(0x72B9, new C_Start_Combo_Instant_Skill()),
	C_REQUEST_CONTRACT(0xA179, new C_Request_Contract()),
	C_REPLY_THROUGH_ARBITER_CONTRACT(0xAB63, new C_Reply_Through_Arbiter_Contract()),
	C_BAN_PARTY_MEMBER(0xA95D, new C_Ban_Party_Member()),
	C_LEAVE_PARTY(0x7741, new C_Leave_Party()),
	C_REQUEST_REFRESH_GUILD_DATA(0xF8C2, new C_Request_Refresh_Guild_Data()),
	C_GET_GUILD_HISTORY(0xB6E8, new C_Get_Guild_History()),
	C_GUILD_APPLY_LIST(0xF988, new C_Guild_Apply_List()),
	C_VIEW_MY_GUILD_WAR(0x9D4F, new C_View_My_Guild_War()),
	C_REQUEST_GUILD_LIST(0xAB20, new C_Request_Guild_List()),
	C_RECOMMEND_GUILD(0xB4A6, new C_Recommend_Guild()),
	C_REQUEST_GUILD_INFO_BEFORE_APPLY_GUILD(0xFB56, new C_Request_Guild_Info_Before_Apply_Guild()),
	C_APPLY_GUILD(0x8252, new C_Apply_Guild()),
	C_ACCEPT_GUILD_APPLY(0xF02A, new C_Accept_Guild_Apply()),
	C_COLLECTION_PICKSTART(0x8AA0, new C_Collection_Pickstart()),
	C_CREATE_GUILDGROUP(0x7E7E, new C_Create_Guildgroup()),
	C_REMOVE_GUILDGROUP(0xC881, new C_Remove_Guildgroup()),
	C_CHANGE_GUILDGROUP(0xFC2B, new C_Change_Guildgroup()),
	C_ASK_INTERACTIVE(0xBDA1, new C_Ask_Interactive()),
	C_CHANGE_GUILD_CHIEF(0x9E4B, new C_Change_Guild_Chief()),
	C_SET_GUILDGROUP_AUTHORITY(0x9102, new C_Set_Guildgroup_Authority()),
	C_UNION_MY_UNION(0xF1DA, new C_Union_My_Union()),
	C_VIEW_UNION_INFO(0xEA20, new C_View_Union_Info()),
	C_UNION_CHANGE_TAXRATE(0x8CF0, new C_Union_Change_taxrate()),
	C_UNION_CHANGE_NOTICE(0xB648, new C_Union_Change_Notice()),
	C_REQUEST_UPDATE_NOTICE(0xECAE, new C_Request_Update_Notice()),
	C_UPDATE_GUILD_TITLE(0xF6E0, new C_Update_Guild_Title()),
	C_REQUEST_UPDATE_INTRODUCE(0x9F2E, new C_Request_Update_Introduce()),





	REQUEST_WORLD_ZONE(0x5E43, new RequestWorldZone()),
	REQUEST_STATE(0xAB5E, new RequestState()),
	REQUEST_TAKING_ITEM(0xB983, new C_Equip_Item()),
	REQUEST_BANK_MOVING_ITEM(0xEFC9, new RequestBankMovingItem()),
	REQUEST_BANK_CHANGE_TAB(0xDBB0, new RequestBankChangeTab()),
	REQUEST_INVENTORY_ITEM_INFO(0xBC87, new RequestInventoryInfoItem()), // 28 00 F4 C4 1E 00 13 00 00 00 C4 71 00 00 00 00
	REQUEST_PICK_UP_ITEM(0x4E2D, new RequestPickUpItem()),
	REQUEST_USE_ITEM(0xE307, new C_Use_Item()), // 3A 00 F6 B0 AB 76 00 00 00 00 00 00 47 1F 00 00
	REQUEST_USE_SCROLL(0x8386, new C_Request_Ret_Village_Info()),
	REQUEST_ITEM_TEMPLATE_INFO(0xA9DA, new C_Show_Item_Tooltip()),

	REQUEST_GUILD_LEAVE(0x9EBD, new RequestGuildLeave()),
	REQUEST_GUILD_EXLUDE(0x8EB7, new RequestGuildExclude()),
	REQUEST_GUILD_UPDATE_RANK(0xFF13, new C_Change_Guildgroup()),
	REQUEST_GUIL_LOAD_ICON(0xF883, new RequestGuildLoadIcon()),
	REQUEST_GUILD_ICON_INFO(0xAC93, new RequestGuildIcon()),

	REQUEST_USE_QUEUE_SKILL(0xCED8, new C_Start_Combo_Instant_Skill()),
	REQUEST_USE_RANGE_SKILL(0x7017, new C_Start_Instance_Skill()),
	REQUEST_USE_RUSH_SKILL(0xBF14, new RequestUseRushSkill()),
	REQUEST_USE_DEFENSE_SKILL(0x57E6, new RequestUseDefenseSkill()),
	REQUEST_LOCK_ON_TARGET(0xD1F2, new RequestLockOnTarget()),
	REQUEST_SKILL_ACTION(0xA6B8, new RequestSkillAction()),
	UPDATE_HOT_KEY(0xB6BA, new UpdateHotKey()),
	REQUEST_RESSURECT(0x50D1, new RequestRessurect()), // 0C 00 20 E0 00 00 00 00 FF FF FF FF
	REQUEST_CONFIRM_SERVER(0x9FCA, new RequestConfirmServer()),
	REQUEST_CHECK_SERVER(0xD6C0, new RequestServerCheck()),
	REQUEST_LOCAL_TELEPORT(0xCADC, new RequestLocalTeleport()),
	REQUEST_DUEL_CANCEL(0xF0AE, new RequestDuelCancel()),

	REQUEST_NPC_ADD_BUY_SHOP(0xF406, new RequestNpcAddBuyShop()), // 1C 00 CC EB F5 5F 00 00 00 00 00 00 65 B1 B6 48
	REQUEST_NPC_SUB_BUY_SHOP(0xA087, new RequestNpcSubBuyShop()), // 20 00 12 CC F5 5F 00 00 00 00 00 00 65 B1 B6 48
	REQUEST_NPC_ADD_SELL_SHOP(0x8EC1, new RequestNpcAddSellShop()), // 18 00 5E F5 F5 5F 00 00 00 00 00 00 65 B1 B6 48
	REQUEST_NPC_SUB_SELL_SHOP(0x51D3, new RequestNpcSubSellShop()), // 18 00 74 D1 F5 5F 00 00 00 00 00 00 65 B1 B6 48
	REQUEST_NPC_CONFIRM_SHOP(0x8082, new RequestNpcConfirmShop()), // 10 00 77 65 F5 5F 00 00 00 00 00 00 65 B1 B6 48
	REQUEST_NPC_CONFIRM_SKILL_SHOP(0xC456, new RequestNpcConfirmSkillShop()),
	REQUEST_NPC_BANK_ADD(0xF25D, new RequestBankAdd()),
	REQUEST_NPC_BANK_SUB(0xEF1D, new RequestBankSub()),
	REQUEST_QUEST_PANEL(0xB1B2, new RequestUpdateQuestPanel()),
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
	REQUEST_ACTION_AGREE(0xA05D, new C_Reply_Through_Arbiter_Contract()),
	/** отмена акшена игроком, версия 172 */

	/** запрос на приглашение человека в пати, версия 172 */
	REQUEST_PARTY_INVITE(0x0111, new RequestPartyInvite()),
	/** запрос на выход из пати, версия 172 */
	/** запрос на изменение группе */
	REQUEST_PARTY_CHANGE(0x784F, new RequestPartyChange()),
	/** запрос на смену лидера в группе */
	REQUEST_PARTY_MAKE_LEADER(0xB14B, new RequestPartyMakeLeader()),
	/** запрос на кик из группы */
	REQUEST_PARTY_KICK(0xFD15, new C_Ban_Party_Member()),
	/** запрос на расформирование группы */
	REQUEST_PARTY_DISBAND(0xC041, new RequestPartyDisband()),

	/** клиентский запрос на сбор ресурса */

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