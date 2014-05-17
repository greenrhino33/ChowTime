package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPotion;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class CTItemDrink extends ItemPotion{

    public CTItemDrink(){
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(ChowTime.creativeTab);
    }
}
