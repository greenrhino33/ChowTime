package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class ItemRaspberry extends Item
{
    public ItemRaspberry()
    {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        setUnlocalizedName("raspberry");
        this.setTextureName(ModConstants.MODID + ":raspberry");
    }
}
