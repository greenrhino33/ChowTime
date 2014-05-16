package net.jamcraft.chowtime.core.tileentities;

import net.jamcraft.chowtime.core.blocks.machines.Fermenter;
import net.jamcraft.chowtime.core.recipies.FermenterRecipies;
import net.jamcraft.chowtime.core.recipies.Recipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class TEIceCreamMaker extends TileEntity implements ISidedInventory
{
    public static final int INV_SIZE=2;
    private ItemStack[] inventory=new ItemStack[INV_SIZE];

    @Override public int getSizeInventory()
    {
        return INV_SIZE;
    }

    @Override public ItemStack getStackInSlot(int var1)
    {
        if(var1>INV_SIZE) return null;
        return inventory[var1];
    }

    @Override public ItemStack decrStackSize(int slot, int amount)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= amount)
            {
                setInventorySlotContents(slot, null);
            }
            else
            {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    @Override public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null)
        {
            setInventorySlotContents(slot, null);
        }
        return itemStack;
    }

    @Override public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        inventory[slot] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override public String getInventoryName()
    {
        return "containter.Fermenter";
    }

    @Override public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override public boolean isUseableByPlayer(EntityPlayer var1)
    {
        return true;
    }

    @Override public void openInventory()
    {
    }

    @Override public void closeInventory()
    {
    }

    @Override public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
        if (slot!=0) return false;
        for(Recipe r:FermenterRecipies.recipeList)
        {
            if (r.getInput().equals(stack)) return true;
        }
        return false;
    }

    @Override public int[] getAccessibleSlotsFromSide(int side)
    {
        if(ForgeDirection.UP.flag==side) return { 0 };
        return new int[0];
    }

    @Override public boolean canInsertItem(int var1, ItemStack var2, int var3)
    {
        return false;
    }

    @Override public boolean canExtractItem(int var1, ItemStack var2, int var3)
    {
        return false;
    }
}
