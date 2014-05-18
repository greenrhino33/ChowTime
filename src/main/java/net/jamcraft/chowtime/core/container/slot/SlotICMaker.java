package net.jamcraft.chowtime.core.container.slot;

import net.jamcraft.chowtime.core.recipies.IceCreamRecipies;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class SlotICMaker extends Slot
{
    public SlotICMaker(IInventory par1IInventory, int par2, int par3, int par4)
    {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return IceCreamRecipies.GetRecipesFromStack(stack) != null;
    }
}
