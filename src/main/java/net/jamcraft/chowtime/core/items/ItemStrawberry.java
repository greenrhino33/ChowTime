package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class ItemStrawberry extends Item
{

    public ItemStrawberry()
    {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        setUnlocalizedName("strawberry");
        this.setTextureName(ModConstants.MODID + ":strawberry");
    }
}
