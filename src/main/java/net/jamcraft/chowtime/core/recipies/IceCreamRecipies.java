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

    public static Recipe2_1 GetRecipeFromStack(ItemStack stack1, ItemStack stack2)
    {
        for (Recipe2_1 r : recipe11List)
        {
            if (r.getInput1().getItem().equals(stack1.getItem()) && r.getInput2().getItem().equals(stack2.getItem()))
                return r;
            if (r.getInput2().getItem().equals(stack1.getItem()) && r.getInput1().getItem().equals(stack2.getItem()))
                return r;
        }
        return null;
    }

//    public static Recipe2_1[] GetRecipesFromStack(ItemStack)
}
