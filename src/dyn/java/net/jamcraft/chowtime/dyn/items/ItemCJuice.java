package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.recipies.JuicerRecipes;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class ItemCJuice extends Item implements IDynItem
{

    public ItemCJuice()
    {
        super();
        setCreativeTab(ChowTime.creativeTab);
        setTextureName(ModConstants.MODID + ":carrotJuice");
        setUnlocalizedName("carrotJuice");
    }

    @Override public String getRegistrationName()
    {
        return "carrotjuice";
    }

    @Override public void registerRecipe()
    {
        JuicerRecipes.AddRecipe(new ItemStack(Items.carrot), new ItemStack(this), 200);
    }
}
