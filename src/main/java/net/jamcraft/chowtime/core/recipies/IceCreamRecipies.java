package net.jamcraft.chowtime.core.recipies;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class IceCreamRecipies
{
    public static List<Recipe2_1> recipe11List = new ArrayList<Recipe2_1>();

    public static void AddRecipe(ItemStack input1, ItemStack input2, ItemStack output, int time)
    {
        Recipe2_1 r = new Recipe2_1(input1, input2, output, time);
        recipe11List.add(r);
    }

    public static Recipe1_1 GetRecipeFromStack(ItemStack stack1, Item)
    {
        for (Recipe2_1 r : recipe11List)
        {
            if (r.getInput().getItem().equals(stack.getItem())) return r;
        }
        return null;
    }
}
