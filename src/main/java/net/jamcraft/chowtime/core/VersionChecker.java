/*
 * ChowTime - Dynamically updating food mod for Minecraft
 *     Copyright (C) 2014  Team JamCraft
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jamcraft.chowtime.core;

/**
 * Created by James Hollowell on 6/2/2014.
 */
public class VersionChecker implements Runnable
{
    private static VersionChecker instance = new VersionChecker();

    //https://github.com/pahimar/Equivalent-Exchange-3/blob/1.6.4/src/main/java/com/pahimar/ee3/helper/VersionHelper.java
    @Override
    public void run()
    {
        
    }

    public static void execute()
    {
        new Thread(instance).start();
    }
}
