package tera.gameserver.network;

/**
 * @author Ronn
 */
public enum ServerPacketType
{
	S_CHECK_VERSION(0x4DBD), // постоянка
	S_AUTH_FAILED(0xD5A2),
	S_AUTH_SUCCESSFUL(0xD121), // 00 00 00 00 AD 68 06 00
	S_LOADING_SCREEN_CONTROL_INFO(0x640E), // 01 00 02 00
	S_REMAIN_PLAY_TIME(0xF111),
	S_LOGIN_ARBITER(0xB388),
	S_GET_USER_LIST(0x57E5),
	S_CAN_CREATE_USER(0xB3DC),
	S_CHECK_USERNAME(0xC7B8),
	S_CREATE_USER(0x7D01),
	S_DELETE_PLAYER(0x7B13), // 01
	S_SELECT_USER(0xF0B9), // 01 00 00 00 00 00 00 00
	S_CURRENT_ELECTION_STATE(0xEb32),
	S_LOGIN(0xBC74), // AF 00 B9 00
	S_INVEN(0x522F), // 06 00 1F 00
	S_SKILL_LIST(0xF25A), // 06 00 08 00
	S_AVAILABLE_SOCIAL_LIST(0x65B5),
	S_CLEAR_QUEST_INFO(0xA7C0),
	S_ARTISAN_SKILL_LIST(0x94B6), // 06 00 19 00 00 00 00 00
	S_ARTISAN_RECIPE_LIST(0xC1A6),
	S_NPC_GUILD_LIST(0x60D8),
	S_PET_INCUBATOR_INFO_CHANGE(0x5697),
	S_PET_INFO_CLEAR(0xECE7),
	S_VIRTUAL_LATENCY( 0x524C),
	S_MOVE_DISTANCE_DELTA(0xD903),
	S_MY_DESCRIPTION(0xFEFA),
	S_MASSTIGE_STATUS(0xEC23),
	S_FESTIVAL_LIST(0x5119),
	S_LOAD_TOPO(0x713A), // 0D 00 00 00
	S_LOAD_HINT(0xF9E0), // 00 00 00 00 ^
	S_ACCOUNT_BENEFIT_LIST(0x96E3),
	S_SPAWN_ME(0xBA9B), // 0F 13 B5 03 00 80 00 0B
	S_PLAYER_STAT_UPDATE(0xC882),
	S_CHAT(0xF4ED), // <.F.O.N.T.>
	S_SPAWN_NPC(0xF278), // 69 00 6E AB 00 00 00 80 0B 00
	S_QA_SET_ADMIN_LEVEL(0x6C03),
	S_RETURN_TO_LOBBY(0x7DA6),
	S_CANCEL_RETURN_TO_LOBBY(0xF075),
	S_EXIT(0xE845), // 0A 00 00 00
	S_CANCEL_EXIT(0xA342),
	S_USER_LOCATION(0xED01), // движение любого обьекта
	S_TRADE_BROKER_HIGHEST_ITEM_LEVEL(0x9256),
	S_UNION_SUMMARY(0xC625),
	S_GUARD_PK_POLICY(0xE915),
	S_REIGN_INFO(0xF1E3),






	PLAYER_CREATE_RESULT(0x543E), // 01
	PLAYER_NAME_RESULT(0xB495), // 01
	RESULT_CHECK_NAME(0x8D0C), // 28 00 0C 8D 01 00 08 00 08 00 00 00 16 00 01 00

	PLAYER_BANK(0xB2E4), // 06 00 1F 00
	FF_STRUCTURE(0x88F4), // 05 00 16 00 01 00 FFFFFF
	TEST_4(0x7C0A),
	TEST_5(0x804F), // 00 00 00 00 00 00 00 00
	TEST_6(0xC782), // 00 00 48 43
	TEST_7(0xFD25), // 06 00 00 00
	SETTINGS(0x5333), // пока не настроено
	HOT_KEY(0xA484), // пока не настроено
	HOT_KEY_CHANGED(0xB023), // пока не настроено

	GUILD_CHECK_NAME(0x8D0C),
	GUILD_MEMBERS(0xBEA6), // 06 00 08 00
	GUILD_INFO(0xE49A), // 06 00 08 00
	GUILD_INPUT_NAME(0xE224), // 10 00 82 93 61 29 AF 03 00 80 00 01 AA 05 00 00
	GUILD_LOGS(0x942E),
	GUILD_LOAD_ICON(0x648F),
	GUILD_ICON_INFO(0xB229),
	GUILD_BANK(0xB2E4),

