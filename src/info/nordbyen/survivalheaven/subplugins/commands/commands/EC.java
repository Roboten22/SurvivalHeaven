/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <alexmsagen@gmail.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.   Alexander Sagen
 * ----------------------------------------------------------------------------
 */
package info.nordbyen.survivalheaven.subplugins.commands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The Class EC.
 */
<<<<<<< HEAD
public class EC implements CommandExecutor
{
=======
public class EC implements CommandExecutor {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender
	 * , org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(final CommandSender Sender, final Command command,
<<<<<<< HEAD
			final String commandLabel, final String[] args)
	{
		if (Sender instanceof Player)
		{
			final Player p = (Player) Sender;
			if (p.hasPermission("sh.ec"))
			{
				if (command.getName().equalsIgnoreCase("ec"))
				{
					p.openInventory(p.getEnderChest());
				}
			} else
			{
=======
			final String commandLabel, final String[] args) {
		if (Sender instanceof Player) {
			final Player p = (Player) Sender;
			if (p.hasPermission("sh.ec")) {
				if (command.getName().equalsIgnoreCase("ec")) {
					if (args.length == 0) {
						p.openInventory(p.getEnderChest());
					} else {
						p.sendMessage(ChatColor.RED + "Feil bruk av kommando");
					}
				}
			} else {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED + "Du har ikke permission!");
			}
		}
		return true;
	}
}
