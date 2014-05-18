package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.recipies.IceCreamRecipies;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ItemStrawberryIceCream extends ItemSnowball implements IDynItem
{

    public ItemStrawberryIceCream()
    {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":strawberryIceCream");
        this.setUnlocalizedName("strawberryIceCream");
    }

    @Override public String getRegistrationName()
    {
        return "strawberryIceCream";
    }

    @Override public void registerRecipe()
    {
        IceCreamRecipies.AddRecipe(new ItemStack(CTInits.Strawberry), new ItemStack(Items.milk_bucket), new ItemStack(this), 60);
    }
}
