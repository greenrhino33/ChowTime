package net.jamcraft.chowtime.core.recipies;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class JuicerRecipes
{
    public static List<Recipe1_1> recipe11List = new ArrayList<Recipe1_1>();

    public static void AddRecipe(ItemStack input, ItemStack output, int time)
    {
        Recipe1_1 r = new Recipe1_1(input, output, time);
        recipe11List.add(r);
    }

    public static Recipe1_1 GetRecipeFromStack(ItemStack stack)
    {
        if(stack==null) return null;
        for (Recipe1_1 r : recipe11List)
        {
            if (r.getInput().getItem().equals(stack.getItem())) return r;
        }
        return null;
    }
}
