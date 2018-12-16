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
	private String accountName;
	/** парль */
	private String password;

	@Override
	public void finalyze()
	{
		accountName = null;
		password = null;
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
		readLong();
		readLong();
		readLong();
		readLong();
		readInt();
		readShort();
		accountName = readS();
		readByte();
		password = readPassword();
    }

	@Override
	public void runImpl()
	{
		getOwner().sendPacket(S_Check_Version.getInstance(S_Check_Version.SUCCESSFUL));
	}
}