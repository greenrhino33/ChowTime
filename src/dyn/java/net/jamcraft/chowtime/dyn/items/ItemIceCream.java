package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.lib.CTStrings;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.item.ItemSnowball;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ItemIceCream extends ItemSnowball implements IDynItem{

    public ItemIceCream() {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":IceCream");
        this.setUnlocalizedName(CTStrings.ItemFood_IceCreamCone_Strawberry);
    }

    @Override public String getRegistrationName()
    {
        return CTStrings.ItemFood_IceCreamCone_Strawberry;
    }

    @Override public void registerRecipe()
    {

    }
}
