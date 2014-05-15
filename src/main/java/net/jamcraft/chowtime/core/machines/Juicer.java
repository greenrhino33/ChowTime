package net.jamcraft.chowtime.core.machines;

import net.jamcraft.chowtime.core.tileentities.TEJuicer;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class Juicer extends BlockContainer {

    public Juicer(){
        super(Material.iron);
    }

    public static ItemStack OnUse(ItemStack food){
        return new ItemStack(Items.apple);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TEJuicer();
    }
}
