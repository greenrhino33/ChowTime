package net.jamcraft.chowtime.core.tileentities;

import net.jamcraft.chowtime.core.recipies.IceCreamRecipies;
import net.jamcraft.chowtime.core.recipies.Recipe2_1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class TEIceCreamMaker extends TileEntity implements ISidedInventory
{
    public static final int INV_SIZE = 3;
    private ItemStack[] inventory = new ItemStack[INV_SIZE];
    private int ticksLeft = 0;
    private int maxTicks = 0;

    public TEIceCreamMaker()
    {
        IceCreamRecipies.AddRecipe(new ItemStack(Items.apple), new ItemStack(Items.carrot), new ItemStack(Items.arrow), 60);
    }

    @Override public int getSizeInventory()
    {
        return INV_SIZE;
    }

    @Override public ItemStack getStackInSlot(int var1)
    {
        if (var1 > INV_SIZE) return null;
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
        return "containter.IceCreamMaker";
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
        return (slot == 0 || slot == 1) && IceCreamRecipies.GetRecipeFromStack(inventory[0], inventory[1]) != null;
    }

    @Override public int[] getAccessibleSlotsFromSide(int side)
    {
        //if(ForgeDirection.UP.flag==side) return new int[]{ 0 };
        return new int[] { 0, 1, 2 };
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
        return slot == 2;//&&side!=ForgeDirection.UP.flag;
    }

    @Override
    public void writeToNBT(NBTTagCompound tags)
    {
        super.writeToNBT(tags);

        tags.setInteger("timeleft", ticksLeft);
        tags.setInteger("maxTime", maxTicks);

        if (inventory[0] != null)
        {
            NBTTagCompound sl1 = new NBTTagCompound();
            inventory[0].writeToNBT(sl1);
            tags.setTag("slot1", sl1);
        }

        if (inventory[1] != null)
        {
            NBTTagCompound sl2 = new NBTTagCompound();
            inventory[1].writeToNBT(sl2);
            tags.setTag("slot2", sl2);
        }

        if (inventory[2] != null)
        {
            NBTTagCompound sl3 = new NBTTagCompound();
            inventory[2].writeToNBT(sl3);
            tags.setTag("slot3", sl3);
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound tags)
    {
        super.readFromNBT(tags);

        ticksLeft = tags.getInteger("timeleft");
        maxTicks = tags.getInteger("maxTime");

        if (tags.hasKey("slot1"))
        {
            inventory[0] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot1"));
        }
        if (tags.hasKey("slot2"))
        {
            inventory[1] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot2"));
        }
        if (tags.hasKey("slot3"))
        {
            inventory[2] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot3"));
        }
    }

    @Override
    public void updateEntity()
    {
        //Something in input and nothing currently processing
        if (inventory[0] != null && inventory[1] != null && ticksLeft == 0)
        {
            Recipe2_1 r = IceCreamRecipies.GetRecipeFromStack(inventory[0], inventory[1]);
            if (r != null)
            {
                maxTicks = r.getTime();
            }
        }
        if (ticksLeft < maxTicks)
        {
            ticksLeft++;
        }
        if (ticksLeft == maxTicks)
        {
            ticksLeft = 0;
            make();
        }
    }

    private void make()
    {
        if (inventory[0] == null || inventory[1] == null) return;
        ItemStack res = IceCreamRecipies.GetRecipeFromStack(inventory[0], inventory[1]).getOutput();
        if (inventory[2] == null)
            inventory[2] = res.copy();
        else
            inventory[2].stackSize += res.stackSize;

        inventory[1].stackSize--;
        if (inventory[1].stackSize <= 0)
        {
            inventory[1] = null;
        }

        inventory[0].stackSize--;
        if (inventory[0].stackSize <= 0)
        {
            inventory[0] = null;
        }
    }

    /* Packets */
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
    {
        readFromNBT(packet.func_148857_g());
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
}
