package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class ItemRaspberry extends ItemFood
{
    public ItemRaspberry()
    {
        super(1,false);
        this.setCreativeTab(ChowTime.creativeTab);
        setUnlocalizedName("raspberry");
        this.setTextureName(ModConstants.MODID + ":raspberry");
    }
}
