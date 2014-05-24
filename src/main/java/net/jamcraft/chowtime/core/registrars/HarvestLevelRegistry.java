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
