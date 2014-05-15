package net.jamcraft.chowtime.core.machines;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class Juicer extends BlockContainer {

    public Juicer(){
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return null;
    }
}
