package net.jamcraft.chowtime.core.blocks.machines;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.GuiIDS;
import net.jamcraft.chowtime.core.tileentities.TEIceCreamMaker;
import net.jamcraft.chowtime.core.tileentities.TEJuicer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class IceCreamMaker extends BlockContainer
{
    public IceCreamMaker()
    {
        super(Material.iron);
        this.setCreativeTab(ChowTime.creativeTab);
        setBlockName("icecreammaker");
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
                    entityPlayer.openGui(ChowTime.instance, GuiIDS.Juicer_Gui, world, x, y, z);
                }
            }

            return true;
        }
    }
}
