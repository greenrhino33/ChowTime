package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class ItemRasberry extends Item
{
    public ItemRasberry()
    {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        setUnlocalizedName("rasberry");
        this.setTextureName(ModConstants.MODID + ":rasberry");
    }
}
