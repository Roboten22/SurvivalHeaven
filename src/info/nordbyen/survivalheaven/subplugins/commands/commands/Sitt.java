/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <alexmsagen@gmail.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.   Alexander Sagen
 * ----------------------------------------------------------------------------
 */
package info.nordbyen.survivalheaven.subplugins.commands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The Class Sitt.
 */
public class Sitt implements CommandExecutor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender
	 * , org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(final CommandSender Sender, final Command command,
			final String commandLabel, final String args[]) {
		if (Sender instanceof Player) {
			if (command.getName().equalsIgnoreCase("sitt")) {
				if (Sender.hasPermission("sh.kick")) {
					if (args.length == 1) {
						final Player p = Bukkit.getPlayer(args[0]);
<<<<<<< HEAD
=======
						@SuppressWarnings("unused")
						final Player tg = (Player) Sender;
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
						p.setPassenger(p);
					}
				}
			}
		}
		return false;
	}
}
