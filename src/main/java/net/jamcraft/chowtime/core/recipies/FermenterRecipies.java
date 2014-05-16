package net.jamcraft.chowtime.core.recipies;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class FermenterRecipies
{
    public static List<Recipe> recipeList = new ArrayList<Recipe>();

    public static void AddRecipe(ItemStack input, ItemStack output, int time)
    {
        Recipe r = new Recipe(input, output, time);
        recipeList.add(r);
    }
}
