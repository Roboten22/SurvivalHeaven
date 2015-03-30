package info.nordbyen.survivalheaven.subplugins.regions;

import info.nordbyen.survivalheaven.SH;
import info.nordbyen.survivalheaven.api.mysql.IMysqlManager;
import info.nordbyen.survivalheaven.api.rankmanager.RankType;
import info.nordbyen.survivalheaven.api.subplugin.SubPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BlockLagManager {

	ArrayList<String> members = new ArrayList<String>();
	ArrayList<String> owners = new ArrayList<String>();

	private static BlockLagManager instance;

	public static BlockLagManager getInstance() {
		if( instance == null )
			instance = new BlockLagManager();
		return instance;
	}

	private void setupTables() throws SQLException {
		IMysqlManager sql = SH.getManager().getMysqlManager();
		sql.query("CREATE TABLE IF NOT EXISTS blocklag_members ("
				+ "`id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "`uuid` VARCHAR(45) NOT NULL"
				+ ");");
		sql.query("CREATE TABLE IF NOT EXISTS blocklag_owners ("
				+ "`id` INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
				+ "`uuid` VARCHAR(45) NOT NULL"
				+ ");");
	}

	private void loadFromTables() throws SQLException {
		members = new ArrayList<String>();
		owners = new ArrayList<String>();
		IMysqlManager sql = SH.getManager().getMysqlManager();
		ResultSet resultSetMembers = sql.query("SELECT * FROM blocklag_members;");
		while( resultSetMembers != null && resultSetMembers.next() ) {
			members.add(resultSetMembers.getString("uuid"));
		}
		ResultSet resultSetOwners = sql.query("SELECT * FROM blocklag_owners;");
		while( resultSetOwners != null && resultSetOwners.next() ) {
			owners.add(resultSetOwners.getString("uuid"));
		}
	}

	public void addMember( String uuid ) throws SQLException {
		if( members.contains( uuid ))
			return;
		IMysqlManager sql = SH.getManager().getMysqlManager();
		sql.query("INSERT INTO blocklag_members ( uuid ) VALUES ( '" + uuid + "' );");
		members.add( uuid );
	}

	public void addOwner( String uuid ) throws SQLException {
		if( owners.contains( uuid ))
			return;
		IMysqlManager sql = SH.getManager().getMysqlManager();
		sql.query("INSERT INTO blocklag_owners ( uuid ) VALUES ( '" + uuid + "' );");
		owners.add( uuid );
	}

	public void removeMember( String uuid ) throws SQLException {
		if( !members.contains( uuid ))
			return;
		IMysqlManager sql = SH.getManager().getMysqlManager();
		sql.query("DELETE FROM blocklag_members WHERE uuid='" + uuid + "';");
		members.remove( uuid );
	}

	public void removeOwner( String uuid ) throws SQLException {
		if( !owners.contains( uuid ))
			return;
		IMysqlManager sql = SH.getManager().getMysqlManager();
		sql.query("DELETE FROM blocklag_owners WHERE uuid='" + uuid + "';");
		owners.remove( uuid );
	}

	public boolean canBreak( Player p, Block b ) {
		if( SH.getManager().getRankManager().getRank( p.getUniqueId().toString() ).getId() >= RankType.MODERATOR.getId() ) {
			SH.debug("p=" + p, "b=" + b, "er høy nok rank");
			return true;
		}
		if( isMember( p.getUniqueId().toString() ) ) {
			SH.debug("p=" + p, "b=" + b, "medlem i byen");
			return true;
		}
		SH.debug("p=" + p, "b=" + b, "kan ikke ødelegge");
		return true;//todo
	}

	public boolean useBlockProtection( Player p, Block b ) {
		if( !( b.getType() == Material.CHEST || b.getType() == Material.TRAPPED_CHEST ) ) {
			return true; // TODO
		}
		return true;
	}

	public boolean isMember( String uuid ) {
		SH.debug("uuid=" + uuid, "members=" + members, "owners=" + owners);
		return listContainsString(owners, uuid) || listContainsString(members, uuid);
	}

	public boolean isOwner( String uuid ) {
		return listContainsString(owners, uuid);
	}

	public boolean listContainsString( List<String> list, String string ) {
		for( String s : list )
			if (s.equalsIgnoreCase( string ))
				return true;
		return false;
	}

	public static class BlockLagPlugin extends SubPlugin {

		public BlockLagPlugin(String name) {
			super(name);
		}

		@Override
		protected void disable() {}

		@Override
		protected void enable() {
			try {
				getInstance().setupTables();
				getInstance().loadFromTables();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public class BlockLagListener implements Listener {

	}
}
