package tera.gameserver.network;

/**
 * @author Ronn
 */
public enum ServerPacketType
{
	S_CHECK_VERSION(0x4DBD),
	S_AUTH_FAILED(0xD5A2),
	S_AUTH_SUCCESSFUL(0xD121),
	S_LOADING_SCREEN_CONTROL_INFO(0x7157),
	S_REMAIN_PLAY_TIME(0xA808),
	S_LOGIN_ARBITER(0xCD11),
	S_GET_USER_LIST(0xB598),
	S_CAN_CREATE_USER(0xBA47),
	S_CHECK_USERNAME(0xDA83),
	S_CREATE_USER(0xF3A5),
	S_DELETE_PLAYER(0x7F92),
	S_SELECT_USER(0xE2CD),
	S_CURRENT_ELECTION_STATE(0xB5B7),
	S_LOGIN(0x8F8E),
	S_INVEN(0xA930),
	S_SKILL_LIST(0xB3B0),
	S_AVAILABLE_SOCIAL_LIST(0xA674),
	S_CLEAR_QUEST_INFO(0xFC85),
	S_ARTISAN_SKILL_LIST(0xACFF),
	S_ARTISAN_RECIPE_LIST(0xC095),
	S_NPC_GUILD_LIST(0xCF73),
	S_PET_INCUBATOR_INFO_CHANGE(0xB247),
	S_PET_INFO_CLEAR(0xBAD1),
	S_VIRTUAL_LATENCY(0xA03F),
	S_MOVE_DISTANCE_DELTA(0xD413),
	S_MY_DESCRIPTION(0x74F5),
	S_MASSTIGE_STATUS(0x692B),
	S_FESTIVAL_LIST(0x8E32),
	S_LOAD_TOPO(0x81ED),
	S_LOAD_HINT(0xE99F),
	S_ACCOUNT_BENEFIT_LIST(0xBEF6),
	S_SPAWN_ME(0xD469),
	S_PLAYER_STAT_UPDATE(0xB8D9),
	S_CHAT(0x6241),
	S_SPAWN_NPC(0x77D1),
	S_QA_SET_ADMIN_LEVEL(0x5312),
	S_RETURN_TO_LOBBY(0x9278),
	S_CANCEL_RETURN_TO_LOBBY(0xD887),
	S_EXIT(0xA3C9),
	S_CANCEL_EXIT(0xBBB9),
	S_USER_LOCATION(0x7B5B),
	S_TRADE_BROKER_HIGHEST_ITEM_LEVEL(0xDD82),
	S_UNION_SUMMARY(0xA2E1),
	S_GUARD_PK_POLICY(0x582C),
	S_REIGN_INFO(0x9818),
	S_LOAD_CLIENT_USER_SETTING(0xED33),
	S_UPDATE_FRIEND_INFO(0x99D8),
	S_FRIEND_LIST(0x4FE9),
	S_DESPAWN_NPC(0xEC50),
	S_NPC_MENU_SELECT(0x6109),
	S_DIALOG(0xB0B6),
	S_NPC_STATUS(0xE7D9),
	//NPC_DIALOG_REPLY(),
	S_SPAWN_USER(0x7CA5),
	S_SEND_USER_PLAY_TIME(0x7010),
	S_INSTANT_MOVE(0x976D),
	S_F2P_PremiumUser_Permission(0xDBB3),
	S_LOAD_CLIENT_ACCOUNT_SETTING(0x7DEB),
	S_CREATURE_CHANGE_HP(0x9E53),
	S_ACCOUNT_PACKAGE_LIST(0xD642),
	S_LOGIN_ACCOUNT_INFO(0x5611),
	S_NOTIFY_CHANGE_CLASS_AND_ELITE(0xF53E),
	S_PCBANGINVENTORY_DATALIST(0xD3FC),
	S_PLAYER_CHANGE_MP(0xD3CA),
	S_SIMPLE_TIP_REPEAT_CHECK(0xF93A),
	S_SOCIAL(0xAFCA),
	S_MY_CONDITION(0xC67F),
	S_SPAWN_BONFIRE(0x7AB4),
	S_DESPAWN_BONFIRE(0xB10E),
	S_SYSTEM_MESSAGE(0xAC94),
	S_SHOW_HP(0xC7E0),
	S_PLAY_MOVIE(0xC8DE),
	S_UPDATE_QUEST(0x8880),
	S_ABNORMALITY_BEGIN(0xAAC1),
	S_ABNORMALITY_END(0x9404),
	S_PLAYER_CHANGE_EXP(0x8E49),
	S_LOOT_DROPITEM(0xB0C3),
	S_CREATURE_ROTATE(0xACC9),
	S_VISIT_NEW_SECTION(0x9D51),
	S_SHOW_ITEM_TOOLTIP(0x7242),
	S_SPAWN_DROPITEM(0xDDA4),
	S_UNMOUNT_VEHICLE(0x6D02),
	S_MOUNT_VEHICLE(0xB474),
	S_QUEST_VILLAGER_INFO(0xB5C5),
	S_NPC_LOCATION(0x65E8),
	S_ACTION_END(0xF3B3),
	S_ACTION_STAGE(0x5B2F),
	S_EACH_SKILL_RESULT(0xF621),
	S_SKILL_LEARN_LIST(0x5399),
	S_START_COOLTIME_SKILL(0x9A06),
	S_FATIGABILITY_POINT(0xB229),
	S_CHANGE_RELATION(0xB062),
	S_REQUEST_CONTRACT(0xD381),
	S_REPLY_REQUEST_CONTRACT(0x91EB),
	S_CANCEL_CONTRACT(0xCE6E),
	S_BEGIN_THROUGH_ARBITER_CONTRACT(0xFCEE),
	S_PARTY_MEMBER_LIST(0x7028),
	S_PARTY_MEMBER_STAT_UPDATE(0xC5D9),
	S_PARTY_MEMBER_BUFF_UPDATE(0xD08E),
	S_LEAVE_PARTY_MEMBER(0xA7B8),
	S_PARTY_MEMBER_INTERVAL_POS_UPDATE(0x6BA3),
	S_BAN_PARTY_MEMBER(0x9067),
	S_EMPTY_GUILD_WINDOW(0xB26B),
	S_REPLY_GUILD_LIST(0xFF9C),
	S_REQUEST_GUILD_INFO_BEFORE_APPLY_GUILD(0xF606),
	S_VIEW_MY_GUILD_WAR(0xA635),
	S_GUILD_NAME(0x623F),
	S_GUILD_INFO(0xA842),
	S_GUILD_MEMBER_LIST(0xF7C5),
	S_GUILD_APPLY_LIST(0x8162),
	S_TOTAL_GUILD_WAR_DATA(0xED01),
	S_SPAWN_COLLECTION(0xF511),
	S_DESPAWN_COLLECTION(0xD51F),
	S_COLLECTION_PICKSTART(0xCEAF),
	S_COLLECTION_PICKEND(0x9836),
	S_PLAYER_CHANGE_PROF(0xCF6F),
	S_COLLECTION_PROGRESS(0xB649),
	S_USER_STATUS(0xB4FB),
	S_UNION_STATE_INFO(0xE38F),
	S_ADD_GUILD_GROUP(0x8299),
	S_REMOVE_GUILD_GROUP(0x93EA),
	S_GUILD_ANNOUNCE(0xBBC7),
	S_ANSWER_INTERACTIVE(0xF851),
	S_CHANGE_GUILD_CHIEF(0xEAE9),
	S_GUILD_HISTORY(0x7ABD),
	S_UNION_CHANGE(0x8766),
	S_UNION_MY_UNION(0xF50C),
	S_VIEW_UNION_INFO(0x5F68),
	S_UNION_CHANGE_TAXRATE(0x5D1E),
	S_UNION_CHANGE_NOTICE(0x6644),
	S_DIALOG_EVENT(0xBAD3),
	S_STR_EVALUATE_LIST(0xD5D8),
	S_INVEN_CHANGEDSLOT(0xCE12),
	S_CREATURE_LIFE(0xA66D),
	S_SHOW_DEAD_UI(0xC920),
	S_HIDE_HP(0x62A4),
	S_USER_PAPERDOLL_INFO(0x5089),
	S_USER_LEVELUP(0x5327),
	S_USER_EXTERNAL_CHANGE(0xFB89),
	S_CREST_INFO(0x866B),
	S_UNEQUIP_ITEM(0xFE70),
	S_EQUIP_ITEM(0xE3CF),
	S_DESPAWN_DROPITEM(0xF4AD),
	S_SYSTEM_MESSAGE_LOOT_ITEM(0xE423),
	S_SHOW_PEGASUS_MAP(0xF1A1),
	S_START_PEGASUS(0x98D5),
	S_SYNC_PEGASUS_TIME(0xBFA4),
	S_END_PEGASUS(0x6C50),





