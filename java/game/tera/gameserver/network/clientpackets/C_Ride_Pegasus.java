package tera.gameserver.network.clientpackets;

import tera.gameserver.model.npc.interaction.dialogs.Dialog;
import tera.gameserver.model.npc.interaction.dialogs.DialogType;
import tera.gameserver.model.npc.interaction.dialogs.PegasDialog;
import tera.gameserver.model.playable.Player;

/**
 * Начало полета на пегасе по выбранному маршруту.
 *
 * @author Ronn
 * @created 26.02.2012
 */
public class C_Ride_Pegasus extends ClientPacket
{
	/** игрок */
	private Player player;

	/** номер маршрута */
	private int index;

	@Override
	public void finalyze()
	{
		player  = null;
	}

	@Override
	public boolean isSynchronized()
	{
		return false;
	}

	@Override
	protected void readImpl()
	{
		player = owner.getOwner();

		index = readInt();//08 00 00 00 покакому маршруту
	}

	@Override
	protected void runImpl()
	{
		if(player == null)
			return;

		Dialog dialog = player.getLastDialog();

		if(dialog == null || dialog.getType() != DialogType.PEGAS)
			return;

		PegasDialog window = (PegasDialog) dialog;

		if(window.fly(index))
			window.apply();
		else
			window.close();
	}
}
