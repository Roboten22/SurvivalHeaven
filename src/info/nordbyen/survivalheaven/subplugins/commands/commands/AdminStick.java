package info.nordbyen.survivalheaven.subplugins.commands.commands;

import info.nordbyen.survivalheaven.SH;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AdminStick implements CommandExecutor{

	public AdminStickWand asw = null;
	
	/** The hashmap. */
	public static List<String> hashmap = new ArrayList<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender
	 * , org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd,
			final String label, final String[] args) {
		if( asw == null ) asw = new AdminStickWand(); 
		
		if( !(sender instanceof Player) ) {
			sender.sendMessage(ChatColor.RED + "Bare spillere kan gj�re dette!");
		}
		Player p = ( Player )sender;
		if( p.hasPermission("sh.adminstick") ) {
			ItemStack item = new ItemStack( Material.STICK, 1 );
			SH.getManager().getWandManager().createWand( item, asw, p);
		}
		return true;
	}
}
