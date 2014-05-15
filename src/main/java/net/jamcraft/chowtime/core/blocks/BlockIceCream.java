package net.jamcraft.chowtime.core.blocks;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.block.BlockSnowBlock;

/**
 * Created by Kayla Marie on 5/15/14.
 */
public class BlockIceCream extends BlockSnowBlock {

    public BlockIceCream() {
        super();
        this.setCreativeTab(ChowTime.creativeTab);
        this.setBlockTextureName(ModConstants.MODID + ":IceCream");
    }
}
