package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class ItemTomato extends ItemFood
{
    public ItemTomato()
    {
        super(1,true);
        this.setCreativeTab(ChowTime.creativeTab);
        setUnlocalizedName("tomato");
        this.setTextureName(ModConstants.MODID + ":tomato");
    }
}
