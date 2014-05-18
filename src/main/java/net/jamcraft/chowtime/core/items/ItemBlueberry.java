package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class ItemBlueberry extends Item
{
    public ItemBlueberry()
    {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        setUnlocalizedName("blueberry");
        this.setTextureName(ModConstants.MODID + ":blueberry");
    }
}
