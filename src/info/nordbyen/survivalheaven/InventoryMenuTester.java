package info.nordbyen.survivalheaven;

import info.nordbyen.survivalheaven.api.command.AbstractCommand;
import info.nordbyen.survivalheaven.api.subplugin.SubPlugin;
import info.nordbyen.survivalheaven.api.util.InventoryMenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryMenuTester extends SubPlugin {

	InventoryMenu testMenu;
	
	public InventoryMenuTester(String name) {
		super(name);
	}

	@Override
	protected void disable() {
		testMenu.destroy();
	}

	@Override
	protected void enable() {
		testMenu = new InventoryMenu("TestMenu", 9, new InventoryMenu.OptionClickEventHandler() {
            @Override
            public void onOptionClick(InventoryMenu.OptionClickEvent event) {
                event.getPlayer().sendMessage("Du trykket på " + event.getName());
                event.setWillClose(true);
            }
        })
        .setOption(3, new ItemStack(Material.APPLE, 1), "Valg1", "Dette valget er fancy :3")
        .setOption(4, new ItemStack(Material.IRON_SWORD, 1), "Valg2", "Dette valget er fancy :3")
        .setOption(5, new ItemStack(Material.EMERALD, 1), "Valg3", "Dette valget er fancy :3");
		new OpenMenuCommand();
	}
	
	public class OpenMenuCommand extends AbstractCommand {

		private OpenMenuCommand() {
			super("openmenu", "/<command>", "Åpner en testmeny");
			register();
		}

		@Override
		public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
			if( !(sender instanceof Player) ) {
				sender.sendMessage( ChatColor.RED + "Du må være en spiller");
				return false;
			}
			Player p = (Player)sender;
			testMenu.open( p );
			p.sendMessage(ChatColor.GREEN + "Åpnet testmenyen");
			return true;
		}
	}
}
