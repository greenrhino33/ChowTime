package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.items.CTItem;
import net.jamcraft.chowtime.core.lib.CTStrings;
import net.jamcraft.chowtime.dyn.common.IDynItem;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ItemCone extends CTItem implements IDynItem
{

    public ItemCone(){
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":cone");
        this.setUnlocalizedName(CTStrings.Item_Cone);
    }

    @Override public String getRegistrationName()
    {
        return CTStrings.Item_Cone;
    }

    @Override public void registerRecipe()
    {
    }
}
