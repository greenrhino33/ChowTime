package net.jamcraft.chowtime.core.container;

import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class ContainterFermenter extends Container
{

    public ContainterFermenter(InventoryPlayer playerInv, TEFermenter te)
    {
        // Add input
        this.addSlotToContainer(new Slot(te,0, 10, 10));

        this.addSlotToContainer(new Slot(te,1,20,20));

        // Add the player's inventory slots to the container
        for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex)
        {
            for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex)
            {
                this.addSlotToContainer(new Slot(playerInv, inventoryColumnIndex + inventoryRowIndex * 9 + 9, 8 + inventoryColumnIndex * 18, 152 + inventoryRowIndex * 18));
            }
        }

        // Add the player's action bar slots to the container
        for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex)
        {
            this.addSlotToContainer(new Slot(playerInv, actionBarSlotIndex, 8 + actionBarSlotIndex * 18, 210));
        }
    }

    @Override public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }
}
