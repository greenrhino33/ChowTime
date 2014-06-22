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

package net.jamcraft.chowtime.core.registrars;

import net.minecraft.item.ItemSeeds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 5/23/2014.
 */
public class SeedRegistry
{
    //TODO: Make weighted percentages, not just straight
    private static List<ItemSeeds> seeds = new ArrayList<ItemSeeds>();

    public static void AddSeed(ItemSeeds seed)
    {
        seeds.add(seed);
    }

    public static ItemSeeds[] getSeeds()
    {
        return seeds.toArray(new ItemSeeds[] { });
    }
}
