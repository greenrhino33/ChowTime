package net.jamcraft.chowtime.dyn.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.lib.CTStrings;
import net.jamcraft.chowtime.dyn.common.IDynItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ItemCottonCandy extends ItemFood implements IDynItem{

    private boolean alwaysEdible = true;

    public ItemCottonCandy() {
        super(3, 2.0F, false);
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":cottonCandy");
    }
//
//    protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
//    {
//        if (!par2World.isRemote && this.potionId > 0 && par2World.rand.nextFloat() < this.potionEffectProbability)
//        {
//            par3EntityPlayer.addPotionEffect(new PotionEffect(this.potionId, this.potionDuration * 20, this.potionAmplifier));
//        }
//    }

    public ItemFood setAlwaysEdible()
    {
        this.alwaysEdible = true;
        return this;
    }

    @Override public String getRegistrationName()
    {
        return CTStrings.ItemFood_CottonCandy_Blue;
    }

    @Override public void registerRecipe()
    {

    }
}
