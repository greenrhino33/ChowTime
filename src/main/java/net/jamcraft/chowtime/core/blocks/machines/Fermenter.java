package net.jamcraft.chowtime.core.blocks.machines;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.GuiIDS;
import net.jamcraft.chowtime.core.ModConstants;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class Fermenter extends BlockContainer
{

    public Fermenter()
    {
        super(Material.iron);
        this.setCreativeTab(ChowTime.creativeTab);
        setBlockName("fermenter");
        setBlockTextureName(ModConstants.MODID+":fermenter");
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
