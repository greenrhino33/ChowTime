package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.items.ItemBarley;
import net.jamcraft.chowtime.core.recipies.JuicerRecipes;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;

/**
 * Created by James Hollowell on 5/18/2014.
 */
public class ItemBarleyFlour extends Item implements IDynItem
{
    public ItemBarleyFlour()
    {
        super();
        setCreativeTab(ChowTime.creativeTab);
        setTextureName(ModConstants.MODID + ":barleyFlour");
        setUnlocalizedName("barleyFlour");
    }

    @Override public String getRegistrationName()
    {
        return "barleyFlour";
    }

    @Override public void registerRecipe()
    {
        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(this), new ItemStack(CTInits.BarleyCrop));
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(this),new ItemStack(Items.bread),.175F);
    }
}
