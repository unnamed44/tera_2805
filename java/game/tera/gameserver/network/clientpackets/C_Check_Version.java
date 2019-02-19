package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.AccountManager;
import tera.gameserver.network.serverpackets.S_Check_Version;

/**
 * Клиентский пакет для авторизации на сервере
 *
 * @author Ronn
 * @created 24.03.2012
 */
public class C_Check_Version extends ClientPacket
{
	/** логин */
	private int code1;
	/** парль */
	private int code2;

	private static final int server_code1 = 343341;
	private static final int server_code2 = 265949;

	@Override
	public void finalyze()
	{
		code1 = -1;
		code2 = -1;
	}

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	public void readImpl()
	{
		readLong();
		readInt();
		code1 = readInt();
		readLong();
		code2 = readInt();
    }

	@Override
	public void runImpl()
	{
		getOwner().sendPacket(S_Check_Version.getInstance(S_Check_Version.SUCCESSFUL));
	}
}