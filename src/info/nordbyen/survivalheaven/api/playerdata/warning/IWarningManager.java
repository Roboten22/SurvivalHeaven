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

package info.nordbyen.survivalheaven.api.playerdata.warning;

import info.nordbyen.survivalheaven.api.playerdata.IPlayerData;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * The Interface IWarningManager.
 */
public interface IWarningManager {

    /**
     * The Interface IWarning.
     */
    public static interface IWarning {

        /**
         * The Enum Level.
         */
        public enum Level {
            /** The low. */
            LOW(1),
            /** The medium. */
            MEDIUM(2),
            /** The high. */
            HIGH(3);

            /**
             * Gets the level from int.
             * 
             * @param level the level
             * @return the level from int
             */
            static Level getLevelFromInt(final int level) {
                if (level == 1)
                    return LOW;
                if (level == 2)
                    return MEDIUM;
                if (level == 3)
                    return HIGH;
                throw new IllegalArgumentException("level " + level + " finnes ikke!");
            }

            /** The level. */
            int level;

            /**
             * Instantiates a new level.
             * 
             * @param level the level
             */
            Level(final int level) {
                this.level = level;
            }

            /**
             * As int.
             * 
             * @return the int
             */
            public int asInt() {
                return level;
            }
        }

        /**
         * Gets the date.
         * 
         * @return the date
         */
        Date getDate();

        /**
         * Gets the id.
         * 
         * @return the id
         */
        int getId();

        /**
         * Gets the level.
         * 
         * @return the level
         */
        Level getLevel();

        /**
         * Gets the message.
         * 
         * @return the message
         */
        String getMessage();

        /**
         * Gets the player.
         * 
         * @return the player
         */
        IPlayerData getPlayer();

        /**
         * Gets the setter.
         * 
         * @return the setter
         */
        IPlayerData getSetter();
    }

    /**
     * Adds the warning.
     * 
     * @param date the date
     * @param player the player
     * @param setter the setter
     * @param message the message
     * @throws SQLException the SQL exception
     */
    public void addWarning(Date date, IPlayerData player, IPlayerData setter, String message) throws SQLException;

    /**
     * Adds the warning.
     * 
     * @param date the date
     * @param player the player
     * @param setter the setter
     * @param message the message
     * @param level the level
     * @throws SQLException the SQL exception
     */
    public void addWarning(Date date, IPlayerData player, IPlayerData setter, String message, IWarning.Level level) throws SQLException;

    /**
     * Gets the every warnings.
     * 
     * @return the every warnings
     */
    public List<IWarning> getEveryWarnings();

    /**
     * Gets the warning from id.
     * 
     * @param id the id
     * @return the warning from id
     */
    public IWarning getWarningFromId(int id);

    /**
     * Gets the warnings from name.
     * 
     * @param name the name
     * @return the warnings from name
     */
    public List<IWarning> getWarningsFromName(String name);

    /**
     * Gets the warnings from player.
     * 
     * @param pd the pd
     * @return the warnings from player
     */
    public List<IWarning> getWarningsFromPlayer(IPlayerData pd);

    /**
     * Gets the warnings from uuid.
     * 
     * @param uuid the uuid
     * @return the warnings from uuid
     */
    public List<IWarning> getWarningsFromUuid(String uuid);

    /**
     * Removes the warning.
     * 
     * @param id the id
     */
    public void removeWarning(int id);

    /**
     * Removes the warning.
     * 
     * @param warning the warning
     */
    public void removeWarning(IWarning warning);
}
