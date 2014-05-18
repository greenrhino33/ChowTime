package net.jamcraft.chowtime.core.items;

import net.minecraft.potion.Potion;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class CTItemDrink extends Potion
{

    public CTItemDrink(int par1, boolean par2, int par3)
    {
        super(par1, par2, par3);
    }

    @Override
    public Potion setIconIndex(int par1, int par2)
    {
        super.setIconIndex(par1, par2);
        return this;
    }
}
