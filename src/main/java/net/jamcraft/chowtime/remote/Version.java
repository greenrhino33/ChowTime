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

package net.jamcraft.chowtime.remote;

/**
 * Created by James Hollowell on 5/15/2014.
 */
public class Version
{
    private int Major = 0;
    private int Minor = 0;
    private int Build = 0;

    public int getMajor()
    {
        return Major;
    }

    public int getMinor()
    {
        return Minor;
    }

    public int getBuild()
    {
        return Build;
    }

    public Version(int major, int minor, int build)
    {
        Major = major;
        Minor = minor;
        Build = build;
    }

    @Override public String toString()
    {
        return Major + "." + Minor + "." + Build;
    }

    public void readFromString(String s)
    {
        Major = Integer.parseInt(s.split("\\.")[0]);
        Minor = Integer.parseInt(s.split("\\.")[1]);
        Build = Integer.parseInt(s.split("\\.")[2]);
    }

    @Override public boolean equals(Object o)
    {
        if (!(o instanceof Version)) return false;
        Version other = (Version) o;
        return other.getMajor() == Major && other.getMinor() == Minor && other.getBuild() == Build;
    }
}
