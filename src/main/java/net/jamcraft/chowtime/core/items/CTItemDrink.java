package net.jamcraft.chowtime.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.*;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class CTItemDrink extends Potion {

    public CTItemDrink(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    @Override
    public Potion setIconIndex(int par1, int par2){
        super.setIconIndex(par1, par2);
        return this;
    }
}
