package net.jamcraft.chowtime.core.blocks;

import net.jamcraft.chowtime.ChowTime;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by Kayla Marie on 5/14/14.
 */
public class CTLeaves extends Block{

    public CTLeaves(){
        super(Material.leaves);
        this.setCreativeTab(ChowTime.creativeTab);
    }
}
