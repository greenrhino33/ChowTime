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

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by James Hollowell on 5/23/2014.
 */
public class HarvestLevelRegistry
{
    private static  Map<Block, Integer> reg=new HashMap<Block, Integer>();

    public static void AddBlockHavestXP(Block block, int harvestXP)
    {
        reg.put(block,harvestXP);
    }

    public static Block[] GetBlocks(int harvestXP)
    {
        List<Block> blocks=new ArrayList<Block>();
        for(Block b: reg.keySet())
        {
            if(harvestXP>=reg.get(b)) blocks.add(b);
        }
        return blocks.toArray(new Block[]{});
    }

    public static boolean IsInList(Block block)
    {
        for(Block b: reg.keySet())
        {
            if(b.equals(block))return true;
        }
        return false;
    }
}
