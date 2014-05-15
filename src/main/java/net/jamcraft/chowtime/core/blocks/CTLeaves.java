package net.jamcraft.chowtime.core.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class CTLeaves extends Block{

    public CTLeaves(){
        super(Material.leaves);
        this.setCreativeTab(ChowTime.creativeTab);
        this.setHardness(0.1F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = iconregister.registerIcon(ModConstants.MODID + ":leavesBasic");
    }
}