	PLAYER_CREATE_RESULT(0x543E), // 01
	PLAYER_NAME_RESULT(0xB495), // 01
	 // 28 00 0C 8D 01 00 08 00 08 00 00 00 16 00 01 00

	PLAYER_BANK(0xB2E4), // 06 00 1F 00
	FF_STRUCTURE(0x88F4), // 05 00 16 00 01 00 FFFFFF
	TEST_4(0x7C0A),
	TEST_5(0x804F), // 00 00 00 00 00 00 00 00
	TEST_6(0xC782), // 00 00 48 43
	TEST_7(0xFD25), // 06 00 00 00
	HOT_KEY(0xA484), // пока не настроено
	HOT_KEY_CHANGED(0xB023), // пока не настроено

	GUILD_CHECK_NAME(0x8D0C),
	GUILD_INPUT_NAME(0xE224), // 10 00 82 93 61 29 AF 03 00 80 00 01 AA 05 00 00
	GUILD_LOAD_ICON(0x648F),
	GUILD_ICON_INFO(0xB229),
	GUILD_BANK(0xB2E4),


	TEST_29(0xB917), // 02 00 08 00 08 00 18 00 01 00 00 00
	TEST_24(0x6DDF), // 00 00 00 00
	TEST_25(0x7C1A), // 00 00 00 00
	TEST_26(0x6874), // 00 00 00 00
	TEST_30(0x9131), // 00 00 00 00 00 00 00 00
	TEST_27(0x8118), // 00 00 00 00 00 00 00 00
	TEST_35(0x99EC), // 15 00 02 88-FF FF FF 7F **
	USER_INFO(0x7FAB), // C7 00 C2 06 00 00 - 78 00 00 00
	TEST_32(0x6B95), // ID 00

