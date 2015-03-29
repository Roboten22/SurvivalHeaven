/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <alexmsagen@gmail.com> wrote this file.  As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return.   Alexander Sagen
 * ----------------------------------------------------------------------------
 */
package info.nordbyen.survivalheaven.subplugins.playerdata;

import info.nordbyen.survivalheaven.SH;
import info.nordbyen.survivalheaven.api.playerdata.IPlayerData;
import info.nordbyen.survivalheaven.api.util.FancyMessages;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * The Class PlayerDatalistener.
 */
@SuppressWarnings("deprecation")
public class PlayerDatalistener implements Listener {

	/**
	 * On join.
	 *
	 * @param e
	 *            the e
	 */
	@EventHandler( priority = EventPriority.LOWEST )
	public void onJoin(final PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		final IPlayerData pd = SH.getManager().getPlayerDataManager()
				.getPlayerData(p.getUniqueId().toString());
		if (pd != null) {
			pd.setLastlogin(new Date());
			pd.setName(p.getName());
			pd.addIp(p.getAddress().toString().replace("/", "").split(":")[0]);
		} else {
			SH.getManager().getPlayerDataManager().createPlayerData(p);
		}
	}
	
	/**
	 * On join.
	 *
	 * @param e
	 *            the e
	 */


	/**
	 * On join2.
	 *
	 * @param e
	 *            the e
	 */
	@EventHandler
	public void onJoin2(final PlayerJoinEvent e) {
		e.setJoinMessage(null);
		if (e.getPlayer().hasPlayedBefore())
			Bukkit.broadcastMessage(ChatColor.GREEN + e.getPlayer().getName()
					+ " logget inn");
		else {
			Bukkit.broadcastMessage(ChatColor.GREEN + e.getPlayer().getName()
					+ " logget inn for første gang!");
			Bukkit.broadcastMessage(ChatColor.BLUE + "Ønsk "
					+ e.getPlayer().getName() + " velkommen");
		}

		FancyMessages.sendActionBar(e.getPlayer(), ChatColor.GREEN + ""
				+ ChatColor.BOLD + "VELKOMMEN TIL " + SH.NAME);
		FancyMessages.sendTitle(e.getPlayer(), 10, 70, 40, ChatColor.GREEN
				+ "Velkommen til " + SH.NAME, SH.MOTTO);
	}
	
	@EventHandler
	public void onChat(final AsyncPlayerChatEvent e){
		if(SH.mutedPlayers.contains(e.getPlayer().getName())){
			FancyMessages.sendActionBar(e.getPlayer(), ChatColor.RED + "Du er mutet og kan ikke snakke");
			e.setCancelled(true);
		}
	}

	/**
	 * On ping.
	 *
	 * @param e
	 *            the e
	 */
	@EventHandler
	public void onPing(final ServerListPingEvent e) {
		e.setMotd(ChatColor.GOLD + "X--===[ " + ChatColor.RED + "Survival"
				+ ChatColor.GRAY + "Heaven " + ChatColor.DARK_GREEN + "1.8"
				+ ChatColor.GOLD + " ]===--X");
	}

	/**
	 * On quit.
	 *
	 * @param e
	 *            the e
	 */
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onQuit(final PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		final IPlayerData pd = SH.getManager().getPlayerDataManager()
				.getPlayerData(p.getUniqueId().toString());
		if (pd != null) {
			pd.setGamemode(p.getGameMode().getValue());
			pd.setLastlocation(p.getLocation());
			pd.setTimeplayed((pd.getTimeplayed() + (new Date()).getTime())
					- pd.getLastlogin().getTime());
		} else {
			SH.getManager().getPlayerDataManager().createPlayerData(p);
		}
	}

	/**
	 * On quit2.
	 *
	 * @param e
	 *            the e
	 */
	@EventHandler
	public void onQuit2(final PlayerQuitEvent e) {
		e.setQuitMessage(null);
		Bukkit.broadcastMessage(ChatColor.RED + e.getPlayer().getName()
				+ " logget av");
	}

}
