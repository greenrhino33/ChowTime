package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class ItemTomato extends Item
{
    public ItemTomato()
    {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        setUnlocalizedName("tomato");
        this.setTextureName(ModConstants.MODID + ":tomato");
    }
}