	FRIEND_LIST_INFO(0xD342),
	FRIEND_LIST_STATE(0xB6EF),

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
	DELETE_OBJECT(0xDD91), // 20 00 63 D7 2D 98 00 00 00 80 0B 00 57 54 B7 47
	CHAR_BATTLE_STATE(0x6B95), // 11 00 95 6B 0D 60 0C 00 00 80 00 01 01 00 00 00
	CHAR_TURN(0xB726), // 12 00 26 B7 94 DD 0A 00 00 80 0B 00 3A 81 A9 07 поворот

	PLAYER_INFO(0xBA9A), // 93 00 9F 00 A1 00 A3 00 О др игроке
	PLAYER_PVP_ON(0xBF3C),
	PLAYER_PVP_OFF(0xD224),
	PLAYER_MOVE(0x0A44),

	PLAYER_CURRENT_HP(0xF75F), // 24 00 5А 15 7F 0C 00 00 7F 0C 00 00 20 00 00 00
	PLAYER_CURRENT_MP(0xB053), // 24 00 53 B0 02 0B 00 00 7F 0C 00 00 20 00 00 00
	PLAYER_EXP(0xDBEE), // 44 00 5F 9D 83 00 00 00 00 00 00 00 48 01 00 00
	PLAYER_INCREASE_LEVE(0xD7DE), // 10 00 DE D7 0D 60 0C 00 00 80 00 01 02 00 00 00

	MOUNT_ON(0xCDD6),
	MOUNT_OFF(0x7E2D),

	CHAR_DEAD(0x7E30),
	CHAR_STATE(0xC41E), // 1A 00 73 F2 96 07 B7 03 00 80 00 01 B0 E3 A6 47
	PLAYER_DEAD_WINDOW(0x7380), // 12 00 80 73 1E 00 00 00 E6 32 00 00 00
	PLAYER_LORD_NUM(0xCD5F), // 10 00 5F CD 06 00 40 00 36 00 30 00 31 00 00 00

	EMOTION(0xEE87), // 15 00 18 8A E1 11 B7 03 00 80 00 01 26 00 00 00
	REACTION(0xC143),
	DAMAGE(0xBB0E), // 53 00 d(0) 48 0E B2 03 00 80 00 02 DD 2E 02 00 00 80 0B 00
	TARGET_CURRENT_HP(0x1111), // 28 00 DD 2E 02 00 00 80 0B 00 28 AF 21 3E 00 00 00 00 01
	TARGET_NPC_HP(0xE963), // 21 00 63 E9 69 F2 0A 00 00 80 0B 00 99 42 4B 3E над бошкой
	NOTIFY_CHARACTER(0xC4DD), // 200 11 00 0E 00 EC A3 1E 00 00 00 E6 32 00 00 00 00

	NPC_STATE(0x985B),

	RESIST_EFFECT(0x985B),

	SUMMON_INFO(0x6B57),

	BONFIRE_INFO(0x867B), // 00 80 0E 00
	BONFIRE_DELETE(0x6CBE), // 0C 6C BE 55 20 00 00 00 80 0E 00 01

	CHARM_SMOKE(0xA238),

	NPC_PLAYABLE_INFO(0x6B57),
	NPC_SPEAK(0xD914), // 200 20 00 D5 B8 BB 52 00 00 00 80 0B 00 CD 08 B5 03
	NPC_REQUEST_INTERACTION_SUCCESS(0xBAE6), // 01 00 00 00-----
	NPC_DIALOG_WINDOW(0x7236), // 01 00 3D 00
	NPC_NOTICE(0xA1C3), // 1D 00 C3 A1 D0 9A 04 00 00 80 0B 00 00 05 00 00

	NPC_BANK(0x0),
	NPC_BANK_PANEL(0xD521),
	NPC_DIALOG_REPLY(0x7236), // 01 00 3D 00
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
	ITEM_DELETE(0x983F), // 0C 00 D9 52 48 D9 0F 00 00 80 09 00
	ITEM_REUSE(0xE7C5), // 0C 00 0C A0 7D 00 00 00 14 00 00 00
	PLAYER_EQUIPMENT(0xD3B9), // 40 00 45 F1 8B 06 B0 03 00 80 00 02 11 27 00 00
	PLAYER_INVENTORY_ITEM_INFO(0xDF5D), // 2F 01 5D DF 00 00 00 00


	PLAYER_INCREASE_LEVEL(0xD7DE),

