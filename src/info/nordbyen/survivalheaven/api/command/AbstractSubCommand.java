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

package info.nordbyen.survivalheaven.api.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

/**
 * The Class AbstractSubCommand.
 */
public abstract class AbstractSubCommand {

    /**
     * Checks if is authorized.
     * 
     * @param sender the sender
     * @param perm the perm
     * @return true, if is authorized
     */
    public boolean isAuthorized(final CommandSender sender, final Permission perm) {
        return sender.hasPermission(perm);
    }

    /**
     * Checks if is authorized.
     * 
     * @param sender the sender
     * @param permission the permission
     * @return true, if is authorized
     */
    public boolean isAuthorized(final CommandSender sender, final String permission) {
        return sender.hasPermission(permission);
    }

    /**
     * Checks if is authorized.
     * 
     * @param player the player
     * @param perm the perm
     * @return true, if is authorized
     */
    public boolean isAuthorized(final Player player, final Permission perm) {
        return player.hasPermission(perm);
    }

    /**
     * Checks if is authorized.
     * 
     * @param player the player
     * @param permission the permission
     * @return true, if is authorized
     */
    public boolean isAuthorized(final Player player, final String permission) {
        return player.hasPermission(permission);
    }

    /**
     * Checks if is player.
     * 
     * @param sender the sender
     * @return true, if is player
     */
    public boolean isPlayer(final CommandSender sender) {
        return (sender instanceof Player);
    }

    /**
     * On sub command.
     * 
     * @param sender the sender
     * @param command the command
     * @param label the label
     * @param args the args
     * @return true, if successful
     */
    public abstract boolean onSubCommand(CommandSender sender, Command command, String label, String[] args);
}