	REQUEST_CHECKING_SERVER(0xB542), // 08 00 67 BB 01 00 00 00
	CHECK_SERVER_RESULT(0x9EE0),

	QUEST_STARTED(0xF039),
	QUEST_MOVE_TO_PANEL(0x802F),
	QUEST_REMOVE_TO_PANEL(0x741F),
	QUEST_NPC_NOTICE(0xCC67),
	QUEST_COMPLETE_LIST(0xAA83),
	QUEST_SPLIT_LIST(0xB33F),
	QUEST_UPDATE_COUNTER(0x8FBB),
	QUEST_VIDEO(0x9DE0),
	QUEST_INFO(0x7236),

	EVENT_MESSAGE(0x653D),

	CHAR_CLIMB(0x86B6),
	CHAR_BATTLE_STATE(0x6B95), // 11 00 95 6B 0D 60 0C 00 00 80 00 01 01 00 00 00
	CHAR_TURN(0xB726), // 12 00 26 B7 94 DD 0A 00 00 80 0B 00 3A 81 A9 07 поворот

	PLAYER_PVP_ON(0xBF3C),
	PLAYER_PVP_OFF(0xD224),
	PLAYER_MOVE(0x0A44),

	PLAYER_CURRENT_MP(0xB053), // 24 00 53 B0 02 0B 00 00 7F 0C 00 00 20 00 00 00
	PLAYER_INCREASE_LEVE(0xD7DE), // 10 00 DE D7 0D 60 0C 00 00 80 00 01 02 00 00 00

	MOUNT_ON(0xCDD6),
	MOUNT_OFF(0x7E2D),

	CHAR_STATE(0xC41E), // 1A 00 73 F2 96 07 B7 03 00 80 00 01 B0 E3 A6 47
	PLAYER_LORD_NUM(0xCD5F), // 10 00 5F CD 06 00 40 00 36 00 30 00 31 00 00 00

