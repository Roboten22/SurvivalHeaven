/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <alexmsagen@gmail.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.   Alexander Sagen
 * ----------------------------------------------------------------------------
 */
package info.nordbyen.survivalheaven.subplugins.homes;

import info.nordbyen.survivalheaven.SH;
import info.nordbyen.survivalheaven.api.playerdata.IPlayerData;

import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The Class HomeManagerCommand.
 */
<<<<<<< HEAD
public class HomeManagerCommand implements CommandExecutor
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender
	 * , org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			sender.sendMessage(ChatColor.RED + "Bare spillere kan ha hjem!");
=======
public class HomeManagerCommand implements CommandExecutor {

	/* (non-Javadoc)
	 * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Bare spillere kan ha kjem!");
			;
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
			return true;
		}
		Player p = (Player) sender;
		HomeManager hm = SH.getManager().getHomeManager();
		IPlayerData pd = SH.getManager().getPlayerDataManager()
				.getPlayerData(p.getUniqueId().toString());
<<<<<<< HEAD
		if (command.getName().equalsIgnoreCase("home"))
		{
			if (args.length != 1)
			{
=======
		if (command.getName().equalsIgnoreCase("home")) {
			if (args.length != 1) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED + "Feil syntax: /home <navn>");
				return true;
			}
			Home home = hm.getHomeFromPlayer(args[0], pd.getUUID());
<<<<<<< HEAD
			if (home == null)
			{
=======
			if (home == null) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED + "Fant ikke hjemmet " + args[0]);
				return true;
			}
			p.teleport(home.getLocation());
			p.sendMessage(ChatColor.GREEN + "Du ble teleportert til hjemmet "
					+ home.getName());
			return true;
<<<<<<< HEAD
		} else if (command.getName().equalsIgnoreCase("sethome"))
		{
			if (args.length != 1)
			{
=======
		} else if (command.getName().equalsIgnoreCase("sethome")) {
			if (args.length != 1) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED + "Feil syntax: /sethome <navn>");
				return true;
			}
			String name = args[0];
			Home home = new Home(name, pd.getUUID(), p.getLocation());
			boolean done = false;
<<<<<<< HEAD
			try
			{
				done = hm.addHome(home);
			} catch (SQLException e)
			{
				e.printStackTrace();
				p.sendMessage(ChatColor.RED
						+ "En feil skjedde. Si ifra til stab!");
				return true;
			}
			if (!done)
			{
=======
			try {
				done = hm.addHome(home);
			} catch (SQLException e) {
				e.printStackTrace();
				p.sendMessage(ChatColor.RED
						+ "En feil sjedde. Si ifra til stab!");
				return true;
			}
			if (!done) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED
						+ "Du har allerede et hjem ved navn " + home.getName());
				return true;
			}
			p.sendMessage(ChatColor.GREEN + "Laget hjemmet " + home.getName()
					+ " i " + home.getLocation().getWorld().getName() + " "
					+ home.getLocation().getBlockX() + " "
					+ home.getLocation().getBlockY() + " "
					+ home.getLocation().getBlockZ());
			return true;
<<<<<<< HEAD
		} else if (command.getName().equalsIgnoreCase("homes"))
		{
			if (args.length != 0)
			{
=======
		} else if (command.getName().equalsIgnoreCase("homes")) {
			if (args.length != 0) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED + "Feil syntax: /homes");
				return true;
			}
			ArrayList<Home> homes = hm.getHomesFromPlayer(p.getUniqueId()
					.toString());
<<<<<<< HEAD
			if (homes == null || homes.size() == 0)
			{
=======
			if (homes == null || homes.size() == 0) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED
						+ "Du har ingen hjem, men kan lage et hjem med /sethome <navn>");
				return true;
			}
			StringBuilder sb = new StringBuilder(ChatColor.GREEN
					+ "Du har følgende hjem:\n" + ChatColor.AQUA);
<<<<<<< HEAD
			for (Home home : homes)
			{
=======
			for (Home home : homes) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				sb.append(home.getName() + ", ");
			}
			p.sendMessage(sb.substring(0, sb.length() - 2));
			return true;
<<<<<<< HEAD
		} else if (command.getName().equalsIgnoreCase("delhome"))
		{
			if (args.length != 1)
			{
=======
		} else if (command.getName().equalsIgnoreCase("delhome")) {
			if (args.length != 1) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED + "Feil syntax: /delhome <navn>");
				return true;
			}
			String name = args[0];
			ArrayList<Home> homes = hm.getHomesFromPlayer(p.getUniqueId()
					.toString());
<<<<<<< HEAD
			if (homes == null || homes.size() == 0)
			{
=======
			if (homes == null || homes.size() == 0) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				p.sendMessage(ChatColor.RED + "Du har ingen hjem å slette");
				return true;
			}
			boolean hasHome = false;
<<<<<<< HEAD
			for (Home home : homes)
			{
				if (home.getName().equalsIgnoreCase(name))
				{
					hasHome = true;
				}
			}
			if (!hasHome)
			{
				p.sendMessage(ChatColor.RED + "Finner ikke hjemmet " + name);
				return true;
			}
			try
			{
				hm.deleteHomeFromPlayer(name, p.getUniqueId().toString());
			} catch (SQLException e)
			{
=======
			for (Home home : homes) {
				if (home.getName().equalsIgnoreCase(name)) {
					hasHome = true;
				}
			}
			if (!hasHome) {
				p.sendMessage(ChatColor.RED + "Finner ikke hjemmet " + name);
				return true;
			}
			try {
				hm.deleteHomeFromPlayer(name, p.getUniqueId().toString());
			} catch (SQLException e) {
>>>>>>> 1d5076231d2c0b13f688aeea7169c973da216e6f
				e.printStackTrace();
				p.sendMessage(ChatColor.RED
						+ "Noe galt skjedde. Si ifra til staben!");
				return true;
			}
			p.sendMessage(ChatColor.GREEN + "Du slettet hjemmet " + args[0]);
			return true;
		}
		return true;
	}

}
