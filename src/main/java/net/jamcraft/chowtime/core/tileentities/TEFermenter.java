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

package net.jamcraft.chowtime.core.tileentities;

import net.jamcraft.chowtime.core.recipies.FermenterRecipies;
import net.jamcraft.chowtime.core.recipies.Recipe1_1;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class TEFermenter extends TileEntity implements ISidedInventory
{
    public static final int INV_SIZE = 2;
    private ItemStack[] inventory = new ItemStack[INV_SIZE];
    private int ticksLeft = 0;
    private int maxTicks = 0;

    public TEFermenter()
    {
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
        return "container.Fermenter";
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
        if (slot != 0) return false;
        for (Recipe1_1 r : FermenterRecipies.recipe11List)
        {
            if (r.getInput().getItem().equals(stack.getItem())) return true;
        }
        return false;
        //        return true;
    }

    @Override public int[] getAccessibleSlotsFromSide(int side)
    {
        //if(ForgeDirection.UP.flag==side) return new int[]{ 0 };
        return new int[] { 0, 1 };
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
        return slot == 1;//&&side!=ForgeDirection.UP.flag;
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
        //        // Write the ItemStacks in the inventory to NBT
        //        NBTTagList tagList = new NBTTagList();
        //        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        //        {
        //            if (inventory[currentIndex] != null)
        //            {
        //                NBTTagCompound tagCompound = new NBTTagCompound();
        //                tagCompound.setByte("Slot", (byte) currentIndex);
        //                inventory[currentIndex].writeToNBT(tagCompound);
        //                tagList.appendTag(tagCompound);
        //            }
        //        }
        //        tags.setTag("Items", tagList);
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

        //        NBTTagList tagList = tags.getTagList("Items",INV_SIZE);
        //        inventory = new ItemStack[this.getSizeInventory()];
        //        for (int i = 0; i < tagList.tagCount(); ++i)
        //        {
        //            NBTTagCompound tagCompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
        //            byte slot = tagCompound.getByte("Slot");
        //            if (slot >= 0 && slot < inventory.length)
        //            {
        //                inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
        //            }
        //        }
    }

    @Override
    public void updateEntity()
    {
        //Something in input and nothing currently processing
        if (inventory[0] != null && ticksLeft == 0)
        {
            Recipe1_1 r = FermenterRecipies.GetRecipeFromStack(inventory[0]);
            if (r != null)
            {
                maxTicks = r.getTime();
            }
        }
        if (ticksLeft < maxTicks && FermenterRecipies.GetRecipeFromStack(inventory[0]) != null)
        {
            Recipe1_1 r=FermenterRecipies.GetRecipeFromStack(inventory[0]);
            if (inventory[1] == null || (r.getOutput().isItemEqual(inventory[1])&&r.getOutput().getMaxStackSize()>inventory[1].stackSize))
            {
                ticksLeft++;
            }
            else
            {
                ticksLeft = 0;
            }
        }
        if (FermenterRecipies.GetRecipeFromStack(inventory[0]) == null && ticksLeft > 0)
        {
            ticksLeft = 0;
        }
        if (ticksLeft == maxTicks)
        {
            ticksLeft = 0;
            ferment();
        }
//        if(this.worldObj.getBlock(xCoord, yCoord + 1, zCoord) instanceof BlockAir) worldObj.setBlock(xCoord, yCoord + 1, zCoord, Blocks.farmland);
    }

    private void ferment()
    {
        if (FermenterRecipies.GetRecipeFromStack(inventory[0]) == null) return;
        ItemStack res = FermenterRecipies.GetRecipeFromStack(inventory[0]).getOutput();
        if (inventory[1] == null)
            inventory[1] = res.copy();
        else
            inventory[1].stackSize += res.stackSize;

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

    public int getScaledProgress(int scale)
    {
        if (maxTicks == 0) return 0;
        return ticksLeft * scale / maxTicks;
    }
}
