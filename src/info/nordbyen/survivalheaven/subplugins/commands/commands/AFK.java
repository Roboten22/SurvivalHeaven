/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <alexmsagen@gmail.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.   Alexander Sagen
 * ----------------------------------------------------------------------------
 */
package info.nordbyen.survivalheaven.subplugins.commands.commands;

import info.nordbyen.survivalheaven.SH;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
<<<<<<< HEAD
import org.bukkit.event.player.PlayerChatEvent;
=======
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * The Class AFK.
 */
<<<<<<< HEAD
@SuppressWarnings("deprecation")
public class AFK implements CommandExecutor
{
=======
public class AFK implements CommandExecutor {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f

	/**
	 * The listener interface for receiving AFK events. The class that is
	 * interested in processing a AFK event implements this interface, and the
	 * object created with that class is registered with a component using the
	 * component's <code>addAFKListener<code> method. When
	 * the AFK event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see AFKEvent
	 */
<<<<<<< HEAD
	public class AFKListener implements Listener
	{

=======
	public class AFKListener implements Listener {
		
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
		/**
		 * On move.
		 *
		 * @param e
		 *            the e
		 */
		@EventHandler
<<<<<<< HEAD
		public void onMove(PlayerMoveEvent e)
		{
			Location from = e.getFrom();
			Location to = e.getTo();
			if (from == to)
				return;
			hashmap.remove(e.getPlayer().getName());
		}

		/**
		 * On chat.
		 * 
		 * @param e
		 *            the event
		 */
		@EventHandler
		public void onChat(PlayerChatEvent e)
		{
			if (e.getMessage() == null)
=======
		public void onMove(PlayerMoveEvent e) {
			Location from = e.getFrom();
			Location to = e.getTo();
			if (from.getX() == to.getX())
				return;
			if (from.getY() == to.getY())
				return;
			if (from.getZ() == to.getZ())
				return;
			if (from.getWorld().getName().equals(to.getWorld().getName()))
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				return;
			hashmap.remove(e.getPlayer().getName());
		}
	}

	/** The hashmap. */
	public static List<String> hashmap = new ArrayList<String>();

	/**
	 * Instantiates a new afk.
	 */
<<<<<<< HEAD
	public AFK()
	{
=======
	public AFK() {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
		Bukkit.getPluginManager().registerEvents(new AFKListener(),
				SH.getPlugin());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender
	 * , org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd,
<<<<<<< HEAD
			final String label, final String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "Du må være en spiller.");
			return true;
		}
		if ((sender instanceof Player))
		{
			final String player = sender.getName();
			if (!hashmap.contains(player))
			{
=======
			final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Du må være en spiller.");
			return true;
		}
		if ((sender instanceof Player)) {
			final String player = sender.getName();
			if (!hashmap.contains(player)) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				hashmap.add(player);
				Bukkit.broadcastMessage(ChatColor.DARK_RED + player
						+ ChatColor.RED + " er nå AFK.");
				return true;
<<<<<<< HEAD
			} else
			{
=======
			} else {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				hashmap.remove(player);
				Bukkit.broadcastMessage(ChatColor.DARK_GREEN + player
						+ ChatColor.GREEN + " er ikke lenger AFK.");
				return true;
			}
		}
		return false;
	}
}