	EMOTION(0xEE87), // 15 00 18 8A E1 11 B7 03 00 80 00 01 26 00 00 00
	REACTION(0xC143),
	DAMAGE(0xBB0E), // 53 00 d(0) 48 0E B2 03 00 80 00 02 DD 2E 02 00 00 80 0B 00
	TARGET_CURRENT_HP(0x1111), // 28 00 DD 2E 02 00 00 80 0B 00 28 AF 21 3E 00 00 00 00 01
	TARGET_NPC_HP(0xE963), // 21 00 63 E9 69 F2 0A 00 00 80 0B 00 99 42 4B 3E над бошкой
	RESIST_EFFECT(0x985B),

	SUMMON_INFO(0x6B57),

	CHARM_SMOKE(0xA238),

	NPC_PLAYABLE_INFO(0x6B57),
	NPC_SPEAK(0xD914), // 200 20 00 D5 B8 BB 52 00 00 00 80 0B 00 CD 08 B5 03
	NPC_NOTICE(0xA1C3), // 1D 00 C3 A1 D0 9A 04 00 00 80 0B 00 00 05 00 00

	NPC_BANK(0x0),
	NPC_BANK_PANEL(0xD521),
	NPC_DIALOG_REPLY_SHOP(0xB205), // 01 00 3D 00
	NPC_DIALOG_SKILL_LEARN(0xB494), // 01 00 3D 00
	NPC_DIALOG_SKILL_LEARN_PANEL(0x7880), // 3A 00 54 79 2C 00 38 00 3A
	ITEM_SHOP_TRADE(0x7CEF), // 01 00 3D 00

	MULTI_SHOP(0x6C91),
	OBJECT_POSITION(0xE6C7),
	SKILL_START(0x660C), // 32 00 FC A3 00 00 00 00 54 5A AA 03 00 80 00 00
	CHARGE_SKILL_START(0x988F), // 32 00 FC A3 00 00 00 00 54 5A AA 03 00 80 00 00
	SKILL_MOVE(0xA7B7), // 2A 00 C9 C3 54 5A AA 03 00 80 00 00 00 9A B6 47
	SKILL_END(0xCAC4), // 2A 00 C9 C3 54 5A AA 03 00 80 00 00 00 9A B6 47
	CANCEL_OWERTURN(0xCAC4), // 2A 00 C9 C3 54 5A AA 03 00 80 00 00 00 9A B6 47
	SKILL_REUSE(0x8A0B), // 0C 00 20 90 C4 EA 00 04 58 1B 00 00
	SKILL_FAST_SHOT(0xC372), // 30 00 4F C9 00 00 00 00 01 00 20 00 54 5A AA 03
	SKILL_SLOW_SHOT(0xE8BB), // 3C 00 61 77 DD 31 AF 03 00 80 00 01 63 2B 00 00
	SKILL_NPC_SLOW_SHOT(0xB0D6),
	SKILL_DELETE_SHOT(0xC7BB),
	SKILL_LEASH(0xC2F6),
	SKILL_LOCK_ATTACK(0xA1C1),
	SKILL_LOCK_TARGET(0xCCE8), // 0D 00 EA 5E DD 31 AF 03 02 80 00 01 01
	SHIELD_BLOCK(0xDAFE), // 14 00 FE DA 69 60 0C 00 00 80 00 01 4F 04 0D 44
	APPLED_BUFF_PACKET(0x5665), // 20 00 33 92 61 29 AF 03 00 80 00 01 61 29
	APPLED_CHARM_PACKET(0xBB75),
	CANCELED_EFFECT_PACKET(0x5A44), // 10 00 82 93 61 29 AF 03 00 80 00 01 AA 05 00 00
	CANCEL_CHARM_EFFECT(0x9DE4),

	TRAP_INFO(0x9F1F),
	TRAP_DELETE(0x6772),

