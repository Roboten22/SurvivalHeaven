package info.nordbyen.survivalheaven.subplugins.commands.commands;

import info.nordbyen.survivalheaven.SH;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Mute implements CommandExecutor {

	
	public SH pl;
	
	public Mute(SH plugin){
		pl=plugin;
	}

	public boolean onCommand(CommandSender Sender, Command command,
			String commandLabel, String args[]) {

		if (Bukkit.getPlayer(args[0]) == null) {
			return false;
		}
		if (Sender.hasPermission("sh.kick")) {
			if (args.length > 0) {
				if (args.length > 1) {
					if (Sender.hasPermission("sh.kick")) {
						if (!pl.mutedPlayers.contains(args[0])) {
							pl.mutedPlayers.add(args[0]);

							StringBuffer me = new StringBuffer();
							for (int i = 1; i < args.length; i++) {
								me.append(args[i] + " ");
							}
							Bukkit.getPlayer(args[0]).sendMessage(
									ChatColor.RED + "Du ble mutet av "
											+ Sender.getName() + ". Grunn: "
											+ me);
						} else {
							Sender.sendMessage(ChatColor.RED
									+ "Denne spilleren er allerede mutet. Fjern grunnen å prøv igjen");
						}
					}
				} else {
					if (!pl.mutedPlayers.contains(args[0])) {
						pl.mutedPlayers.add(args[0]);
						Bukkit.getPlayer(args[0]).sendMessage(
								ChatColor.RED + "Du ble mutet av "
										+ Sender.getName() + ".");
						Sender.sendMessage(ChatColor.GREEN + "Du mutet "
								+ args[0]);

					} else {
						pl.mutedPlayers.remove(args[0]);
						Bukkit.getPlayer(args[0]).sendMessage(
								ChatColor.GREEN + "Du ble un-mutet av "
										+ Sender.getName() + ".");
						Sender.sendMessage(ChatColor.GREEN + "Du un-mutet "
								+ args[0]);
					}
				}
			}
		}
		return false;

	}

}
