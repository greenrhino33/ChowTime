package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.recipies.JuicerRecipes;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class ItemPJuice extends Item implements IDynItem
{

    public ItemPJuice()
    {
        super();
        setCreativeTab(ChowTime.creativeTab);
        setTextureName(ModConstants.MODID + ":pumpkinJuice");
        setUnlocalizedName("pumpkinJuice");
    }

    @Override public String getRegistrationName()
    {
        return "pumpkinjuice";
    }

    @Override public void registerRecipe()
    {
        JuicerRecipes.AddRecipe(new ItemStack(Blocks.pumpkin),new ItemStack(this),200);
    }
}
