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
