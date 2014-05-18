package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ItemBarley extends Item
{

    public ItemBarley()
    {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":barleyCrop");
    }
}
