package net.jamcraft.chowtime.core.blocks;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.block.BlockSnow;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class BlockIceCream extends BlockSnow {

    public BlockIceCream() {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        this.setBlockTextureName(ModConstants.MODID + ":IceCream");
    }
}
