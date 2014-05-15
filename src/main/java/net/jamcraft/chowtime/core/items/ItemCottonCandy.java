package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class ItemCottonCandy extends ItemFood{

    private boolean alwaysEdible = true;

    public ItemCottonCandy(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
        super(3, 2.0F, false);
        this.setCreativeTab(ChowTime.creativeTab);
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
}
