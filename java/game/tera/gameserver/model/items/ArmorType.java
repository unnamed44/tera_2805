package tera.gameserver.model.items;

import tera.gameserver.model.equipment.SlotType;

/**
 * Перечисление типов брони.
 * 
 * @author Ronn
 */
public enum ArmorType
{
	/** боты */
	BOOTS("boots", SlotType.SLOT_BOOTS),
	/** перчатки */
	GLOVES("gloves", SlotType.SLOT_GLOVES),
	/** майка */
	SHIRT("shirt", SlotType.SLOT_SHIRT),
	/** тело */
	BODY("body", SlotType.SLOT_ARMOR),
	/** серьга */
	EARRING("earring", SlotType.SLOT_EARRING),
	/** кольцо */
	RING("ring", SlotType.SLOT_RING),
	/** ожерелье */
	NECKLACE("necklace", SlotType.SLOT_NECKLACE),
	/** маска */
	MASK("mask", SlotType.SLOT_MASK),
	/** шапочке */
	HAT("hat", SlotType.SLOT_HAT),
	/** ремодел брони */
	REMODEL("remodel", SlotType.SLOT_ARMOR_REMODEL),

	BELT("belt", SlotType.SLOT_BELT),
	BROOCH("brooch", SlotType.SLOT_BROOCH),
	COSTUME_HEAD("costume_head", SlotType.SLOT_COSTUME_HEAD),
	COSTUME_FACE("costume_face", SlotType.SLOT_COSTUME_FACE),
	COSTUME_WEAPON("costume_weapon", SlotType.SLOT_COSTUME_WEAPON),
	COSTUME_BODY("costume_body", SlotType.SLOT_COSTUME_BODY),
	COSTUME_BACK("costume_back", SlotType.SLOT_COSTUME_BACK);

	/**
	 * Получение нужного армор типа по названию в xml.
	 * 
	 * @param name название в xml.
	 * @return соответствующий армор тип.
	 */
	public static ArmorType valueOfXml(String name)
	{
		for(ArmorType type : values())
			if(type.getXmlName().equals(name))
				return type;
		
		throw new IllegalArgumentException();
	}
	/** название типа в xml */
	private String xmlName;
	
	/** слот, куда одевается данный тип брони */
	private SlotType slot;

	/**
	 * @param xmlName название в хмл.
	 * @param slot одеваемый слот.
	 */
	private ArmorType(String xmlName, SlotType slot)
	{
		this.xmlName = xmlName;
		this.slot = slot;
	}
	
	/**
	 * @return слот, в который одевается броня.
	 */
	public final SlotType getSlot()
	{
		return slot;
	}

	/**
	 * @return xml название.
	 */
	public final String getXmlName()
	{
		return xmlName;
	}
}