	PLAYER_ACTION_DONED(0x86D4), // 1C 00 D4 86 3A 54 0D 00 00 80 00 03 00 00 00 00
	PLAYER_ACTION_INVITE(0xC8F1), // 36 00 F1 C8 16 00 26 00 10 00 04 00 00 00 3C 8F
	ACTION_START(0xA924), // 08 00 24 A9 04 00 00 00
	PLAYER_WAITING_ACTION(0x7880), // 54 00 80 78 2C 00 3A 00 3C 00 18 00 3A 54 0D 00
	PARTY_INFO(0xFF77), // 9C 00 77 FF 02 00 30 00 30 00 00 00 00 E5 7F 00
	PARTY_MEMBER_INFO(0xB932), // 33 00 32 B9 0D 00 00 00 5F 78 00 00 78 05 00 00
	PARTY_MEMBER_EFFECT(0xB14F), // 31 00 4F B1 01 00 14 00 01 00 24 00 0D 00 00 00
	PARTY_LEAVE(0x55DA), // 04 00 55 DA
	PARTY_MEMBER_COORDS(0x8954), // 04 00 55 DA
	NAME_COLOR(0x5A29), // 10 00 29 5A 86 5D 0D 00 00 80 00 03 02 00 00 00
	OPEN_TRADE(0xB549), // 9C 00 49 B5 38 00 32 00 6A 00 32 00 C6 52 0D 00
	CLOSE_TRADE(0xA2F2), // 18 00 F2 A2 BD 07 00 10 00 80 00 13
	DUEL_START(0x72AD), // 06 AD 72 88 13
	CANCEL_TARGET_HP(0xF470), // 70 F4 D2 07 00 10 00 80 00 13

	TP1(0x8293), // 10 00 FC 5D 96 07 B7 03 00 80 00 01 78 41 0F 00

	PUT_AN_PEGAS(0x57E9), // 10 00 E9 57 4F 6E 0D 00 00 80 00 04 01 00 00 00
	FLY_PEGAS(0xB9AD), // 18 00 AD B9 4F 6E 0D 00 00 80 00 04 08 00 00 00
	FLY_PEGAS_REPLY_PACKET(0xd9c2),
	DEPARTURE_PORTAL(0x1111), // 18 00 AD B9 4F 6E 0D 00 00 80 00 04 08 00 00 00
	STATE_ALLOWED(0x71C3),
	GET_OFF_PEGAS(0xB63E), // 0C 00 3E B6 4F 6E 0D 00 00 80 00 04
	ROUTE_PEGAS(0xEFA6), // 20 00 A6 EF 01 00 08 00 08 00 00 00 08 00 00 00

	TELEPORT_POINTS(0xA5F7),

	PLAYER_ACTION_START(0xBEB7), // 2C 00 36 00 42 00 00
	DUEL_END(0x2C88), // 32 17 B2 03 00 80 00 04 11 1D B2 03
	DUEL_END_OK(0xCC79), // 00
	DUEL_END_OK2(0x63BB), // 06 00
	RIVALS(0xE58B), // 14 00 32 17 B2 03 00 80 00 04 и 2го ИД

	RESOURSE_INFO(0xAB9D),
	RESOURSE_REMOVE(0xC0D6),
	RESOURSE_START_COLLECT(0xFB4E),
	RESOURSE_END_COLLECT(0x9D3F),
	RESOURSE_INCREASE_LEVEL(0xE49B),
	RESOURSE_COLLECT_PROGRESS(0xA671),

	CONNECT_SUCCESSFUL(0xFFFE),
	SERVER_KEY(0xFFFF),
	DEVELOPER_PACKET(0xFFFD),

	UPDATE_STAMINA(0xA885),

	SYSTEM_MESSAGE(0xE19F),
	MESSAGE_ADD_ITEM(0xB79F),

	ENCHANT_ITEM_DIALOG_INFO(0xFCEC),
	ENCHANT_RESULT(0xC06B), ;

	public static final int LENGTH = values().length;

	public static final void init()
	{
		ITEM_TEMPLATE_INFO.set(PLAYER_INVENTORY_ITEM_INFO);
		QUEST_INFO.set(NPC_DIALOG_WINDOW);
		CANCEL_OWERTURN.set(SKILL_END);
		SUMMON_INFO.set(S_SPAWN_NPC);
		GUILD_CHECK_NAME.set(RESULT_CHECK_NAME);
		FLY_PEGAS_REPLY_PACKET.set(PLAYER_WAITING_ACTION);
		DEPARTURE_PORTAL.set(FLY_PEGAS);
		GUILD_BANK.set(PLAYER_BANK);
		EVENT_MESSAGE.set(SYSTEM_MESSAGE);
		SKILL_NPC_SLOW_SHOT.set(TRAP_INFO);
		NPC_PLAYABLE_INFO.set(PLAYER_INFO);
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
