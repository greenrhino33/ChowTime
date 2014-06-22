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
public class Recipe2_1
{
    private ItemStack input1;
    private ItemStack input2;
    private ItemStack output;
    private int time;
    private int temp;

    /**
     * Creates a new recipe with 2 inputs and one output.
     *
     * @param input1 1st Input item stack
     * @param input2 2nd Input item stack
     * @param output Output item stack
     * @param time   Time in ticks
     * @param temp   Temperature of this recipes freezing point
     */
    public Recipe2_1(ItemStack input1, ItemStack input2, ItemStack output, int time, int temp)
    {
        this.input1 = input1.copy();
        this.input2 = input2.copy();
        this.output = output.copy();
        this.time = time;
        this.temp = temp;
    }

    public ItemStack getInput1()
    {
        return input1;
    }

    public ItemStack getInput2()
    {
        return input2;
    }

    public ItemStack getOutput()
    {
        return output;
    }

    public int getTime()
    {
        return time;
    }

    public int getTemp()
    {
        return temp;
    }
}
