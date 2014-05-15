package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.item.ItemSnowball;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ItemIceCream extends ItemSnowball{

    public ItemIceCream() {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":IceCream");
    }
}
