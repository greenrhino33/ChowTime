package net.jamcraft.chowtime.core.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.GuiIDS;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class Fermenter extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    protected IIcon top;
    @SideOnly(Side.CLIENT)
    protected IIcon side;

    public Fermenter()
    {
        super(Material.iron);
        this.setCreativeTab(ChowTime.creativeTab);
        setBlockName("fermenter");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TEFermenter();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9)
    {
        super.onBlockActivated(world, x, y, z, entityPlayer, par6, par7, par8, par9);
        if (entityPlayer.isSneaking()) return false;
        else
        {
            if (!world.isRemote)
            {
                TEFermenter logic = (TEFermenter) world.getTileEntity(x, y, z);

                if (logic != null)
                {
                    entityPlayer.openGui(ChowTime.instance, GuiIDS.Fermenter_Gui, world, x, y, z);
                }
            }

            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.side = par1IconRegister.registerIcon(ModConstants.MODID + ":" + "fermenter_side");
        this.top = par1IconRegister.registerIcon(ModConstants.MODID + ":" + "fermenter_top");
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int p_150163_1_)
    {
        return this.side;
    }

    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return this.top;
    }

    //    @Override
    //    public boolean renderAsNormalBlock()
    //    {
    //        return false;
    //    }
    //
    //    @Override
    //    public int getRenderType()
    //    {
    //        return -1;
    //    }
}
