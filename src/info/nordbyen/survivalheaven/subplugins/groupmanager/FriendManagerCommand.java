package info.nordbyen.survivalheaven.subplugins.groupmanager;

import java.sql.SQLException;
import java.util.ArrayList;

import info.nordbyen.survivalheaven.SH;
import info.nordbyen.survivalheaven.api.playerdata.IPlayerData;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FriendManagerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String arg, final String[] args) {
		if( !( sender instanceof Player ) ) {
			sender.sendMessage( ChatColor.RED + "En konsoll kan ikke ha venner .-." );
			return true;
		}

		Player p = ( Player ) sender;
		IPlayerData pd = SH.getManager().getPlayerDataManager().getPlayerData( p.getUniqueId().toString() );
		FriendManager fm = SH.getManager().getFriendManager();

		if( args.length == 0 ) {
			p.sendMessage( ChatColor.GREEN + "************************************************");
			p.sendMessage( ChatColor.BLUE + "Kommandoer til vennesystemet");
			p.sendMessage( ChatColor.GRAY + "En spiller som er venn med deg kan �pne dine kister og knuse dine blokker. Pass p� hvem du legger til som venn!");
			p.sendMessage( ChatColor.GREEN + "------------------------------------------------");
			p.sendMessage( ChatColor.YELLOW + "/venn " + ChatColor.GRAY + "Lister kommandoer");
			p.sendMessage( ChatColor.YELLOW + "/venn list " + ChatColor.GRAY + "Lister alle dine venner");
			p.sendMessage( ChatColor.YELLOW + "/venn sp�r <spiller> " + ChatColor.GRAY + "Sender en venneforesp�rsel");
			p.sendMessage( ChatColor.YELLOW + "/venn godta <spiller> " + ChatColor.GRAY + "Godtar en venneforesp�rsel");
			p.sendMessage( ChatColor.YELLOW + "/venn avsl� <spiller> " + ChatColor.GRAY + "Avsl�r en venneforesp�rsel");
			p.sendMessage( ChatColor.YELLOW + "/venn foresp�rsler " + ChatColor.GRAY + "Lister alle dine venneforesp�rsler");
			p.sendMessage( ChatColor.RED + "/venn fjern <spiller " + ChatColor.GRAY + "Sletter en spiller fra vennelisten din");
			p.sendMessage( ChatColor.GREEN + "************************************************");
			return true;
		}

		if( args[0].equalsIgnoreCase( "list" ) ) {
			if( args.length != 1 ) {
				p.sendMessage( ChatColor.RED + "Feil syntax: /venn list" );
				return true;
			}
			ArrayList< IPlayerData > list = fm.getFriendsWith( pd );
			if( list.size() == 0 ) {
				p.sendMessage( ChatColor.GREEN + "Du har for �yeblikket ingen spiller p� vennelisten. Legg til venner med /venn sp�r <spiller>");
				return true;
			}
			StringBuilder venner = new StringBuilder( ChatColor.GREEN + "F�lgende personer er venn med deg:\n" + ChatColor.AQUA);
			for( IPlayerData venn : list ) {
				venner.append( venn.getName() + ", " );
			}
			String l = venner.substring( 0, venner.length() - 2 );
			p.sendMessage( l );
			return true;
		} else if( args[0].equalsIgnoreCase( "sp�r" ) ) {
			if( args.length != 2 ) {
				p.sendMessage( ChatColor.RED + "Feil syntax: /venn sp�r <spiller>");
				return true;
			}
			Player r = Bukkit.getPlayer( args[1] );
			if( r == null ) {
				p.sendMessage( ChatColor.RED + "Fant ikke spilleren " + args[1] );
				return true;
			}
			ArrayList< IPlayerData > list = fm.getFriendsWith( pd );
			for( IPlayerData fpd : list ) {
				if( fpd.getUUID().equals( r.getUniqueId().toString() ) ) {
					p.sendMessage( ChatColor.RED + "Du er allerede venn med " + r.getName() );
					return true;
				}
			}
			ArrayList< String > reql = fm.getFriendrequestsTo( args[1] );
			
			if( reql != null && reql.contains( p.getName() ) ) {
				p.sendMessage( ChatColor.RED + "Du har allerede sendt en foresp�rsel til denne spilleren" );
				return true;
			}
			p.sendMessage( ChatColor.GREEN + "Du sendte en venneforesp�rsel til " + r.getName() );

			r.sendMessage( ChatColor.GREEN + "************************************************");
			r.sendMessage( ChatColor.BLUE + "Du har mottat en venneforesp�rsel fra " + p.getName());
			r.sendMessage( ChatColor.GRAY + "En spiller som er venn med deg kan �pne dine kister og knuse dine blokker. Pass p� hvem du legger til som venn!");
			r.sendMessage( ChatColor.GREEN + "------------------------------------------------");
			r.sendMessage( ChatColor.YELLOW + "/venn godta " + p.getName() + ChatColor.GRAY + " For � godta foresp�rselen");
			r.sendMessage( ChatColor.YELLOW + "/venn avsl� " + p.getName() + ChatColor.GRAY + " For � avsl� foresp�rselen");
			r.sendMessage( ChatColor.GREEN + "************************************************");
			
			ArrayList< String > req = fm.getFriendrequestsTo( args[1] );
			if( req == null ) {
				fm.setFriendrequestsTo( args[1], new ArrayList< String >() );
				req = fm.getFriendrequestsTo( args[1] );
			}
			req.add( p.getName() );
			return true;
		} else if( args[0].equalsIgnoreCase( "godta" ) ) {
			if( args.length != 2 ) {
				p.sendMessage( ChatColor.RED + "Feil syntax: /venn godta <spiller>");
				return true;
			}
			if( !fm.getFriendrequestsTo( p.getName() ).contains( args[1] ) ) {
				p.sendMessage( ChatColor.RED + "Du har ingen foresp�rsler fra " + args[1] );
				return true;
			}
			Player r = Bukkit.getPlayer( args[1] );
			if( r == null ) {
				p.sendMessage( ChatColor.RED + args[0] + " m� v�re online for � gj�re dette");
				return true;
			}
			try {
				fm.setFriends( p.getUniqueId().toString(), r.getUniqueId().toString() );
			} catch (SQLException e) {
				p.sendMessage( ChatColor.RED + "Noe gikk galt... Si ifra til staben eller send en melding til alex.l0lkj p� skype");
				return true;
			}
			fm.getFriendrequestsTo( p.getName() ).remove( r.getName() );
			
			p.sendMessage( ChatColor.GREEN + "Du godtok venneforesp�rselen fra " + args[0]);
			p.sendMessage( ChatColor.GRAY + "Dere er n� venner og kan �delegge hverandres blokker");
			
			r.sendMessage( ChatColor.GREEN + p.getName() + " godtok venneforesp�rselen din");
			r.sendMessage( ChatColor.GRAY + "Dere er n� venner og kan �delegge hverandres blokker");
			return true;
		} else if( args[0].equalsIgnoreCase( "fjern" ) ) {
			IPlayerData fpd = SH.getManager().getPlayerDataManager().getPlayerDataFromName( args[1] );
			if( !fm.isFriends( pd, fpd ) ) {
				p.sendMessage( ChatColor.RED + "Dere er ikke venner");
				return true;
			}
			try {
				fm.removeFriendship( pd.getUUID(), fpd.getUUID() );
			} catch (SQLException e) {
				p.sendMessage( ChatColor.RED + "Noe gikk galt... Si ifra til staben eller send en melding til alex.l0lkj p� skype");
				return true;
			}
			p.sendMessage( ChatColor.GREEN + "Du fjernet " + args[0] + " fra dine venner");
			Player r = Bukkit.getPlayer( args[0]);
			if( r != null )
				r.sendMessage( ChatColor.RED + p.getName() + " fjernet deg fra sine venner!");
			return true;
		}
		return true;
	}
}
