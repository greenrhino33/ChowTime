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
public class IceCreamRecipies
{
    //TODO: make meta sensitive
    public static List<Recipe2_1> recipe21List = new ArrayList<Recipe2_1>();

    public static void AddRecipe(ItemStack input1, ItemStack input2, ItemStack output, int time)
    {
        int temp=-21000;
        Recipe2_1 r = new Recipe2_1(input1, input2, output, time, temp);
        recipe21List.add(r);
    }

    public static Recipe2_1 GetRecipeFromStack(ItemStack stack1, ItemStack stack2)
    {
        if (stack1 == null || stack2 == null) return null;
        for (Recipe2_1 r : recipe21List)
        {
            if (r.getInput1().getItem().equals(stack1.getItem()) && r.getInput2().getItem().equals(stack2.getItem()))
                return r;
            if (r.getInput2().getItem().equals(stack1.getItem()) && r.getInput1().getItem().equals(stack2.getItem()))
                return r;
        }
        return null;
    }

    public static Recipe2_1[] GetRecipesFromStack(ItemStack stack)
    {
        List<Recipe2_1> out = new ArrayList<Recipe2_1>();
        if (stack == null) return null;
        for (Recipe2_1 r : recipe21List)
        {
            if (r.getInput1().getItem().equals(stack.getItem()) || r.getInput2().getItem().equals(stack.getItem()))
                out.add(r);
        }
        return out.toArray(new Recipe2_1[0]);
    }

    public static List<Recipe2_1> GetAllRecipes()
    {
        return ImmutableList.copyOf(recipe21List);
    }
}
