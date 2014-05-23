package net.jamcraft.chowtime.core.tileentities;

import net.jamcraft.chowtime.core.lib.ItemHelper;
import net.jamcraft.chowtime.core.recipies.IceCreamRecipies;
import net.jamcraft.chowtime.core.recipies.Recipe2_1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by James Hollowell on 5/16/2014.
 */

//TODO: Work on ISided-ness
public class TEIceCreamMaker extends TileEntity implements ISidedInventory
{
    public static final int IN1_LOC = 1;
    public static final int IN2_LOC = 2;
    public static final int OUT_LOC = 3;
    public static final int FUEL_LOC = 4;
    //    public static final int FREEZING_TEMP = -21000;
    public static final int ROOM_TEMP = 25000;
    public static final int MIN_TEMP = -50000;

    public static final int INV_SIZE = 4;
    private ItemStack[] inventory = new ItemStack[INV_SIZE];
    private int ticksLeft = 0;
    private int maxTicks = 0;
    private int temp = ROOM_TEMP; //room temperature (1000=1 Degree Celcius)
    private int freezeTemp = MIN_TEMP;

    public TEIceCreamMaker()
    {
        //        IceCreamRecipies.AddRecipe(new ItemStack(Items.apple), new ItemStack(Items.carrot), new ItemStack(Items.arrow), 60);
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
        return "container.IceCreamMaker";
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
        if(slot == IN1_LOC || slot == IN2_LOC) return IceCreamRecipies.GetRecipesFromStack(stack)!=null;
        if(slot == FUEL_LOC) return TEIceCreamMaker.isIceFuel(stack);
        return false;
    }

    @Override public int[] getAccessibleSlotsFromSide(int side)
    {
        if (ForgeDirection.UP.ordinal() == side)
            return new int[] { IN1_LOC, IN2_LOC };
        if (side == ForgeDirection.DOWN.ordinal()) return new int[] { OUT_LOC };
        return new int[] { FUEL_LOC };
    }

    @Override public boolean canInsertItem(int slot, ItemStack itemStack, int side)
    {
        if (slot == OUT_LOC || side == ForgeDirection.DOWN.ordinal())
            return false;
        return isItemValidForSlot(slot, itemStack);
    }

    @Override public boolean canExtractItem(int slot, ItemStack itemStack, int side)
    {
        return slot == OUT_LOC && side == ForgeDirection.DOWN.ordinal();
    }

    @Override
    public void writeToNBT(NBTTagCompound tags)
    {
        super.writeToNBT(tags);

        tags.setInteger("timeleft", ticksLeft);
        tags.setInteger("maxTime", maxTicks);
        tags.setInteger("temp", temp);
        tags.setInteger("freezingTemp", freezeTemp);

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

        if (inventory[3] != null)
        {
            NBTTagCompound sl4 = new NBTTagCompound();
            inventory[3].writeToNBT(sl4);
            tags.setTag("slot4", sl4);
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound tags)
    {
        super.readFromNBT(tags);

        ticksLeft = tags.getInteger("timeleft");
        maxTicks = tags.getInteger("maxTime");
        temp = tags.getInteger("temp");
        freezeTemp = tags.getInteger("freezingTemp");

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
        if (tags.hasKey("slot4"))
        {
            inventory[3] = ItemStack.loadItemStackFromNBT(tags.getCompoundTag("slot4"));
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
                freezeTemp = r.getTemp();
            }
        }
        //Actual processing
        if (temp < freezeTemp)
        {
            if (ticksLeft < maxTicks && IceCreamRecipies.GetRecipeFromStack(inventory[0], inventory[1]) != null)
            {
                if (inventory[2] == null || IceCreamRecipies.GetRecipeFromStack(inventory[0], inventory[1]).getOutput().getItem().equals(inventory[2].getItem()))
                {
                    ticksLeft++;
                }
                else
                {
                    ticksLeft = 0;
                }
            }
            if (IceCreamRecipies.GetRecipeFromStack(inventory[0], inventory[1]) == null && ticksLeft > 0)
            {
                ticksLeft = 0;
            }
            if (ticksLeft == maxTicks && maxTicks != 0)
            {
                ticksLeft = 0;
                make();
            }
        }

        if (inventory[3] != null && this.worldObj.getTotalWorldTime() % 3 == 0)
        {
            if (temp - iceFuelValue(inventory[3]) > MIN_TEMP)
            {
                if (isIceFuel(inventory[3]))
                {
                    temp -= iceFuelValue(inventory[3]);

                    inventory[3].stackSize--;
                    if (inventory[3].stackSize <= 0)
                    {
                        inventory[3] = null;
                    }
                }
            }
        }

        if (temp < ROOM_TEMP && this.worldObj.getTotalWorldTime() % 10 == 0)
        {
            temp++;
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

        inventory[1] = ItemHelper.decreaseStack(inventory[1], worldObj, xCoord, yCoord, zCoord);

        inventory[0] = ItemHelper.decreaseStack(inventory[0], worldObj, xCoord, yCoord, zCoord);

        //TODO: make temp decrease while makeing, not just at end...
        temp += 200;
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

    public int getScaledTemp(int scale)
    {
        return (ROOM_TEMP - temp) * scale / (ROOM_TEMP - MIN_TEMP);
    }

    public int getTemp()
    {
        return temp;
    }

    public int getScaledFreezeTemp(int scale)
    {
        return (ROOM_TEMP - freezeTemp) * scale / (ROOM_TEMP - MIN_TEMP);
    }

    public static boolean isIceFuel(ItemStack stack)
    {
        return iceFuelValue(stack) != 0;
    }

    public static int iceFuelValue(ItemStack stack)
    {
        if (stack == null) return 0;
        Item i = stack.getItem();
        if (i.equals(Items.snowball))
        {
            return 100;
        }
        if (i.equals(Item.getItemFromBlock(Blocks.snow)))
        {
            return 400;
        }
        if (i.equals(Item.getItemFromBlock(Blocks.ice)))
        {
            return 300;
        }
        if (i.equals(Item.getItemFromBlock(Blocks.packed_ice)))
        {
            return 1000;
        }
        return 0;
    }
}
