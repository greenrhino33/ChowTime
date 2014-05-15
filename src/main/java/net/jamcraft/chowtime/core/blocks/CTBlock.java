package net.jamcraft.chowtime.core.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class CTBlock extends Block{

    public CTBlock(Material material){
        super(material);
        this.setCreativeTab(ChowTime.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = iconregister.registerIcon(ModConstants.MODID + ":" + this.getUnlocalizedName());
    }
}
