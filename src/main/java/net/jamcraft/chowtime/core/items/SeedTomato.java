package net.jamcraft.chowtime.core.items;

import net.jamcraft.chowtime.ChowTime;
import net.jamcraft.chowtime.core.CTInits;
import net.jamcraft.chowtime.core.ModConstants;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

/**
 * Created by DarkKnight on 5/18/14.
 */
public class SeedTomato extends ItemSeeds {

    public SeedTomato(){
        super(CTInits.CropTomato, Blocks.farmland);
        this.setCreativeTab(ChowTime.creativeTab);
        this.setTextureName(ModConstants.MODID + ":" + "tomatoSeeds");
        setUnlocalizedName("tomatoSeeds");
    }
}
