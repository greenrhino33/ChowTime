package net.jamcraft.chowtime.core.blocks.machines;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.tileentities.TEFermenter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class Fermenter extends BlockContainer{

    public Fermenter(){
        super(Material.iron);
        this.setCreativeTab(ChowTime.creativeTab);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TEFermenter();
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public int getRenderType()
    {
        return -1;
    }
}