	CHAR_PICK_UP_ITEM(0xA66C), // 15 00 6C A6 0D 60 0C 00 00 80 00 01 6B BD 0E 00
	ITEM_INFO(0xF6D4),
	ITEM_TEMPLATE_INFO(0xDF5D),
	ITEM_REUSE(0xE7C5), // 0C 00 0C A0 7D 00 00 00 14 00 00 00
	PLAYER_EQUIPMENT(0xD3B9), // 40 00 45 F1 8B 06 B0 03 00 80 00 02 11 27 00 00
	PLAYER_INVENTORY_ITEM_INFO(0xDF5D), // 2F 01 5D DF 00 00 00 00


	PLAYER_INCREASE_LEVEL(0xD7DE),

	PLAYER_ACTION_INVITE(0xC8F1), // 36 00 F1 C8 16 00 26 00 10 00 04 00 00 00 3C 8F
	ACTION_START(0xA924), // 08 00 24 A9 04 00 00 00
	PLAYER_WAITING_ACTION(0x7880), // 54 00 80 78 2C 00 3A 00 3C 00 18 00 3A 54 0D 00
	OPEN_TRADE(0xB549), // 9C 00 49 B5 38 00 32 00 6A 00 32 00 C6 52 0D 00
	CLOSE_TRADE(0xA2F2), // 18 00 F2 A2 BD 07 00 10 00 80 00 13
	DUEL_START(0x72AD), // 06 AD 72 88 13
	CANCEL_TARGET_HP(0xF470), // 70 F4 D2 07 00 10 00 80 00 13

	TP1(0x8293), // 10 00 FC 5D 96 07 B7 03 00 80 00 01 78 41 0F 00
	FLY_PEGAS_REPLY_PACKET(0xd9c2),
	DEPARTURE_PORTAL(0x1111), // 18 00 AD B9 4F 6E 0D 00 00 80 00 04 08 00 00 00
	STATE_ALLOWED(0x71C3),

	TELEPORT_POINTS(0xA5F7),

	PLAYER_ACTION_START(0xBEB7), // 2C 00 36 00 42 00 00
	DUEL_END(0x2C88), // 32 17 B2 03 00 80 00 04 11 1D B2 03
	DUEL_END_OK(0xCC79), // 00
	DUEL_END_OK2(0x63BB), // 06 00
	RIVALS(0xE58B), // 14 00 32 17 B2 03 00 80 00 04 и 2го ИД

	CONNECT_SUCCESSFUL(0xFFFE),
	SERVER_KEY(0xFFFF),
	DEVELOPER_PACKET(0xFFFD),

	UPDATE_STAMINA(0xA885),

	SYSTEM_MESSAGE(0xE19F),

	ENCHANT_ITEM_DIALOG_INFO(0xFCEC),
	ENCHANT_RESULT(0xC06B), ;

	public static final int LENGTH = values().length;

	public static final void init()
	{
		ITEM_TEMPLATE_INFO.set(PLAYER_INVENTORY_ITEM_INFO);
		QUEST_INFO.set(S_DIALOG);
		CANCEL_OWERTURN.set(SKILL_END);
		SUMMON_INFO.set(S_SPAWN_NPC);
		GUILD_CHECK_NAME.set(S_STR_EVALUATE_LIST);
		FLY_PEGAS_REPLY_PACKET.set(S_REQUEST_CONTRACT);
		DEPARTURE_PORTAL.set(S_SYNC_PEGASUS_TIME);
		GUILD_BANK.set(PLAYER_BANK);
		EVENT_MESSAGE.set(SYSTEM_MESSAGE);
		SKILL_NPC_SLOW_SHOT.set(TRAP_INFO);
		NPC_PLAYABLE_INFO.set(S_SPAWN_USER);
	}

	/** опкод пакета */
	private int opcode;

	/**
	 * @param opcode опкод пакета.
	 */
	private ServerPacketType(int opcode)
	{
		this.opcode = opcode;
	}

	/**
	 * @return опкод пакета.
	 */
	public int getOpcode()
	{
		return opcode;
	}

	/**
	 * @param opcode опкод пакета.
	 */
	public void setOpcode(int opcode)
	{
		this.opcode = opcode;
	}

	/**
	 * @param type опкод указанного типа пакета.
	 */
	public void set(ServerPacketType type)
	{
		this.opcode = type.opcode;
	}
}
