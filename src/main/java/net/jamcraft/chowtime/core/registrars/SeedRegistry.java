package net.jamcraft.chowtime.core.registrars;

import net.minecraft.item.ItemSeeds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 5/23/2014.
 */
public class SeedRegistry
{
    //TODO: Make waited percentages, not just straight
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
