package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.recipies.FermenterRecipies;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class ItemVinegar extends Item implements IDynItem
{
    public ItemVinegar()
    {
        super();
        setCreativeTab(ChowTime.creativeTab);
        setTextureName(ModConstants.MODID + ":vinegar");
        setUnlocalizedName("vinegar");
    }

    @Override public String getRegistrationName()
    {
        return "vinegar";
    }

    @Override public void registerRecipe()
    {
        FermenterRecipies.AddRecipe(new ItemStack(Items.apple), new ItemStack(this), 200);
        FermenterRecipies.AddRecipe(new ItemStack(Items.carrot), new ItemStack(this), 280);
    }
}
