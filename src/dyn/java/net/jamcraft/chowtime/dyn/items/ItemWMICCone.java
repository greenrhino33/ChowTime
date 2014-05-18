package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.dyn.DynItems;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

/**
 * Created by James Hollowell on 5/17/2014.
 */
public class ItemWMICCone extends ItemFood implements IDynItem
{
    public ItemWMICCone()
    {
        super(1, false);
        setCreativeTab(ChowTime.creativeTab);
        setTextureName(ModConstants.MODID + ":watermelonIceCreamCone");
        setUnlocalizedName("watermelonIceCreamCcone");
    }

    @Override public String getRegistrationName()
    {
        return "wmiccone";
    }

    @Override public void registerRecipe()
    {
        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(this),new ItemStack(DynItems.items.get("cone")),new ItemStack(DynItems.items.get("wmicecream")));
    }
}
