/**
 * This file is part of survivalheaven.org, licensed under the MIT License (MIT).
 *
 * Copyright (c) SurvivalHeaven.org <http://www.survivalheaven.org>
 * Copyright (c) NordByen.info <http://www.nordbyen.info>
 * Copyright (c) l0lkj.info <http://www.l0lkj.info>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package info.nordbyen.survivalheaven.subplugins.old_stuff.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// TODO: Auto-generated Javadoc
/**
 * The Class TPAccept.
 */
public class TPAccept implements CommandExecutor {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender
     * , org.bukkit.command.Command, java.lang.String, java.lang.String[])
     */
    @Override
    public boolean onCommand(final CommandSender Sender, final Command command, final String commandLabel, final String args[]) {
        if (Sender instanceof Player) {
            if (args.length == 0) {
                if (command.getName().equalsIgnoreCase("tpaccept")) {
                    if (TPA.tpa.containsKey(Sender.getName())) {
                        final Player p = Bukkit.getPlayer(TPA.tpa.get(Sender.getName()));
                        p.teleport(((Player) Sender).getLocation());
                        p.sendMessage(ChatColor.GREEN + "poff");
                        TPA.tpa.remove(Sender.getName());
                        Sender.sendMessage(ChatColor.GREEN + p.getName() + " ble teleportert til deg");
                    } else {
                        Sender.sendMessage(ChatColor.RED + "Du har ingen teleport foresp�rsel");
                    }
                }
            }
        }
        return false;
    }
}