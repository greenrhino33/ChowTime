package net.jamcraft.chowtime.core.container.slot;

import net.jamcraft.chowtime.core.recipies.FermenterRecipies;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class SlotFermenter extends Slot
{
    public SlotFermenter(IInventory par1IInventory, int par2, int par3, int par4)
    {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return FermenterRecipies.GetRecipeFromStack(stack)!=null;
    }
}
