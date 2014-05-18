package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.Item;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class ItemGrape extends Item
{

    public ItemGrape()
    {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        setUnlocalizedName("grape");
        this.setTextureName(ModConstants.MODID + ":grape");
    }
}
