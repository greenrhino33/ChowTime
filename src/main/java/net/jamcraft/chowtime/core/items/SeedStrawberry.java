package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

/**
 * Created by Kayla Marie on 5/17/14.
 */
public class SeedStrawberry extends ItemSeeds{

    public SeedStrawberry(){
        super(CTInits.CropStrawberry, Blocks.farmland);
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":" + "strawberrySeeds");
        setUnlocalizedName("strawberrySeeds");
    }
}
