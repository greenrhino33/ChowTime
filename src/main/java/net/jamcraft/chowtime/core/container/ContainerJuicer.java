package net.jamcraft.chowtime.core.container;

import net.jamcraft.chowtime.core.container.slot.SlotJuicer;
import net.jamcraft.chowtime.core.container.slot.SlotOutput;
import net.jamcraft.chowtime.core.recipies.JuicerRecipes;
import net.jamcraft.chowtime.core.tileentities.TEJuicer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class ContainerJuicer extends Container
{

    public ContainerJuicer(InventoryPlayer playerInv, TEJuicer te)
    {
        // Add input
        this.addSlotToContainer(new SlotJuicer(te, 0, 54, 34));

        this.addSlotToContainer(new SlotOutput(te, 1, 116, 34));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(playerInv, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 84 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(playerInv, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 142));
        }
    }

    @Override public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {

            ItemStack slotItemStack = slot.getStack();
            itemStack = slotItemStack.copy();

            /**
             * If we are shift-clicking an item out of the RocketAssembler
             * container, attempt to put it in the first available slot in the
             * player's inventory
             */
            if (slotIndex < TEJuicer.INV_SIZE)
            {

                if (!this.mergeItemStack(slotItemStack, TEJuicer.INV_SIZE, inventorySlots.size(), false))
                {
                    return null;
                }
            }
            else
            {

                /**
                 * If the stack being shift-clicked into the assembler's
                 * container is a fuel, try to put it in the fuel slot.
                 */
                if (JuicerRecipes.GetRecipeFromStack(slotItemStack) != null)
                {
                    if (!this.mergeItemStack(slotItemStack, 0, TEJuicer.INV_SIZE, false))
                    {
                        return null;
                    }
                }
                else
                {
                    return null;
                }
            }

            if (slotItemStack.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemStack;
    }
}
