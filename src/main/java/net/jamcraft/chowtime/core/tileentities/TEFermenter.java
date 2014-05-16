package net.jamcraft.chowtime.core.tileentities;

import net.jamcraft.chowtime.core.recipies.FermenterRecipies;
import net.jamcraft.chowtime.core.recipies.Recipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class TEFermenter extends TileEntity implements ISidedInventory
{
    public static final int INV_SIZE=2;
    private ItemStack[] inventory=new ItemStack[INV_SIZE];
    private int ticksLeft=0;

    public TEFermenter()
    {
        FermenterRecipies.AddRecipe(new ItemStack(Items.apple),new ItemStack(Items.arrow),10);
    }

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
//        for(Recipe r: FermenterRecipies.recipeList)
//        {
//            if (r.getInput().equals(stack)) return true;
//        }
//        return false;
        return true;
    }

    @Override public int[] getAccessibleSlotsFromSide(int side)
    {
        //if(ForgeDirection.UP.flag==side) return new int[]{ 0 };
        return new int[]{0,1};
    }

    @Override public boolean canInsertItem(int slot, ItemStack itemStack, int side)
    {
        return true;
//        if(slot!=0||side!=ForgeDirection.UP.flag) return false;
//        return isItemValidForSlot(slot,itemStack);
    }

    @Override public boolean canExtractItem(int slot, ItemStack itemStack, int side)
    {
//        return true;
        return slot==1;//&&side!=ForgeDirection.UP.flag;
    }


    @Override
    public void updateEntity()
    {
        if(inventory[0]!=null)
        {
            if(ticksLeft<=0)
            {
                for(Recipe r:FermenterRecipies.recipeList)
                {
                    if(r.getInput().equals(inventory[0]))
                    {
                        ticksLeft=r.getTime();
                        break;
                    }
                }
            }
            else
            {
                ticksLeft--;
            }
        }
        else
        {
            ticksLeft=0;
        }
    }
}
