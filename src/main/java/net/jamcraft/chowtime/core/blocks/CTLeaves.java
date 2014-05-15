package net.jamcraft.chowtime.core.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class CTLeaves extends BlockLeaves{

    public CTLeaves(){
        super();
        this.setTickRandomly(true);
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockName("floraLeaves");
        this.setCreativeTab(ChowTime.creativeTab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = iconregister.registerIcon(ModConstants.MODID + ":" + this.getUnlocalizedName().substring(5));
    }

    @Override
    public void updateTick (World world, int x, int y, int z, Random random)
    {
        if (!world.isRemote)
        {
            int meta = world.getBlockMetadata(x, y, z);

            if ((meta & 4) == 0)
            {
                boolean nearbyTree = false;
                byte range = 4;
                for (int posX = x - range; posX <= x + range; posX++)
                {
                    for (int posY = y - range; posY <= y + range; posY++)
                    {
                        for (int posZ = z - range; posZ <= z + range; posZ++)
                        {
                            Block block = world.getBlock(posX, posY, posZ);
                            if (block != null && block.canSustainLeaves(world, posX, posY, posZ))
                                nearbyTree = true;
                        }
                    }
                }

                if (!nearbyTree)
                    this.removeLeaves(world, x, y, z);
            }
        }
    }

    public void removeLeaves (World world, int x, int y, int z)
    {
        this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        world.setBlock(x, y, z, Blocks.air, 0, 7);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
    {
        return 0x097A25;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public IIcon getIcon(int var1, int var2) {
        return null;
    }

    @Override
    public String[] func_150125_e() {
        return new String[0];
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        //Block b1 = BlocksInit.glowGlass;
        Block b1 = par1IBlockAccess.getBlock(par2, par3, par4);
        if (b1 == this || b1 == CTInits.CTLeaves)
        {
            return false;
        }
        return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
        //return !true && b1 == BlocksInit.glowGlass ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

}
