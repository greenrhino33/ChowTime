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
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.*;

/**
 * Created by Kayla Marie on 5/16/14.
 */
public class CTItemDrink extends ItemPotion{

    private HashMap effectCache = new HashMap();
    private static final Map field_77835_b = new LinkedHashMap();
    @SideOnly(Side.CLIENT)
    private IIcon field_94591_c;
    @SideOnly(Side.CLIENT)
    private IIcon field_94590_d;
    @SideOnly(Side.CLIENT)
    private IIcon field_94592_ct;

    public CTItemDrink(){
        this.setMaxStackSize(6);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(ChowTime.creativeTab);
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

    public List getEffects(ItemStack par1ItemStack)
    {
        if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("CustomPotionEffects", 9))
        {
            ArrayList arraylist = new ArrayList();
            NBTTagList nbttaglist = par1ItemStack.getTagCompound().getTagList("CustomPotionEffects", 10);

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
                PotionEffect potioneffect = PotionEffect.readCustomPotionEffectFromNBT(nbttagcompound);

                if (potioneffect != null)
                {
                    arraylist.add(potioneffect);
                }
            }

            return arraylist;
        }
        else
        {
            List list = (List)this.effectCache.get(Integer.valueOf(par1ItemStack.getItemDamage()));

            if (list == null)
            {
                list = PotionHelper.getPotionEffects(par1ItemStack.getItemDamage(), false);
                this.effectCache.put(Integer.valueOf(par1ItemStack.getItemDamage()), list);
            }

            return list;
        }
    }

    /**
     * Returns a list of effects for the specified potion damage value.
     */
    public List getEffects(int par1)
    {
        List list = (List)this.effectCache.get(Integer.valueOf(par1));

        if (list == null)
        {
            list = PotionHelper.getPotionEffects(par1, false);
            this.effectCache.put(Integer.valueOf(par1), list);
        }

        return list;
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        if (!par2World.isRemote)
        {
            List list = this.getEffects(par1ItemStack);

            if (list != null)
            {
                Iterator iterator = list.iterator();

                while (iterator.hasNext())
                {
                    PotionEffect potioneffect = (PotionEffect)iterator.next();
                    par3EntityPlayer.addPotionEffect(new PotionEffect(potioneffect));
                }
            }
        }

        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            if (par1ItemStack.stackSize <= 0)
            {
                return new ItemStack(Items.glass_bottle);
            }

            par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }

        return par1ItemStack;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            return par1ItemStack;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int par1, int par2)
    {
        return super.getIconFromDamageForRenderPass(par1,par2);
//        return par2 == 0 ? this.field_94592_ct : super.getIconFromDamageForRenderPass(par1, par2);
    }
}
