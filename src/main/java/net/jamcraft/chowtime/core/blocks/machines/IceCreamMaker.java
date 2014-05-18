package net.jamcraft.chowtime.core.blocks.machines;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.GuiIDS;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.tileentities.TEIceCreamMaker;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class IceCreamMaker extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    protected IIcon top;
    @SideOnly(Side.CLIENT)
    protected IIcon side;

    public IceCreamMaker()
    {
        super(Material.iron);
        this.setCreativeTab(ChowTime.creativeTab);
        setBlockName("icecreammaker");
        setBlockTextureName(ModConstants.MODID + ":icecreammaker");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new TEIceCreamMaker();
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
                TEIceCreamMaker logic = (TEIceCreamMaker) world.getTileEntity(x, y, z);

                if (logic != null)
                {
                    entityPlayer.openGui(ChowTime.instance, GuiIDS.ICMaker_Gui, world, x, y, z);
                }
            }

            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.side = par1IconRegister.registerIcon(ModConstants.MODID + ":" + "iceCreamMaker_side");
        this.top = par1IconRegister.registerIcon(ModConstants.MODID + ":" + "iceCream_top");
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

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        int k = p_149691_2_ & 12;
        int l = p_149691_2_ & 3;
        return k == 0 && (p_149691_1_ == 1 || p_149691_1_ == 0) ? this.getTopIcon(l) : (k == 4 && (p_149691_1_ == 5 || p_149691_1_ == 4) ? this.getTopIcon(l) : (k == 8 && (p_149691_1_ == 2 || p_149691_1_ == 3) ? this.getTopIcon(l) : this.getSideIcon(l)));
    }
    public int getRenderType()
    {
        return 31;
    }
}
