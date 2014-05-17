package net.jamcraft.chowtime.core.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by James Hollowell on 5/16/2014.
 */
public class ContainterFermenter extends Container
{

    @Override public boolean canInteractWith(EntityPlayer var1)
    {
        return false;
    }
}
