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
public class DynResourceDescription extends DynDescription
{
    public String path;

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof DynResourceDescription)) return false;
        DynResourceDescription other = (DynResourceDescription) o;
        return other.version.equals(version) && other.path.equals(path);
    }
}
