package net.jamcraft.chowtime.core.container.slot;

import net.jamcraft.chowtime.core.tileentities.TEIceCreamMaker;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class SlotIceFuel extends Slot
{
    public SlotIceFuel(IInventory par1IInventory, int par2, int par3, int par4)
    {
        super(par1IInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return TEIceCreamMaker.isIceFuel(par1ItemStack);
    }
}
