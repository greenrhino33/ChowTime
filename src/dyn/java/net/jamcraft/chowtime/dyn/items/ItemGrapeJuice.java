package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.recipies.JuicerRecipes;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class ItemGrapeJuice extends Item implements IDynItem
{

    public ItemGrapeJuice()
    {
        super();
        setCreativeTab(ChowTime.creativeTab);
        setTextureName(ModConstants.MODID + ":grapeJuice");
        setUnlocalizedName("grapeJuice");
    }

    @Override public String getRegistrationName()
    {
        return "grapeJuice";
    }

    @Override public void registerRecipe()
    {
        JuicerRecipes.AddRecipe(new ItemStack(CTInits.Grape), new ItemStack(this), 200);
    }
}
