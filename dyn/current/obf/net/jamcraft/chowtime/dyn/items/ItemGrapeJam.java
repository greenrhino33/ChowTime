package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.dyn.DynItems;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class ItemGrapeJam extends Item implements IDynItem
{
    public ItemGrapeJam()
    {
        super();
        setCreativeTab(ChowTime.creativeTab);
        setTextureName(ModConstants.MODID + ":grapeJam");
        setUnlocalizedName("grapeJam");
    }

    @Override public String getRegistrationName()
    {
        return "grapeJam";
    }

    @Override public void registerRecipe()
    {
        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(this), new ItemStack(DynItems.items.get("grapeJuice")), new ItemStack(Items.sugar));
    }
}
