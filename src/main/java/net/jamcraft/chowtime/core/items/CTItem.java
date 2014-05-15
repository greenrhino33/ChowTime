package net.jamcraft.chowtime.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class CTItem extends Item {

    public CTItem(){
        super();
        this.setCreativeTab(ChowTime.creativeTab);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(ModConstants.MODID + ":" + this.getUnlocalizedName().substring(5));
    }
}
