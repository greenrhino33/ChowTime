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

import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class Recipe1_1
{
    private ItemStack input;
    private ItemStack output;
    private int time;

    /**
     * Creates a recipe with 1 input and one output
     *
     * @param input  Input item stack
     * @param output Output item stack
     * @param time   Time in ticks
     */
    public Recipe1_1(ItemStack input, ItemStack output, int time)
    {
        this.input = input.copy();
        this.output = output.copy();
        this.time = time;
    }

    public ItemStack getInput()
    {
        return input;
    }

    public ItemStack getOutput()
    {
        return output;
    }

    public int getTime()
    {
        return time;
    }
}
