/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <alexmsagen@gmail.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.   Alexander Sagen
 * ----------------------------------------------------------------------------
 */
package info.nordbyen.survivalheaven.subplugins.commands.commands;

<<<<<<< HEAD
=======
import org.bukkit.ChatColor;
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The Class WB.
 */
<<<<<<< HEAD
public class WB implements CommandExecutor
{
=======
public class WB implements CommandExecutor {
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
			final String CommandLabel, final String[] args)
	{
		if (Sender instanceof Player)
		{
			final Player p = (Player) Sender;
			if (p.hasPermission("sh.wb"))
			{
				if (command.getName().equalsIgnoreCase("wb"))
				{
					p.openWorkbench(p.getLocation(), true);
=======
			final String CommandLabel, final String[] args) {
		if (Sender instanceof Player) {
			final Player p = (Player) Sender;
			if (p.hasPermission("sh.wb")) {
				if (command.getName().equalsIgnoreCase("wb")) {
					if (args.length == 0) {
						p.openWorkbench(p.getLocation(), true);
					} else {
						p.sendMessage(ChatColor.RED + "/wb");
					}
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				}
			}
		}
		return true;
	}
}
