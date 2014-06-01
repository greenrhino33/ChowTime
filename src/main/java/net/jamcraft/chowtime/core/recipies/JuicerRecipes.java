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

package net.jamcraft.chowtime.core.recipies;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class JuicerRecipes
{
    //TODO: make meta sensitive
    public static List<Recipe1_1> recipe11List = new ArrayList<Recipe1_1>();

    public static void AddRecipe(ItemStack input, ItemStack output, int time)
    {
        Recipe1_1 r = new Recipe1_1(input, output, time);
        recipe11List.add(r);
    }

    public static Recipe1_1 GetRecipeFromStack(ItemStack stack)
    {
        if (stack == null) return null;
        for (Recipe1_1 r : recipe11List)
        {
            //&&r.getInput().getItemDamage()==stack.getItemDamage() ??
            if (r.getInput().getItem().equals(stack.getItem())) return r;
        }
        return null;
    }

    public static List<Recipe1_1> GetAllRecipies()
    {
        return ImmutableList.copyOf(recipe11List);
    }
}
